package com.maera.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import com.google.android.material.chip.ChipGroup;
import com.maera.R;
import com.maera.fragment.MetafFragment;


/**
 * Actividad principal
 */
public class MainActivity extends AppCompatActivity {

    /**
     * El fragmento que muestra los aeropuertos.
     */
    private MetafFragment _metaf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpViews();
        _metaf.refrescar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        final MenuItem refresh = menu.findItem(R.id.refresh);
        refresh.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                _metaf.refrescar();
                return false;
            }
        });


        /*
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
        */

        //Buscador

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull  MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void setUpViews() {
        _metaf = (MetafFragment)getSupportFragmentManager().findFragmentById(R.id.metaf);
        _metaf.setDataBaseContext(this);

        SearchView searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextChange(String consulta) {
                _metaf.buscar(consulta);
                return true;
            }

            @Override
            public boolean onQueryTextSubmit(String consulta){
                _metaf.buscar(consulta);
                return true;
            }
        });

        ChipGroup chipGroup = findViewById(R.id.sortBy);
        chipGroup.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup chipGroup, int id) {
                switch (id) {
                    case R.id.all:
                        _metaf.filtrar(MetafFragment.TIPO_FILTRO.TODOS);
                        break;
                    case R.id.eze:
                        _metaf.filtrar(MetafFragment.TIPO_FILTRO.EZE);
                        break;
                    case R.id.cba:
                        _metaf.filtrar(MetafFragment.TIPO_FILTRO.CBA);
                        break;
                    case R.id.doz:
                        _metaf.filtrar(MetafFragment.TIPO_FILTRO.DOZ);
                        break;
                    case R.id.sis:
                        _metaf.filtrar(MetafFragment.TIPO_FILTRO.SIS);
                        break;
                    case R.id.crv:
                        _metaf.filtrar(MetafFragment.TIPO_FILTRO.CRV);
                }
            }
        });

        //final DataBaseManager manager = DataBaseManager.getInstance(this);
        //final ViewPager viewPager = findViewById(R.id.viewPager);
        //_metaf = new MetafFragment(manager);
        //final Fragment[] fragments = new Fragment[]{_metaf, new PronareaFragment()};
        //final TabLayout tabLayout = findViewById(R.id.tabLayout);

        //viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), fragments));
        //tabLayout.setupWithViewPager(viewPager);
    }
}
