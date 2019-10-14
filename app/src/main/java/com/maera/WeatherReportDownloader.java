package com.maera;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.List;


/**
 * Se encarga de descargar el mensaje sea cual sea, METAR, TAF o PRONAREA
 *
 */

class WeatherReportDownloader extends AsyncTask<Void, Void, String>
{
    private Context _context;
    private StringBuilder _finalURL = new StringBuilder();

    WeatherReportDownloader(@NonNull Context context,
                            @NonNull List<Airport> airports,
                            @NonNull WeatherReport.TYPE type){
        _context = context;
        _finalURL.append(type.getURL());
        for( Airport airport : airports )
            _finalURL.append( airport.getIcaoCode() ).append("+");
    }


    @Override
    protected void onPreExecute(){
        Toast.makeText(_context, "Descargando mensaje(s)...", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected String doInBackground(Void... codes) {
        try {
            Document page = Jsoup.connect(_finalURL.toString()).get();
            Elements elements = page.select("td[width=\"100%\"]");

            StringBuilder result = new StringBuilder();

            for( Element element : elements )
                result.append(element.text()).append("\n\n");

            return result.toString();
        }
        catch(Exception e){
            e.printStackTrace();
        }
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
    protected void onPostExecute(String result)
    {
        Intent intent = new Intent(_context, ResultActivity.class);
        WeatherReport report = new WeatherReport(result);
        intent.putExtra("RESULT", report);
        _context.startActivity(intent);
    }
}