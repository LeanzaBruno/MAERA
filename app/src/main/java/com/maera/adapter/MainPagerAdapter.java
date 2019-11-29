package com.maera.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.maera.fragment.AirportsListFragment;

public final class MainPagerAdapter extends FragmentPagerAdapter {
    private final AirportsListFragment[] _fragments;
    public MainPagerAdapter(@NonNull FragmentManager fm, AirportsListFragment[] fragments) {
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
    public CharSequence getPageTitle(int position){
        return _fragments[position].getTitle();
    }
}
