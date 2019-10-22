package com.maera;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Se encarga de descargar el mensaje sea cual sea, METAR, TAF o PRONAREA
 *
 */

class WeatherReportDownloader extends AsyncTask<Void, Void, Void>
{
    private WeakReference<Context> _context;
    private ArrayList<Airport> _airports;
    private StringBuilder _finalURL = new StringBuilder();
    private WeatherReport.TYPE _type;

    WeatherReportDownloader(@NonNull Context context,
                            @NonNull ArrayList<Airport> airports,
                            @NonNull WeatherReport.TYPE type){
        _context = new WeakReference<>(context);
        _airports = airports;
        _type = type;
    }


    @Override
    protected void onPreExecute(){
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
    protected void onCancelled()
    {
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        Intent intent = new Intent(_context.get(), ResultActivity.class);
        intent.putParcelableArrayListExtra("RESULT", _airports);
        intent.putExtra("TYPE", _type.toString());
        _context.get().startActivity(intent);
    }
}
