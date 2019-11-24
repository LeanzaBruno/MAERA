package com.maera.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.maera.fragment.BaseListFragment;

public final class MainPagerAdapter extends FragmentPagerAdapter {
    private final BaseListFragment[] _fragments;
    public MainPagerAdapter(@NonNull FragmentManager fm, BaseListFragment[] fragments) {
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
