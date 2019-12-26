package com.maera.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.Toast;
import com.maera.R;
import com.maera.core.Airport;
import com.maera.core.DataBaseManager;
import com.maera.core.WeatherReport;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class AirportActivity extends AppCompatActivity {
    private Airport _airport;
    private TextView _resultMETAR, _resultTAF;
    private ImageButton _refreshMETAR, _refreshTAF, _copyTAF;
    private Button _copyMETAR;

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_airport, menu);

        MenuItem favourite = menu.findItem(R.id.favourite);
        if(_airport.isFavourite())
            favourite.setIcon(R.drawable.favorite_on);
        else
            favourite.setIcon(R.drawable.favorite_off);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if( item.getItemId() == R.id.info)
            showHelpDialog();
        if( item.getItemId() == R.id.favourite){
            final DataBaseManager dataBase = DataBaseManager.getInstance(this);
            if(_airport.isFavourite()) {
                _airport.setFavourite(false);
                dataBase.setFavourite(_airport, false);
                item.setIcon(R.drawable.favorite_off);
            }
            else {
                _airport.setFavourite(true);
                dataBase.setFavourite(_airport, true);
                item.setIcon(R.drawable.favorite_on);
            }
        }
        return false;
    }

    private void setUpViewReferences(){
        _resultMETAR = findViewById(R.id.METAR);
        _resultTAF = findViewById(R.id.TAF);
        _refreshMETAR = findViewById(R.id.refreshMETAR);
        _refreshTAF = findViewById(R.id.refreshTAF);
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

        TextView icao = findViewById(R.id.icao);
        icao.setText(_airport.getIcaoCode());

        TextView anac = findViewById(R.id.anac);
        anac.setText(_airport.getLocalCode());

        TextView fir = findViewById(R.id.fir);
        fir.setText(_airport.getFir().name());

        TextView name = findViewById(R.id.name);
        name.setText(_airport.getName());

        TextView location = findViewById(R.id.location);
        location.setText(_airport.getLocation().toString());

        if(!_airport.issuesTaf())
            _resultTAF.setText(getString(R.string.noTAF));

    }

    private void setUpEvents() {
        _refreshMETAR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){ refreshMetar(); }
        }
        );

        _refreshTAF.setOnClickListener( new View.OnClickListener(){
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
                    clipboardManager.setPrimaryClip(ClipData.newPlainText("source text", _resultMETAR.getText()));
                Toast.makeText(AirportActivity.this, "METAR copiado", Toast.LENGTH_SHORT).show();
            }
        });

        _copyTAF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                if( clipboardManager != null)
                    clipboardManager.setPrimaryClip( ClipData.newPlainText("source text", _resultTAF.getText()));
                Toast.makeText(AirportActivity.this, "TAF copiado", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private
    void
    refreshMetar() {
        new MessageDownloader(this, WeatherReport.METAR).execute();
    }

    private
    void
    refreshTaf(){
        new MessageDownloader(this, WeatherReport.TAF).execute();
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
        private static final String CSS_QUERY = "td[width=\"100%\"]";
        private ProgressDialog _dialog;
        private WeatherReport _type = null;
        private StringBuilder _metarURL, _pronareaURL, _tafURL;
        private String _metarResult, _tafResult, _pronareaResult;

        MessageDownloader(@NonNull Context context){
            _dialog = new ProgressDialog(context);
            _dialog.setCancelable(false);

            createMETARRequest();
            if(_airport.issuesTaf()) createTAFRequest();
            createPRONAREARequest();
        }

        MessageDownloader(@NonNull Context context, WeatherReport type){
            _dialog = new ProgressDialog(context);
            _type = type;

            if( _type == WeatherReport.METAR ) createMETARRequest();
            else if( type == WeatherReport.TAF ) createTAFRequest();
            else createPRONAREARequest();
        }

        private void createMETARRequest(){
            _metarURL = new StringBuilder();
            _metarURL.append(WeatherReport.METAR.generateURL(_airport));
        }

        private void createTAFRequest(){
            _tafURL = new StringBuilder();
            _tafURL.append(WeatherReport.TAF.generateURL(_airport));
        }

        private void createPRONAREARequest(){
            _pronareaURL = new StringBuilder();
            _pronareaURL.append(WeatherReport.PRONAREA.generateURL(_airport));
        }

        private String getMessage(@NonNull Document page){
            final Elements query = page.select(CSS_QUERY);
            final Element element = query.first();
            if (element != null)
                return element.text();
            else
                return "";
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
                if(_type == null ){
                    _metarResult = getMessage(Jsoup.connect(_metarURL.toString()).get());
                    _tafResult = getMessage(Jsoup.connect(_tafURL.toString()).get());
                    _pronareaResult = getMessage(Jsoup.connect(_pronareaURL.toString()).get());
                }
                else {
                    switch (_type) {
                        case METAR:
                            _metarResult = getMessage(Jsoup.connect(_metarURL.toString()).get());
                            break;
                        case TAF:
                            _tafResult = getMessage(Jsoup.connect(_tafURL.toString()).get());
                            break;
                        case PRONAREA:
                            _pronareaResult = getMessage(Jsoup.connect(_pronareaURL.toString()).get());
                    }
                }
            }catch (Exception exception){exception.printStackTrace();}
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }

        @Override
        protected void onPostExecute(Void result) {
            if(_type == null ){
                _resultMETAR.setText(_metarResult);
                _resultTAF.setText(_tafResult);
            }else {
                switch (_type) {
                    case METAR:
                        _resultMETAR.setText(_metarResult);
                        Toast.makeText(AirportActivity.this, "METAR actualizado", Toast.LENGTH_SHORT).show();
                        break;
                    case TAF:
                        if (_tafResult.isEmpty())
                            _resultTAF.setText(getResources().getString(R.string.noTAF));
                        else
                            _resultTAF.setText(_tafResult);
                        Toast.makeText(AirportActivity.this, "TAF actualizado", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
            _dialog.dismiss();
        }
    }
}
