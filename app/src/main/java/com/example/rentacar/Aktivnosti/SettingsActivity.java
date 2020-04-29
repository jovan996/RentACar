package com.example.rentacar.Aktivnosti;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.rentacar.R;

import butterknife.BindView;


public class SettingsActivity extends AppCompatActivity {

    private Switch promjenaTeme;

    private TextView detaljiAplikacije;


    public Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        /*ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Podesavanja");*/

        toolBar = (Toolbar) findViewById(R.id.settingsToolbar);
        toolBar.setTitle(R.string.podesavanja);
        setSupportActionBar(toolBar);

        promjenaTeme = (Switch) findViewById(R.id.settingPromijeniTemu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent myIntent = new Intent(getApplicationContext(), MasterViewActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }
}
