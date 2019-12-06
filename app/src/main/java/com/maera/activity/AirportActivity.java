package com.maera.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.AsyncTask;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
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
    private TextView _resultMetar, _resultTaf;
    private ImageButton _metarBtn, _tafBtn, _copyMETAR, _copyTAF;

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_airport, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if( item.getItemId() == R.id.info)
            showHelpDialog();
        return true;
    }

    private void setUpViewReferences(){
        _resultMetar = findViewById(R.id.resultMetar);
        _resultTaf = findViewById(R.id.resultTaf);
        _metarBtn = findViewById(R.id.metarBtn);
        _tafBtn = findViewById(R.id.tafBtn);
        _copyMETAR = findViewById(R.id.copyMETAR);
        _copyTAF = findViewById(R.id.copyTAF);
    }

    private void showHelpDialog(){
        final ViewGroup container = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_messages_codes, container, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void setUpViews(){
        setTitle(_airport.getIcaoCode() + " / " + _airport.getLocalCode());
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

        _copyMETAR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                if( clipboardManager != null )
                    clipboardManager.setPrimaryClip(ClipData.newPlainText("source text", _resultMetar.getText()));
                Toast.makeText(AirportActivity.this, "METAR copiado", Toast.LENGTH_SHORT).show();
            }
        });

        _copyTAF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                if( clipboardManager != null)
                    clipboardManager.setPrimaryClip( ClipData.newPlainText("source text", _resultTaf.getText()));
                Toast.makeText(AirportActivity.this, "TAF copiado", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private
    void
    refreshMetar() {
        new MessageDownloader(this, WeatherReport.TYPE.METAR).execute();
    }

    private
    void
    refreshTaf(){
        new MessageDownloader(this, WeatherReport.TYPE.TAF).execute();
    }

    private
    void
    setUpWeatherReportMessages(){
        new MessageDownloader(this).execute();
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
        private ProgressDialog _dialog;
        private WeatherReport.TYPE _type = null;
        private StringBuilder _metarURL = new StringBuilder(), _tafURL;

        MessageDownloader(@NonNull Context context){
            _dialog = new ProgressDialog(context);
            _metarURL.append(WeatherReport.TYPE.METAR.getURL()).append(_airport.getIcaoCode());

            if(_airport.issuesTaf()) {
                _tafURL = new StringBuilder();
                _tafURL.append(WeatherReport.TYPE.TAF.getURL()).append(_airport.getIcaoCode());
            }
        }

        MessageDownloader(@NonNull Context context, WeatherReport.TYPE type){
            _dialog = new ProgressDialog(context);
            _type = type;
            if( _type == WeatherReport.TYPE.METAR )
                _metarURL.append(_type.getURL()).append(_airport.getIcaoCode());
            if( type == WeatherReport.TYPE.TAF ){
                _tafURL = new StringBuilder();
                _tafURL.append(_type.getURL()).append(_airport.getIcaoCode());
            }
        }

        @Override
        protected void onPreExecute(){
            _dialog.setMessage("Descargando...");
            _dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            _dialog.show();
        }

        @Override
        protected Void doInBackground(Void... aVoid) {
            try {
                Document page;
                Elements elements;
                Element el;
                if( _type == null ) {
                    //METAR
                    page = Jsoup.connect(_metarURL.toString()).get();
                    elements = page.select("td[width=\"100%\"]");
                    el = elements.first();
                    if (el != null)
                        _airport.setMetar(el.text());

                    //TAF
                    if( _airport.issuesTaf() ) {
                        page = Jsoup.connect(_tafURL.toString()).get();
                        elements = page.select("td[width=\"100%\"]");
                        el = elements.first();
                        if (el != null)
                            _airport.setTaf(el.text());
                    }
                }

                else if(_type == WeatherReport.TYPE.METAR) {
                    page = Jsoup.connect(_metarURL.toString()).get();
                    elements = page.select("td[width=\"100%\"]");
                    el = elements.first();
                    if (el != null)
                        _airport.setMetar(el.text());
                }
                else if(_type == WeatherReport.TYPE.TAF){
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


        @Override
        protected void onProgressUpdate(Void... values) {
        }

        @Override
        protected void onPostExecute(Void result) {
            if( _type == null ) {
                _resultMetar.setText(_airport.getMetar());
                if (_airport.issuesTaf()) _resultTaf.setText(_airport.getTaf());
            }
            else if( _type == WeatherReport.TYPE.METAR ) {
                _resultMetar.setText(_airport.getMetar());
                Toast.makeText(AirportActivity.this, "METAR actualizado", Toast.LENGTH_SHORT).show();
            }

            else {
                _resultTaf.setText(_airport.getTaf());
                Toast.makeText(AirportActivity.this, "TAF actualizado", Toast.LENGTH_SHORT).show();
            }
            _dialog.dismiss();
        }
    }
}
