package com.maera;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Button;
import android.os.Bundle;
import android.widget.Toast;

public class AirportActivity extends AppCompatActivity {
    private Airport _airport;
    private TextView _icao, _name, _fir, _resultMetar, _resultTaf;
    private Button _metarBtn, _tafBtn;


    private void setUpViewReferences(){
        _icao = findViewById(R.id.icao);
        _name = findViewById(R.id.name);
        _fir = findViewById(R.id.fir);
        _resultMetar = findViewById(R.id.resultMetar);
        _resultTaf = findViewById(R.id.resultTaf);
        _metarBtn = findViewById(R.id.metarBtn);
        _tafBtn = findViewById(R.id.tafBtn);
    }

    private void setUpViews(){
        _icao.setText(_airport.getIcaoCode());
        _name.setText(_airport.getAirportName());
        _fir.setText(_airport.getFir());
    }

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_airport);

        _airport = getIntent().getParcelableExtra("AIRPORT");
        if( _airport != null ) {
            setUpViewReferences();
            setUpViews();
        }
        else
            Toast.makeText(this, "Error! Couldn't find any airport", Toast.LENGTH_SHORT).show();
    }
}
