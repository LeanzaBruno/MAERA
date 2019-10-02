package com.maera;

import androidx.appcompat.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RadioButton _metarBtn, _tafBtn, _pronareaBtn;
    private Button _getBtn;
    private ArrayList<CheckBox> _checkBoxes;
    private TextView _resultTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       setUpViews();
       setUpEvents();
    }

    private void setUpViews()
    {
        _metarBtn = findViewById(R.id.metar);
        _tafBtn = findViewById(R.id.taf);
        _pronareaBtn = findViewById(R.id.pronarea);
        _getBtn = findViewById(R.id.get);

        _resultTextView = findViewById(R.id.result);

        _checkBoxes = new ArrayList<>();
        _checkBoxes.add((CheckBox)findViewById(R.id.sabe));
        _checkBoxes.add((CheckBox)findViewById(R.id.saav));
        _checkBoxes.add((CheckBox)findViewById(R.id.saar));
        _checkBoxes.add((CheckBox)findViewById(R.id.satr));
        _checkBoxes.add((CheckBox)findViewById(R.id.sadf));
    }


    private void setUpEvents(){
        _metarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setMetarFragment();
            }
        });

        _tafBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTafFragment();
            }
        });

        _pronareaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPronareaFragment();
            }
        });

        _getBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getMessage();
            }
        });
    }

    @org.jetbrains.annotations.NotNull
    private String getCodes()
    {
        StringBuilder builder = new StringBuilder();
        for( CheckBox checkBox : _checkBoxes )
            if( checkBox.isChecked() )
                builder.append(checkBox.getText()).append("+");
        return builder.toString();
    }

    private void getMessage()
    {
        if( _metarBtn.isChecked() )
            new MessageDownloader( getCodes(), Message.METAR ).execute();
        else if( _tafBtn.isChecked() )
            new MessageDownloader( getCodes(), Message.TAF ).execute();
        else
            new MessageDownloader( getCodes(), Message.PRONAREA ).execute();
    }

    private void setMetarFragment()
    {

    }

    private void getTafFragment()
    {

    }

    private void setPronareaFragment()
    {

    }



    final private class MessageDownloader extends AsyncTask<Void, Void, String>
    {
        private StringBuilder _finalURL = new StringBuilder(Message.commonURL);

        MessageDownloader(String codes, Message message)
        {
            _finalURL.append( message.getUrl() ).append(codes);
        }


        @Override
        protected void onPreExecute(){

        }

        @Override
        protected String doInBackground(Void... codes) {
            try {
                Document page = Jsoup.connect(_finalURL.toString()).get();
//                Elements elements = page.select("input[type=hidden]");
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
            _resultTextView.setText(result);
        }
    }
}
