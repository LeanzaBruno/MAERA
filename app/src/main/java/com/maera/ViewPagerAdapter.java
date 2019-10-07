package com.maera;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


final class ViewPagerAdapter extends FragmentPagerAdapter {
    private Context _context;
    private String[] _titles;

    ViewPagerAdapter(Context context, @NonNull FragmentManager manager, String[] titles) {
        super(manager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        _context = context;
        _titles = titles;
    }

    @Override
    @Nullable
    public Fragment getItem(int index) {
        switch (index) {
            case 0:
                return new MetarFragment(_context);
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
