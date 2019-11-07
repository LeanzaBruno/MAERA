package com.maera.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.Toast;
import com.maera.R;
import com.maera.core.Airport;
import com.maera.core.WeatherReport;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class AirportActivity extends AppCompatActivity {
    private Airport _airport;
    private TextView _icao, _localCode, _name, _fir, _resultMetar, _resultTaf, _location;
    private ImageButton _metarBtn, _tafBtn;
    private ImageView _imageView;
    private ProgressBar _progressBar;


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
        _progressBar = findViewById(R.id.progressBar);
    }

    private void setUpViews(){
        setTitle(_airport.getAirportName());
        _icao.setText(_airport.getIcaoCode());
        _localCode.setText(_airport.getLocalCode());
        _name.setText(_airport.getAirportName());
        _fir.setText(_airport.getFir());
    	_location.setText(_airport.getLocation());

        if( _airport.issuesTaf())
            _imageView.setImageDrawable(getResources().getDrawable(R.drawable.available));
        else {
            _tafBtn.setImageDrawable(getResources().getDrawable(R.drawable.not_available));
            _imageView.setImageDrawable(getResources().getDrawable(R.drawable.not_available));
            _tafBtn.setEnabled(false);
        }
    }

    private void setUpEvents() {
        _metarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){ refreshMetar(); }
        }
        );

        _tafBtn.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view){
                refreshTaf();
            }
        });
    }

    private
    void
    refreshMetar() {
    }

    private
    void
    refreshTaf(){
    }

    private
    void
    setUpWeatherReportMessages(){
        new MessageDownloader().execute();
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


    private
    class MessageDownloader extends AsyncTask<Void, Void, Void>
    {
        private StringBuilder _metarURL = new StringBuilder(), _tafURL;
        private String mMetarReport, mTafReport;

        MessageDownloader(){
            _metarURL.append(WeatherReport.TYPE.METAR.getURL()).append(_airport.getIcaoCode());

            if(_airport.issuesTaf()) {
                _tafURL = new StringBuilder();
                _tafURL.append(WeatherReport.TYPE.TAF.getURL()).append(_airport.getIcaoCode());
            }
        }

        @Override
        protected void onPreExecute(){
            _progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... aVoid) {
            try {
                //METAR
                Document page = Jsoup.connect(_metarURL.toString()).get();
                Elements elements = page.select("td[width=\"100%\"]");
                Element el = elements.first();
                if (el != null)
                    _airport.setMetar(el.text());

                if( _airport.issuesTaf() ) {
                    page = Jsoup.connect(_tafURL.toString()).get();
                    elements = page.select("td[width=\"100%\"]");
                    el = elements.first();
                    if (el != null)
                        _airport.setTaf(el.text());
                }
            }
            catch(Exception e) {e.printStackTrace();}
            return null;
        }

        private
        void
        downloadMETAR(){

        }


        @Override
        protected void onProgressUpdate(Void... values) {
        }

        @Override
        protected void onPostExecute(Void result) {
            _progressBar.setVisibility(View.GONE);
            _resultMetar.setText(_airport.getMetar());
            if(_airport.issuesTaf())
                _resultTaf.setText(_airport.getTaf());
        }
    }
}
