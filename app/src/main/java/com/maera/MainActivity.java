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

import org.w3c.dom.Text;

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
                new PageDownloader().execute(_codeEditText.getText().toString());
            }
        });


    }



    private class PageDownloader extends AsyncTask<String, Integer, Boolean>
    {
        private final String METAR_PAGE_PREFIX = "https://ssl.smn.gob.ar/mensajes/index.php?observacion=metar&operacion=consultar&tipoEstacion=OACI&CODIGO_FIR=-1&CODIGO=";

        private ProgressDialog _progressDialog;

        private void doTask(){
            try {
                Thread.sleep(1000);
            }catch (Exception e){ e.printStackTrace(); }
        }


        @Override
        protected void onPreExecute(){
            _progressDialog = new ProgressDialog(MainActivity.this);
            _progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            _progressDialog.setCancelable(false);
            _progressDialog.show();
        }

        @Override
        protected Boolean doInBackground(String... codes) {

            for( int i = 1; i <= 10 ; ++i )
            {
                doTask();
                publishProgress(i*10);

                if( isCancelled() ) return false;
            }
            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values)
        {
            _progressDialog.setProgress(values[0]);
        }


        @Override
        protected void onCancelled()
        {
            Toast.makeText(MainActivity.this, "Task was cancelled!", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPostExecute(Boolean result)
        {
            if( result )
                Toast.makeText(MainActivity.this, "Task is done!", Toast.LENGTH_SHORT).show();

            _progressDialog.dismiss();
        }

    }
}
