package com.maera.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import com.google.android.material.tabs.TabLayout;
import com.maera.R;
import com.maera.adapter.MainPagerAdapter;
import com.maera.core.DataBaseManager;
import com.maera.core.TypeOfSearch;
import com.maera.fragment.AirportsListFragment;
import com.maera.fragment.BottomSheetFragment;

public class MainActivity extends AppCompatActivity {
    private TabLayout _categoriesOptions;
    private SearchView _searchView;
    private AirportsListFragment[] _fragments;
    private TypeOfSearch _typeOfSearch;

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
        _typeOfSearch = new TypeOfSearch();
        getMenuInflater().inflate(R.menu.main, menu);

        MenuItem aboutItem = menu.findItem(R.id.about);
        _searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        _searchView.setQueryHint(getResources().getString(R.string.search_hint));
        _searchView.setIconifiedByDefault(true);
        _searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetFragment dialog = new BottomSheetFragment(_typeOfSearch);
                dialog.show(getSupportFragmentManager(), "TAG");
            }
        });

        aboutItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });

        _searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                search(newText);
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
        _categoriesOptions = findViewById(R.id.categories);
    }

    private void setUpViews() {
        final DataBaseManager manager = DataBaseManager.getInstance(this);
        _fragments = new AirportsListFragment[7];
        _fragments[0] = new AirportsListFragment(AirportsListFragment.FRAGMENT_TYPE.FAVOURITE, manager);
        _fragments[1] = new AirportsListFragment(AirportsListFragment.FRAGMENT_TYPE.AIRPORTS, manager);
        _fragments[2] = new AirportsListFragment(AirportsListFragment.FRAGMENT_TYPE.EZE, manager);
        _fragments[3] = new AirportsListFragment(AirportsListFragment.FRAGMENT_TYPE.CBA, manager);
        _fragments[4] = new AirportsListFragment(AirportsListFragment.FRAGMENT_TYPE.DOZ, manager);
        _fragments[5] = new AirportsListFragment(AirportsListFragment.FRAGMENT_TYPE.SIS, manager);
        _fragments[6] = new AirportsListFragment(AirportsListFragment.FRAGMENT_TYPE.CRV, manager);
        ViewPager viewPager = findViewById(R.id.viewPager);
        _categoriesOptions.setupWithViewPager(viewPager);

        MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager(), _fragments);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(_searchView != null && _searchView.getQuery().length() != 0)
                    search(_searchView.getQuery().toString());
            }

            @Override
            public void onPageSelected(int position) {
                if(_searchView != null && _searchView.getQuery().length() != 0)
                    search(_searchView.getQuery().toString());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void search(String text){
        final AirportsListFragment.AirportFilter filter = _fragments[_categoriesOptions.getSelectedTabPosition()].getfilter();
        filter.filterBy(_typeOfSearch);
        filter.filter(text);
    }
}
