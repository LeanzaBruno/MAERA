package com.maera.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;
import com.google.android.material.tabs.TabLayout;
import com.maera.adapter.AirportsListAdapter;
import com.maera.R;
import com.maera.core.DataBaseManager;
import com.maera.core.FIR;
import com.maera.core.TYPE_OF_SEARCH;

public class MainActivity extends AppCompatActivity {
    private DataBaseManager _manager;
    private TabLayout _tabLayout;
    private SearchView _searchView;
    private MenuItem _showItem, _aboutItem;
    private TYPE_OF_SEARCH _typeOfSearch = TYPE_OF_SEARCH.ICAO;
    private RecyclerView _airportsRecycler;
    private AirportsListAdapter _adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _manager = DataBaseManager.getInstance(this);
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
        _searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _tabLayout.setVisibility(View.VISIBLE);
                _showItem.setVisible(false);
                _aboutItem.setVisible(false);
            }
        });

        _searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                _tabLayout.setVisibility(View.GONE);
                _showItem.setVisible(true);
                _aboutItem.setVisible(true);
                return false;
            }
        });

        _searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                final AirportsListAdapter.AirportFilter filter = _adapter.getFilter();
                filter.setTypeOfSearch(_typeOfSearch);
                filter.filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                final AirportsListAdapter.AirportFilter filter = _adapter.getFilter();
                filter.setTypeOfSearch(_typeOfSearch);
                filter.filter(newText);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int id = item.getItemId();
        switch( id ){
            case R.id.about:
                return true;
            case R.id.all:
                _adapter.showAll();
                _adapter.notifyDataSetChanged();
                checkItemChecked(item);
                Toast.makeText(this, "Mostrando todos los aeropuertos", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.EZE:
                _adapter.showOnly(FIR.EZE);
                checkItemChecked(item);
                Toast.makeText(this, "Mostrando FIR Ezeiza", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.CBA:
                _adapter.showOnly(FIR.CBA);
                checkItemChecked(item);
                Toast.makeText(this, "Mostrando FIR Córdoba", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.DOZ:
                _adapter.showOnly(FIR.DOZ);
                checkItemChecked(item);
                Toast.makeText(this, "Mostrando FIR Mendoza", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.SIS:
                _adapter.showOnly(FIR.SIS);
                checkItemChecked(item);
                Toast.makeText(this, "Mostrando FIR Resistencia", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.CRV:
                _adapter.showOnly(FIR.CRV);
                checkItemChecked(item);
                Toast.makeText(this, "Mostrando FIR Comodoro Rivadavia", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.ANT:
                _adapter.showOnly(FIR.ANTARTIDA);
                checkItemChecked(item);
                Toast.makeText(this, "Mostrando FIR Antártida", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void checkItemChecked(MenuItem item ){
        if( item.isChecked() )
            item.setChecked(false);
        else
            item.setChecked(true);
    }

    private void setUpViewsReferences() {
        _airportsRecycler = findViewById(R.id.airports);
        _tabLayout = findViewById(R.id.tabLayout);
    }

    private void setUpViews() {
        _adapter = new AirportsListAdapter(_manager);
        _airportsRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        _airportsRecycler.setAdapter(_adapter);

        _tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}
