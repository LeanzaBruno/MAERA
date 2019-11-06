package com.maera.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.os.Bundle;
import android.widget.Toast;

import com.maera.R;
import com.maera.core.Airport;

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
                downloadMetar();
            }
        }
        );

        _tafBtn.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view){
                downloadTaf();
            }
        });
    }

    private
    void
    downloadMetar() {
        _airport.refreshMetar();
        _resultMetar.setText(_airport.getMetar() );
    }

    private
    void
    downloadTaf(){
        _airport.refreshTaf();
        _resultTaf.setText(_airport.getTaf() );
    }

    private
    void
    setUpWeatherReportMessages(){
        downloadMetar();
        if( _airport.hasTaf() )
            downloadTaf();
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
