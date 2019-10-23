package com.maera;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private RecyclerView _airportsRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpViewsReferences();
        setUpViews();
        setUpEvents();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        final int id = item.getItemId();

        if( id == R.id.search ){


        }
        return true;
    }

    private void setUpViewsReferences() {
        _airportsRecycler = findViewById(R.id.airports);
    }

    private void setUpViews(){
        _airportsRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        _airportsRecycler.setAdapter( new AirportsListAdapter(AirportsDataBase.allAirportsList));
    }

    private void setUpEvents(){
    }
}
