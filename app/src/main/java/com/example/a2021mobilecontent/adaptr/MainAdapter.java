package com.example.a2021mobilecontent.adaptr;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.a2021mobilecontent.fragment.MainFragment.FragmentSleep;
import com.example.a2021mobilecontent.fragment.MainFragment.FragmentCaffeine;

import java.util.ArrayList;
public class MainAdapter extends FragmentStatePagerAdapter {
    private ArrayList<Fragment> items;


    private ArrayList<String> itext=new ArrayList<String>();

    public MainAdapter(FragmentManager fm) {
        super(fm);
        items = new ArrayList<Fragment>();
        items.add(new FragmentCaffeine());
        items.add(new FragmentSleep());

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


