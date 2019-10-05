package com.maera;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


final class ViewPagerAdapter extends FragmentPagerAdapter {
    private String[] _titles;

    ViewPagerAdapter(@NonNull FragmentManager manager, String[] titles) {
        super(manager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        _titles = titles;
    }

    @Override
    public Fragment getItem(int index) {
        switch (index) {
            case 0:
                return new MetarFragment();
            case 1:
                return new TafFragment();
            case 2:
                return new PronareaFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return _titles.length;
    }

    @Override
    public CharSequence getPageTitle(int index) {
        return _titles[index];
    }
}
