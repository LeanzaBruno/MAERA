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
    private TextView _icao, _localCode, _name, _fir, _resultMetar, _resultTaf, _location;
    private Button _metarBtn, _tafBtn;
    private ImageView _imageView;


    private void setUpViewReferences(){
        _icao = findViewById(R.id.icao);
        _localCode = findViewById(R.id.localCode);
        _name = findViewById(R.id.name);
        _fir = findViewById(R.id.fir);
        _resultMetar = findViewById(R.id.resultMetar);
        _resultTaf = findViewById(R.id.resultTaf);
    	_location = findViewById(R.id.location);
        _metarBtn = findViewById(R.id.metarBtn);
        _tafBtn = findViewById(R.id.tafBtn);
        _imageView = findViewById(R.id.tafAvailable);
    }

    private void setUpViews(){
        setTitle(_airport.getAirportName());
        _icao.setText(_airport.getIcaoCode());
        _localCode.setText(_airport.getLocalCode());
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
                _resultMetar.setText(metar);
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
                _resultTaf.setText(taf);
            }
        });
    }

    private
    void
    setUpWeatherReportMessages(){
        final ProgressBar progressBar = findViewById(R.id.progressBar);
        new WeatherReportDownloader(getWindow(), progressBar, _airport, WeatherReport.TYPE.METAR).execute();
        final String metar = _airport.getMetar();
        if( metar == null || metar.isEmpty() )
            Toast.makeText(this, "Couldn't get METAR report", Toast.LENGTH_SHORT).show();
        else
            _resultMetar.setText(metar);

        if( !_airport.hasTaf() ) return;

        new WeatherReportDownloader(getWindow(), progressBar, _airport, WeatherReport.TYPE.TAF).execute();
        final String taf = _airport.getTaf();
        if( taf == null || taf.isEmpty() )
             Toast.makeText(this, "Couldn't get TAF report", Toast.LENGTH_SHORT).show();
        else
            _resultTaf.setText(taf);
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
            setUpWeatherReportMessages();
        }
        else
            Toast.makeText(this, "Error! Couldn't find any airport", Toast.LENGTH_SHORT).show();
    }
}
