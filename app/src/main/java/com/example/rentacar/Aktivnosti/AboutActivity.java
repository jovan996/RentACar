package com.example.rentacar.Aktivnosti;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.rentacar.R;

public class AboutActivity extends AppCompatActivity {

    private TextView appIme;

    private TextView appVerzija;

    private TextView datumInstalacije;

    private TextView appMemorija;

    private ListView appDozvole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        /*ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("O aplikaciji");*/

        appVerzija= (TextView) findViewById(R.id.brojVerzije);
        datumInstalacije = (TextView) findViewById(R.id.appDatumInstalacije);
        appMemorija = (TextView) findViewById(R.id.appMemorija);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent myIntent = new Intent(getApplicationContext(), SettingsActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }

    public TextView getAppIme() {
        return appIme;
    }

    public void setAppIme(TextView appIme) {
        this.appIme = appIme;
    }

    public TextView getAppVerzija() {
        return appVerzija;
    }

    public void setAppVerzija(TextView appVerzija) {
        this.appVerzija = appVerzija;
    }

    public TextView getDatumInstalacije() {
        return datumInstalacije;
    }

    public void setDatumInstalacije(TextView datumInstalacije) {
        this.datumInstalacije = datumInstalacije;
    }

    public TextView getAppMemorija() {
        return appMemorija;
    }

    public void setAppMemorija(TextView appMemorija) {
        this.appMemorija = appMemorija;
    }

    public ListView getAppDozvole() {
        return appDozvole;
    }

    public void setAppDozvole(ListView appDozvole) {
        this.appDozvole = appDozvole;
    }
}
