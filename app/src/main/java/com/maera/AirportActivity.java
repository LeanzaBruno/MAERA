package com.maera;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Button;
import android.os.Bundle;
import android.widget.Toast;

public class AirportActivity extends AppCompatActivity {
    private Airport _airport;
    private TextView _icao, _name, _fir, _result, _location;
    private Button _metarBtn, _tafBtn;
    private ImageView _imageView;


    private void setUpViewReferences(){
        _icao = findViewById(R.id.icao);
        _name = findViewById(R.id.name);
        _fir = findViewById(R.id.fir);
        _result = findViewById(R.id.result);
	_location = findViewById(R.id.location);
        _metarBtn = findViewById(R.id.metarBtn);
        _tafBtn = findViewById(R.id.tafBtn);
        _imageView = findViewById(R.id.tafAvailable);
    }

    private void setUpViews(){
        setTitle(_airport.getAirportName());
        _icao.setText(_airport.getIcaoCode());
        _name.setText(_airport.getAirportName());
        _fir.setText(_airport.getFir());
	_location.setText(_airport.getLocation());

        if( _airport.hasTaf())
            _imageView.setImageDrawable(getResources().getDrawable(R.mipmap.check));
        else {
            _imageView.setImageDrawable(getResources().getDrawable(R.mipmap.cross));
            _tafBtn.setEnabled(false);
        }
    }

    private void setUpEvents() {
        _metarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){
                String metar = _airport.getMetar();
                if( metar == null || metar.isEmpty() ) {
                    final ProgressBar progressBar = findViewById(R.id.progressBar);
                    new WeatherReportDownloader(getWindow(), progressBar, _airport, WeatherReport.TYPE.METAR).execute();
                    metar = _airport.getMetar();
                }
                _result.setText(metar);
            }
        }
        );

        _tafBtn.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String taf = _airport.getTaf();
                if( taf == null || taf.isEmpty() ) {
                    final ProgressBar progressBar = findViewById(R.id.progressBar);
                    new WeatherReportDownloader(getWindow(), progressBar, _airport, WeatherReport.TYPE.TAF).execute();
                    taf = _airport.getTaf();
                }
                _result.setText(taf);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_airport);

        _airport = getIntent().getParcelableExtra("AIRPORT");
        if( _airport != null ) {
            setUpViewReferences();
            setUpViews();
            setUpEvents();
        }
        else
            Toast.makeText(this, "Error! Couldn't find any airport", Toast.LENGTH_SHORT).show();
    }
}
