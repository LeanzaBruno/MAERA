package com.maera.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.google.android.material.tabs.TabLayout;
import com.maera.R;
import com.maera.adapter.ViewPagerAdapter;
import com.maera.core.DataBaseManager;
import com.maera.fragment.FilterBottomSheetDialog;
import com.maera.fragment.MetafFragment;
import com.maera.fragment.PronareaFragment;
import com.maera.fragment.SearchByBottomSheetDialog;

public class MainActivity extends AppCompatActivity {
    private MetafFragment _metaf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpViewsReferences();
        setUpViews();
        setUpActionBar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        SearchView searchView = (SearchView)menu.findItem(R.id.search).getActionView();
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchByBottomSheetDialog dialog = new SearchByBottomSheetDialog(_metaf.getFilter());
                dialog.show(getSupportFragmentManager(), "SEARCH");
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                MetafFragment.AdapterFilter adapterFilter = _metaf.getFilter();
                adapterFilter.search(newText);
                return false;
            }
        });

        MenuItem filter = menu.findItem(R.id.filter);
        filter.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                final FilterBottomSheetDialog dialog = new FilterBottomSheetDialog(_metaf.getFilter());
                dialog.show(getSupportFragmentManager(), "FILTER");

                return true;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull  MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void setUpViewsReferences() {
    }

    private void setUpViews() {
        final DataBaseManager manager = DataBaseManager.getInstance(this);
        final ViewPager viewPager = findViewById(R.id.viewPager);
        _metaf = new MetafFragment(manager);
        final Fragment[] fragments = new Fragment[]{_metaf, new PronareaFragment()};
        final TabLayout tabLayout = findViewById(R.id.tabLayout);

        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), fragments));
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setUpActionBar(){
        ActionBar bar = getSupportActionBar();
        if( bar != null )
            bar.setElevation(0);
    }
}
