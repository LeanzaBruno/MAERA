package com.maera;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MainActivity extends AppCompatActivity {

    private EditText _codeEditText;
    private Button _searchButton;
    private TextView _resultTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       _codeEditText = findViewById(R.id.code);
       _searchButton = findViewById(R.id.search);
       _resultTextView = findViewById(R.id.result);

        setUpEvents();
    }


    private void setUpEvents(){
        _searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parseHTML();
            }
        });
    }

    private void parseHTML()
    {
        new PageDownloader().execute();
    }



    private class PageDownloader extends AsyncTask<Void, Void, Void>
    {

        private final String METAR_PAGE_PREFIX = "https://ssl.smn.gob.ar/mensajes/index.php?observacion=metar&operacion=consultar&tipoEstacion=OACI&CODIGO_FIR=-1&CODIGO=SAAV+SAAP+SACO";
        private ProgressDialog _progressDialog;
        StringBuilder result = new StringBuilder();

        @Override
        protected void onPreExecute(){
        }

        @Override
        protected Void doInBackground(Void... codes) {

            try {
                Document page = Jsoup.connect(METAR_PAGE_PREFIX).get();
                Elements elements = page.select("input[type=hidden]");

                for( Element element : elements )
                    result.append("Text: ").append(element.text()).append(element.attr("value")).append("\n\n");
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
            Toast.makeText(MainActivity.this, "Task was cancelled!", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            _resultTextView.setText(result);
        }

    }
}
