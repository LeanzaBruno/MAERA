package com.maera.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public final class ViewPagerAdapter extends FragmentPagerAdapter {
    private Fragment[] _fragments;
    public ViewPagerAdapter(@NonNull FragmentManager fm, @NonNull Fragment[] fragments) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        _fragments = fragments;
    }

    @Override
    public int getCount(){
        return _fragments.length;
    }

    @Override
    @NonNull
    public Fragment getItem(int position){
        return _fragments[position];
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) return "METAR & TAF";
        else return "PRONAREA";
    }
}
