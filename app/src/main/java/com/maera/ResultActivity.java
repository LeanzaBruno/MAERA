package com.maera;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public final class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        TextView _result = findViewById(R.id.result);
        _result.setText( intent.getStringExtra("RESULT"));
    }

}
