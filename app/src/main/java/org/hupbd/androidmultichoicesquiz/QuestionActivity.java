package org.hupbd.androidmultichoicesquiz;

import android.os.Bundle;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;

import android.os.CountDownTimer;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.widget.TextView;

import org.hupbd.androidmultichoicesquiz.Adepter.AnswerSheetAdapter;
import org.hupbd.androidmultichoicesquiz.Common.Common;
import org.hupbd.androidmultichoicesquiz.DBHelper.DBHelper;
import org.hupbd.androidmultichoicesquiz.Model.CurrentQuestion;

import java.util.concurrent.TimeUnit;

public class QuestionActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    int time_play = Common.TOTAL_TIME;
    boolean isAnswerModeView = false;


    TextView txt_right_answer, txt_timer;


    RecyclerView answer_sheet_view;
    AnswerSheetAdapter answerSheetAdapter;


    //ctrl+O

    @Override
    protected void onDestroy() {
        if (Common.countDownTimer != null)
            Common.countDownTimer.cancel();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(Common.selectedCategory.getName());
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, "Open navigation drawer", "Close navigation drawer");
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //First, we need take question from DB
        takeQuestion();

        if (Common.questionList.size() > 0) {

            //Show Textview right answer and text View timer
            txt_right_answer = (TextView) findViewById(R.id.txt_question_right);
            txt_timer = (TextView) findViewById(R.id.txt_timer);

            txt_timer.setVisibility(View.VISIBLE);
            txt_right_answer.setVisibility(View.VISIBLE);

            txt_right_answer.setText(new StringBuilder(String.format("%d/%d", Common.right_answer_count, Common.questionList.size())));

            countTimer();


            //View
            answer_sheet_view = (RecyclerView) findViewById(R.id.grid_answer);
            answer_sheet_view.setHasFixedSize(true);
            if (Common.questionList.size() > 5) //It question List nave size > 2 we will sperate 2 raws
                answer_sheet_view.setLayoutManager(new GridLayoutManager(this, Common.questionList.size() / 2));
            answerSheetAdapter = new AnswerSheetAdapter(this, Common.answerSheetList);
            answer_sheet_view.setAdapter(answerSheetAdapter);
        }


    }


    private void countTimer() {
        if (Common.countDownTimer == null) {

            Common.countDownTimer = new CountDownTimer(Common.TOTAL_TIME, 1000) {
                @Override
                public void onTick(long l) {
                    txt_timer.setText(String.format("%02d:%02d",
                            TimeUnit.MILLISECONDS.toMinutes(1),
                            TimeUnit.MILLISECONDS.toSeconds(1) -
                                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(1))));
                    time_play -= 1000;
                }

                @Override
                public void onFinish() {
                    //Finish Game

                }
            }.start();
        } else {
            Common.countDownTimer.cancel();
            Common.countDownTimer = new CountDownTimer(Common.TOTAL_TIME, 1000) {
                @Override
                public void onTick(long l) {
                    txt_timer.setText(String.format("%02d:%02d",
                            TimeUnit.MILLISECONDS.toMinutes(1),
                            TimeUnit.MILLISECONDS.toSeconds(1) -
                                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(1))));
                    time_play -= 1000;
                }

                @Override
                public void onFinish() {
                    //Finish Game

                }
            }.start();

        }
    }

    private void takeQuestion() {

        Common.questionList = DBHelper.getInstance(this).getQuestionByCategory(Common.selectedCategory.getId());
        if (Common.questionList.size() == 0) {
            //If no question
            new MaterialStyledDialog.Builder(this)
                    .setTitle("Opps !")
                    .setIcon(R.drawable.ic_sentiment_very_dissatisfied_black_24dp)
                    .setDescription("We don't have any question in this" + Common.selectedCategory.getName() + "category")
                    .setPositiveText("Ok")
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            dialog.dismiss();
                            finish();
                        }
                    }).show();
        } else {
            if (Common.answerSheetList.size() > 0)
                Common.answerSheetList.clear();
            //Gen answer item from question
            //30 question = 30 answer sheet item
            //1question = 1 answer sheet item

            for (int i = 0; i < Common.questionList.size(); i++) {
                //Because we need take index of Question in list , so we will use for i
                Common.answerSheetList.add(new CurrentQuestion(i, Common.ANSWER_TYPE.NO_ANSWER));// Default all answer is no answer

            }
        };
    }


    @Override
    public void onBackpressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.question, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }
}
