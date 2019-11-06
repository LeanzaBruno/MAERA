package com.maera.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;
import com.maera.core.AirportsDataBase;
import com.maera.adapter.AirportsListAdapter;
import com.maera.R;
import com.maera.core.Airport;
import com.maera.core.TYPE_OF_SEARCH;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Airport> _airports;
    private SearchView _searchView;
    private TYPE_OF_SEARCH _typeOfSearch = TYPE_OF_SEARCH.CODE;
    private RecyclerView _airportsRecycler;
    private AirportsListAdapter _adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpViewsReferences();
        setUpViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        _searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        _searchView.setQueryHint(getResources().getString(R.string.search_hint_code));
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
            case R.id.searchBy:
            case R.id.about:
                return true;
            case R.id.searchByName:
                _typeOfSearch = TYPE_OF_SEARCH.NAME;
                _searchView.setQueryHint(getResources().getString(R.string.search_hint_name));
                checkItemChecked(item);
                return true;
            case R.id.searchByCode:
                _typeOfSearch = TYPE_OF_SEARCH.CODE;
                _searchView.setQueryHint(getResources().getString(R.string.search_hint_code));
                checkItemChecked(item);
                return true;
            case R.id.searchByLocation:
                _typeOfSearch = TYPE_OF_SEARCH.LOCATION;
                _searchView.setQueryHint(getResources().getString(R.string.search_hint_location));
                checkItemChecked(item);
                return true;
            case R.id.all:
                _adapter.showAll();
                _adapter.notifyDataSetChanged();
                checkItemChecked(item);
                Toast.makeText(this, "Mostrando todos los aeropuertos", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.EZE:
                _adapter.showOnly("EZE");
                checkItemChecked(item);
                Toast.makeText(this, "Mostrando  FIR EZE", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.CBA:
                _adapter.showOnly("CBA");
                checkItemChecked(item);
                Toast.makeText(this, "Mostrando  FIR CBA", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.DOZ:
                _adapter.showOnly("DOZ");
                checkItemChecked(item);
                Toast.makeText(this, "Mostrando  FIR DOZ", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.SIS:
                _adapter.showOnly("SIS");
                checkItemChecked(item);
                Toast.makeText(this, "Mostrando  FIR SIS", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.CRV:
                _adapter.showOnly("CRV");
                checkItemChecked(item);
                Toast.makeText(this, "Mostrando FIR CRV", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.ANT:
                _adapter.showOnly("ANT");
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
        _airports = AirportsDataBase.allAirportsList;
        _airportsRecycler = findViewById(R.id.airports);
    }

    private void setUpViews() {
        _adapter = new AirportsListAdapter(_airports);
        _airportsRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        _airportsRecycler.setAdapter(_adapter);

    }
}
