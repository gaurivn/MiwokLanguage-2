package com.example.miwoklanguage;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryAdapter extends FragmentPagerAdapter {

    private String tabTitles[] = new String[] { "Numbers", "Family Members", "Colors", "Phrases" };
    private Context context;

    public CategoryAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int i) {
        if(i==0){
            return new NumbersFragment();
        }
        else if(i==1){
            return new FamilyFragment();
        }
        else if(i==2){
            return new ColorsFragment();
        }
        else{
            return new PhrasesFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
