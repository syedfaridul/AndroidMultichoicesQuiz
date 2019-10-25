package org.hupbd.androidmultichoicesquiz.Adepter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.hupbd.androidmultichoicesquiz.Model.CurrentQuestion;

import java.util.List;

public class AnswerSheetHelperAdapter extends RecyclerView.Adapter<AnswerSheetAdapter.MyViewHolder>{

    Context context;
    List<CurrentQuestion> currentQuestionList;

    public AnswerSheetHelperAdapter(Context context, List<CurrentQuestion> currentQuestionList) {
        this.context = context;
        this.currentQuestionList = currentQuestionList;
    }
    

    @NonNull
    @Override
    public AnswerSheetAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AnswerSheetAdapter.MyViewHolder myViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public void notifyDataSetChange() {
    }
}
