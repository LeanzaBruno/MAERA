package com.maera;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ExpandableListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public final class MetarFragment extends Fragment {
    private Context _context;
    private EditText _icaoCode;
    private Button _getBtn;
    private ExpandableListView _firList;
    private AirportsListAdapter _adapter;

    MetarFragment(Context context){ _context = context; }

    private void setUpViewsReferences(@NonNull View view)
    {
        _icaoCode = view.findViewById(R.id.search);
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
        _getBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                if(_icaoCode.getText().length() < 4 ){
                    Toast.makeText(_context, "Debes ingresar un código OACI", Toast.LENGTH_SHORT).show();
                    return;
                }
                Airport airport = AirportsDataBase.getAirport(_icaoCode.getText().toString().toUpperCase());
                if( airport != null )
                    new WeatherReportDownloader(_context,airport, WeatherReport.TYPE.METAR).execute();
                else
                    Toast.makeText(_context, "El código OACI ingresado es incorrecto", Toast.LENGTH_SHORT).show();
                 */

                new WeatherReportDownloader( _context, _adapter.getRequestedAirports(), WeatherReport.TYPE.METAR ).execute();
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
