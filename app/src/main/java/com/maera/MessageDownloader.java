package com.maera;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.lang.ref.WeakReference;

class MessageDownloader extends AsyncTask<Void, Void, String>
{
    private Fragment            _fragment;
    private WeakReference<Activity> _currentActivity;
    private StringBuilder _finalURL = new StringBuilder(Message.commonURL);

    MessageDownloader(String codes, Message message, Fragment baseFragment) {
        _finalURL.append( message.getUrl() ).append(codes);
        _fragment = baseFragment;
        _currentActivity = new WeakReference<>((Activity)_fragment.getActivity());
    }


    @Override
    protected void onPreExecute(){
        Toast.makeText(_currentActivity.get(), "Cargando...", Toast.LENGTH_SHORT).show();
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
        Intent intent = new Intent(_currentActivity.get(), ResultActivity.class);
        intent.putExtra("RESULT", result);
        _currentActivity.get().startActivity(intent);
    }
}
