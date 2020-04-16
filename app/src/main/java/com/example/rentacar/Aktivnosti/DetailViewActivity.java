package com.example.rentacar.Aktivnosti;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.view.MenuItem;

import com.example.rentacar.R;

public class DetailViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //TextView naslov = findViewById(R.id.detailViewNaslov);
        //TextView detalji = findViewById(R.id.detailViewDetalji);

        Intent intent = getIntent();
        String textNaslov = intent.getStringExtra("naslov");
        String textDetalji = intent.getStringExtra("detalji");

        actionBar.setTitle("Detalji");
        //naslov.setText(textNaslov);
        //detalji.setText(textDetalji);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent myIntent = new Intent(getApplicationContext(), MasterViewActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }
}
