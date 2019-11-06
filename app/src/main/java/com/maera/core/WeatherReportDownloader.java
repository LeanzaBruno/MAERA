package com.maera.core;

import android.os.AsyncTask;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;

import com.maera.core.Airport;
import com.maera.core.WeatherReport;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;


/**
 * Se encarga de descargar el mensaje sea cual sea, METAR, TAF o PRONAREA
 *
 */

class WeatherReportDownloader extends AsyncTask<Void, Void, Void>
{
    private Window _window;
    private WeakReference<ProgressBar> _progressBar;
    private ArrayList<Airport> _airports;
    private StringBuilder _finalURL = new StringBuilder();
    private WeatherReport.TYPE _type;
    private String mWeatherReport;

    WeatherReportDownloader(@NonNull ProgressBar progressBar,
                            @NonNull ArrayList<Airport> airports,
                            @NonNull WeatherReport.TYPE type){
        _progressBar = new WeakReference<>(progressBar);
        _airports = airports;
        _type = type;
    }

    WeatherReportDownloader(@NonNull Window window,
                            @NonNull ProgressBar progressBar,
                            @NonNull Airport airport,
                            @NonNull WeatherReport.TYPE type){
        _window = window;
        _progressBar = new WeakReference<>(progressBar);
        _airports = new ArrayList<>();
        _airports.add(airport);
        _type = type;
    }


    @Override
    protected void onPreExecute(){
        _window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        _progressBar.get().setVisibility(View.VISIBLE);
    }

    @Override
    protected Void doInBackground(Void... aVoid) {
        _finalURL.append(_type.getURL());
        for(Airport airport : _airports )
            _finalURL.append(airport.getIcaoCode()).append("+");

        try {
            Document page = Jsoup.connect(_finalURL.toString()).get();
            Elements elements = page.select("td[width=\"100%\"]");

            Iterator<Airport> iterator = _airports.iterator();
            for( Element element : elements ) {
                mWeatherReport = element.text();
                if( _type == WeatherReport.TYPE.METAR )
                    iterator.next().setMetar(element.text());
                if( _type == WeatherReport.TYPE.TAF)
                    iterator.next().setTaf(element.text());
            }
        }
        catch(Exception e) {e.printStackTrace();}
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values)
    {

    }

    @Override
    protected void onPostExecute(Void aVoid) {
        _progressBar.get().setVisibility(View.GONE);
        _window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    String getmWeatherReport(){
        return mWeatherReport;
    }
}
