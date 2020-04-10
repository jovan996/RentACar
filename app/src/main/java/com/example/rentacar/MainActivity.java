package com.example.rentacar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listView=(ListView) findViewById(R.id.listaAutomobila);
        setContentView(R.layout.activity_main);
    }
}
