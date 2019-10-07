package com.maera;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private TabLayout _tabLayout;
    private ViewPager _viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpViewsReferences();
        setUpViewPager();
        setUpTabLayout();
        setUpEvents();
    }

    private void setUpViewsReferences() {
        _tabLayout = findViewById(R.id.tabLayout);
        _viewPager = findViewById(R.id.viewPager);
    }
    private void setUpViewPager() {
        String[] titles = new String[]{ getResources().getString(R.string.METAR),
                                        getResources().getString(R.string.TAF),
                                        getResources().getString(R.string.PRONAREA)
        };
        _viewPager.setAdapter(new ViewPagerAdapter(getApplicationContext(), getSupportFragmentManager(), titles));
    }

    private void setUpTabLayout() {
        _tabLayout.setupWithViewPager(_viewPager);
    }

    private void setUpEvents(){

    }
}
