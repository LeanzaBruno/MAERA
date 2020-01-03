package com.maera.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import com.maera.R;
import com.maera.fragment.FilterBottomSheetDialog;
import com.maera.fragment.MetafFragment;
import com.maera.fragment.SearchByBottomSheetDialog;

public class MainActivity extends AppCompatActivity {
    private MetafFragment _metaf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        final MenuItem refresh = menu.findItem(R.id.refresh);
        refresh.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                _metaf.refresh();
                return false;
            }
        });



        final MenuItem filter = menu.findItem(R.id.filter);
        filter.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                final TextView textView = findViewById(R.id.showing);
                final FilterBottomSheetDialog dialog = new FilterBottomSheetDialog(_metaf.getFilter(), textView);
                dialog.show(getSupportFragmentManager(), "FILTER");
                return true;
            }
        });
        SearchView searchView = (SearchView)menu.findItem(R.id.search).getActionView();
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filter.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
                SearchByBottomSheetDialog dialog = new SearchByBottomSheetDialog(_metaf.getFilter());
                dialog.show(getSupportFragmentManager(), "SEARCH");
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                filter.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
                return false;
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

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull  MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void setUpViews() {
        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        _metaf = (MetafFragment)getSupportFragmentManager().findFragmentById(R.id.metaf);
        _metaf.setDataBaseContext(this);
        _metaf.refresh();
        //final DataBaseManager manager = DataBaseManager.getInstance(this);
        //final ViewPager viewPager = findViewById(R.id.viewPager);
        //_metaf = new MetafFragment(manager);
        //final Fragment[] fragments = new Fragment[]{_metaf, new PronareaFragment()};
        //final TabLayout tabLayout = findViewById(R.id.tabLayout);

        //viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), fragments));
        //tabLayout.setupWithViewPager(viewPager);
    }
}
