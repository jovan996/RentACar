package com.example.rentacar.Aktivnosti;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.rentacar.R;

import butterknife.BindView;

public class AboutAppActivity extends AppCompatActivity {

    @Nullable
    @BindView(R.id.aboutAppToolbar)
    public Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_application);

        toolBar = (Toolbar) findViewById(R.id.aboutAppToolbar);
        toolBar.setTitle(R.string.o_aplikaciji);
        setSupportActionBar(toolBar);

    }
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent myIntent = new Intent(getApplicationContext(), SettingsActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }
}

