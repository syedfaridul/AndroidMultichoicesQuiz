package org.hupbd.androidmultichoicesquiz.Adepter;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import org.hupbd.androidmultichoicesquiz.Common.Common;
import org.hupbd.androidmultichoicesquiz.QuestionFragment;

import java.util.List;

public class QuestionFragmentAdapter extends FragmentPagerAdapter {

    Context context;
    List<QuestionFragment> fragmentList;

    public QuestionFragmentAdapter(FragmentManager fm, Context context, List<QuestionFragment> fragmentList) {
        super(fm);
        this.context = context;
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return new StringBuilder("Question").append(position+1).toString();
    }
}
