package com.maera;

import android.content.Context;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class MetarFragment extends Fragment {
    private Context _context;
    private EditText _searchText;
    private Button _getBtn;
    private ExpandableListView _firList;
    private AirportsListAdapter _adapter;

    MetarFragment(Context context){ _context = context; }

    private void setUpViewsReferences(@NonNull View view)
    {
        _searchText = view.findViewById(R.id.search);
        _getBtn = view.findViewById(R.id.get);
        _firList = view.findViewById(R.id.list);
    }

    private void setUpViews()
    {
        List<Pair<String, List<Airport>>> data = new ArrayList<>();
        for( FIR fir : AirportsDataBase.getFIRList() )
            data.add( new Pair<>(fir.getName(), fir.getAirports()));
        _adapter = new AirportsListAdapter( _context, data, WeatherReport.TYPE.METAR);
        _firList.setAdapter( _adapter );
        setUpEvents();
    }

    private void setUpEvents() {
        _firList.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int i) {
                _searchText.setEnabled(false);
            }
        });

        _getBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Airport> airports;
                if( _searchText.isEnabled() ){
                    if(_searchText.getText().length() < 4 ){
                        Toast.makeText(_context, "Debes ingresar un código OACI", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    airports = new ArrayList<>();
                    airports.add(AirportsDataBase.getAirport(_searchText.getText().toString()));
                }
                else
                    airports = _adapter.getRequestedAirports();

                new WeatherReportDownloader( _context, airports, WeatherReport.TYPE.METAR ).execute();
            }
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup viewGroup,
                             @Nullable Bundle savedInstance) {

        final View view = inflater.inflate(R.layout.fragment_metar, viewGroup, false);

        setUpViewsReferences(view);
        setUpViews();
        return view;
    }

}
