package com.maera;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;

public final class TafFragment extends Fragment {
    private Context _context;
    private EditText _icaoCode;
    private Button _getBtn;

    TafFragment(Context context){ _context = context; }

    private void setUpViewsReferences(@NonNull View view)
    {
        _icaoCode = view.findViewById(R.id.search);
        _getBtn = view.findViewById(R.id.get);
    }

    private void setUpViews()
    {
        setUpEvents();
    }


    private void setUpEvents(){
        _getBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Airport airport = AirportsDataBase.getAirport(_icaoCode.getText().toString());
                if( airport != null )
                    new WeatherReportDownloader(_context, new ArrayList<Airport>(){{add(airport);}}, WeatherReport.TYPE.TAF).execute();
                else
                    Toast.makeText(_context, "Error: no a valid icao code!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance ){
        final View view = inflater.inflate(R.layout.fragment_metar, container, false);

        setUpViewsReferences(view);
        setUpViews();
        return view;
    }
}
