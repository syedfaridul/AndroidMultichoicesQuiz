package org.hupbd.androidmultichoicesquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.DisplayMetrics;

import org.hupbd.androidmultichoicesquiz.Adepter.CategoryAdapter;
import org.hupbd.androidmultichoicesquiz.Common.SpaceDecoration;
import org.hupbd.androidmultichoicesquiz.DBHelper.DBHelper;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recycler_category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("MCQ Quiz 2019");
        setSupportActionBar(toolbar);

        recycler_category = (RecyclerView)findViewById(R.id.recycler_category);
        recycler_category.setHasFixedSize(true);
        recycler_category.setLayoutManager(new GridLayoutManager(this,2));

//        Get Screen Height
//        DisplayMetrics displayMetrics = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//        int height = displayMetrics.heightPixels / 8; //Max size of item in category
        CategoryAdapter adapter = new CategoryAdapter(MainActivity.this, DBHelper.getInstance(this).getAllCategory());
        int spaceinpixel = 8;
        recycler_category.addItemDecoration(new SpaceDecoration(spaceinpixel));
        recycler_category.setAdapter(adapter);

    }
}
