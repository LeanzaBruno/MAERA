package com.maera.fragment;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.maera.R;
import com.maera.core.FIR;
import com.maera.core.WeatherReport;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public final class PronareaFragment extends Fragment {
    private List<FIR> _firs;
    private RecyclerView _list;
    private RecyclerAdapter _adapter;

    public PronareaFragment(){
        _firs = new ArrayList<>();
        _firs.add(FIR.EZE);
        _firs.add(FIR.CBA);
        _firs.add(FIR.DOZ);
        _firs.add(FIR.SIS);
        _firs.add(FIR.CRV);
        _firs.add(FIR.ANT);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle data) {
        View view = inflater.inflate(R.layout.fragment_pronarea, container, false);

        _list = view.findViewById(R.id.list);
        _list.setLayoutManager(new LinearLayoutManager(getContext()));
        _adapter = new RecyclerAdapter();
        _list.setAdapter(_adapter);
        return view;
    }

    private class PronareaDownloader extends AsyncTask<Void, Void, Void> {
        private static final String CSS_QUERY = "td[width=\"100%\"]";
        private ProgressDialog _dialog;
        private TextView _textView;
        private StringBuilder _URL = new StringBuilder();
        private String _result;

        PronareaDownloader(FIR fir, TextView textView){
            _textView = textView;
            _URL.append(WeatherReport.PRONAREA.generateURL(fir));
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
            try {
                Document page = Jsoup.connect(_URL.toString()).get();
                final Elements query = page.select(CSS_QUERY);
                final Element element = query.first();
                if(element != null)
                    _result = element.text();
            }catch(Exception e){e.printStackTrace();}
            return null;
        }

        @Override
        protected void onPostExecute(Void avoid){
            _textView.setText(_result);
            _textView.setVisibility(View.VISIBLE);
            _dialog.dismiss();
        }
    }


    private class RecyclerAdapter extends RecyclerView.Adapter<FIRViewHolder>{

        @NonNull @Override
        public FIRViewHolder onCreateViewHolder(@NonNull ViewGroup container, final int viewType){
            final View view = LayoutInflater.from(container.getContext()).inflate(R.layout.cardview_fir, container, false);
            return new FIRViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull FIRViewHolder viewHolder, int position){
            final FIR fir = _firs.get(position);
            viewHolder._code.setText(fir.name());
            viewHolder._name.setText(fir.toString());
        }

        @Override
        public int getItemCount() {
            return _firs.size();
        }


    }
    private class FIRViewHolder extends RecyclerView.ViewHolder{
        private TextView _code, _name, _result;
        FIRViewHolder(View view){
            super(view);
            _code = view.findViewById(R.id.codes);
            _name = view.findViewById(R.id.name);
            _result = view.findViewById(R.id.result);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   PronareaDownloader task = new PronareaDownloader(_firs.get(getAdapterPosition()), _result);
                   task.execute();

                }
            });
        }
    }


}
