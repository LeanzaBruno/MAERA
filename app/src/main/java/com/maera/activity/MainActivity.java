package com.maera.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import com.google.android.material.tabs.TabLayout;
import com.maera.R;
import com.maera.adapter.MainPagerAdapter;
import com.maera.core.DataBaseManager;
import com.maera.core.TYPE_OF_SEARCH;
import com.maera.fragment.BaseListFragment;

public class MainActivity extends AppCompatActivity {
    private TabLayout _searchOptions, _categoriesOptions;
    private SearchView _searchView;
    private MenuItem _showItem, _aboutItem;
    private BaseListFragment[] _fragments;
    private TYPE_OF_SEARCH _typeOfSearch = TYPE_OF_SEARCH.ICAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpViewsReferences();
        setUpViews();
        ActionBar bar = getSupportActionBar();
        if( bar != null )
            bar.setElevation(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        _showItem = menu.findItem(R.id.showOnly);
        _aboutItem = menu.findItem(R.id.about);

        _searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        _searchView.setQueryHint(getResources().getString(R.string.search_hint));
        _searchView.setIconifiedByDefault(true);

        _searchOptions.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                final int pos = tab.getPosition();
                switch (pos){
                    case 0:
                        _typeOfSearch = TYPE_OF_SEARCH.ICAO;
                        break;
                    case 1:
                        _typeOfSearch = TYPE_OF_SEARCH.LOCAL;
                        break;
                    case 2:
                        _typeOfSearch = TYPE_OF_SEARCH.LOCATION;
                        break;
                    case 3:
                        _typeOfSearch = TYPE_OF_SEARCH.NAME;
                        break;
                }
                _searchView.setQuery("",false);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }

            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });

        _searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                _fragments[_categoriesOptions.getSelectedTabPosition()].filter(newText);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull  MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void setUpViewsReferences() {
        _searchOptions = findViewById(R.id.searchOptions);
        _categoriesOptions = findViewById(R.id.categories);
    }

    private void setUpViews() {
        final DataBaseManager manager = DataBaseManager.getInstance(this);
        _fragments = new BaseListFragment[8];
        _fragments[0] = new BaseListFragment(BaseListFragment.FRAGMENT_TYPE.FAVOURITE, manager);
        _fragments[1] = new BaseListFragment(BaseListFragment.FRAGMENT_TYPE.AIRPORTS, manager);
        _fragments[2] = new BaseListFragment(BaseListFragment.FRAGMENT_TYPE.EZE, manager);
        _fragments[3] = new BaseListFragment(BaseListFragment.FRAGMENT_TYPE.CBA, manager);
        _fragments[4] = new BaseListFragment(BaseListFragment.FRAGMENT_TYPE.DOZ, manager);
        _fragments[5] = new BaseListFragment(BaseListFragment.FRAGMENT_TYPE.SIS, manager);
        _fragments[6] = new BaseListFragment(BaseListFragment.FRAGMENT_TYPE.CRV, manager);
        _fragments[7] = new BaseListFragment(BaseListFragment.FRAGMENT_TYPE.ANT, manager);
        ViewPager viewPager = findViewById(R.id.viewPager);
        _categoriesOptions.setupWithViewPager(viewPager);

        MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager(), _fragments);
        viewPager.setAdapter(adapter);
    }
}
