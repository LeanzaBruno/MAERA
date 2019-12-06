package com.maera.fragment;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.google.android.material.tabs.TabLayout;
import com.maera.R;
import com.maera.core.FIR;
import com.maera.core.WeatherReport;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public final class PronareaFragment extends Fragment {
    private TabLayout _tabLayout;
    private TextView _textResult;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle data){
        View view = inflater.inflate(R.layout.fragment_pronarea, container, false);

        _textResult = view.findViewById(R.id.result);
        _tabLayout = view.findViewById(R.id.tabLayout);
        setUpTabLayout();

        return view;
    }

    private void setUpTabLayout(){
        _tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                new PronareaDownloader(tab.getPosition()).execute();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private class PronareaDownloader extends AsyncTask<Void, Void, Void> {
        private ProgressDialog _dialog;
        private int _firSelected;
        private StringBuilder _URL = new StringBuilder();
        private String _result;

        PronareaDownloader(int position){
            _firSelected = position;
        }

        @Override
        protected void onPreExecute(){
            _dialog = new ProgressDialog(getContext());
            _dialog.setCancelable(false);
            _dialog.setMessage("Descargando...");
            _dialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids){
            _URL.append(WeatherReport.TYPE.PRONAREA.getURL());
            switch(_firSelected){
                case 0: _URL.append(FIR.EZE.getCode());break;
                case 1: _URL.append(FIR.CBA.getCode());break;
                case 2: _URL.append(FIR.DOZ.getCode());break;
                case 3: _URL.append(FIR.SIS.getCode());break;
                case 4: _URL.append(FIR.CRV.getCode());break;
                case 5: _URL.append(FIR.ANT.getCode());break;
            }
            _URL.append("=on");
            try{

                Document page = Jsoup.connect(_URL.toString()).get();
                Elements elements = page.select("td[width=\"100%\"]");
                _result = elements.first().text();

            }catch(Exception e){ e.printStackTrace();}
            return null;
        }

        @Override
        protected void onPostExecute(Void avoid){
            _textResult.setText(_result);
            _dialog.dismiss();
        }
    }
}
