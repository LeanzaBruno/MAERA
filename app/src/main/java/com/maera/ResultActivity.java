package com.maera;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public final class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_result);


        RecyclerView recyclerView = findViewById(R.id.result);
        recyclerView.setLayoutManager( new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        Intent intent = getIntent();
        final ArrayList<Airport> airports = intent.getParcelableArrayListExtra("RESULT");
        String type = intent.getStringExtra("TYPE");
        if( airports != null )
            recyclerView.setAdapter( new ResultListAdapter(airports, type));
        else
            Toast.makeText(this, "Error: No airport(s) found!", Toast.LENGTH_SHORT).show();

    }

}
