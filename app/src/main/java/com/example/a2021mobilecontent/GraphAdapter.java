package com.example.a2021mobilecontent;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class GraphAdapter extends FragmentStatePagerAdapter {
    private ArrayList<Fragment> items;


    private ArrayList<String> itext=new ArrayList<String>();

    public GraphAdapter(FragmentManager fm) {
        super(fm);
        items = new ArrayList<Fragment>();
        items.add(new GraphFragment_1());
        items.add(new GraphFragment_2());

        itext.add("카폐인 섭취량");
        itext.add("수면 시간");

    }

    @Override
    public Fragment getItem(int position) {
        return items.get(position);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @NonNull
    @Override
    public CharSequence getPageTitle(int position){
        return itext.get(position);
    }
}
