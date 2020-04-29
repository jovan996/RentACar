package com.example.rentacar.Aktivnosti;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.example.rentacar.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RentActivity extends AppCompatActivity {

    private TextView rentNaslov;

    private TextView brojSjedista;

    private TextView brojVrata;

    private TextView kubikaza;

    private TextView tipMotora;

    private TextView snagaMotora;

    private TextView boja;

    private TextView godiste;

    private TextView kilometraza;

    private TextView cijenaPoDanu;

    private TextView ukupnaCijena;

    private Button iznajmiAutomobil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent);

        /*ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Iznajmljivanje automobila");*/

        rentNaslov =(TextView) findViewById(R.id.rentNaslov);
        brojSjedista = (TextView) findViewById(R.id.iznajmiBrojSedista);
        brojVrata = (TextView) findViewById(R.id.iznajmiBrojVrata);
        kubikaza = (TextView) findViewById(R.id.iznajmiKubikaza);
        tipMotora= (TextView) findViewById(R.id.iznajmiTipMotor);
        snagaMotora = (TextView) findViewById(R.id.iznajmiSnagaMotora);
        boja= (TextView) findViewById(R.id.iznajmiBoja);
        godiste= (TextView) findViewById(R.id.iznajmiGodiste);
        kilometraza = (TextView) findViewById(R.id.iznajmiKilometraza);
        cijenaPoDanu = (TextView) findViewById(R.id.iznajmiCijenaPoDanu);
        ukupnaCijena =  (TextView) findViewById(R.id.rentUkupnaCijena);
        iznajmiAutomobil = (Button) findViewById(R.id.rentIznajmi);

        iznajmiAutomobil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MasterViewActivity.class);
                startActivityForResult(intent, 0);
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent myIntent = new Intent(getApplicationContext(), DetailViewActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }

    public TextView getRentNaslov() {
        return rentNaslov;
    }

    public void setRentNaslov(TextView rentNaslov) {
        this.rentNaslov = rentNaslov;
    }

    public TextView getBrojSjedista() {
        return brojSjedista;
    }

    public void setBrojSjedista(TextView brojSjedista) {
        this.brojSjedista = brojSjedista;
    }

    public TextView getBrojVrata() {
        return brojVrata;
    }

    public void setBrojVrata(TextView brojVrata) {
        this.brojVrata = brojVrata;
    }

    public TextView getKubikaza() {
        return kubikaza;
    }

    public void setKubikaza(TextView kubikaza) {
        this.kubikaza = kubikaza;
    }

    public TextView getTipMotora() {
        return tipMotora;
    }

    public void setTipMotora(TextView tipMotora) {
        this.tipMotora = tipMotora;
    }

    public TextView getSnagaMotora() {
        return snagaMotora;
    }

    public void setSnagaMotora(TextView snagaMotora) {
        this.snagaMotora = snagaMotora;
    }

    public TextView getBoja() {
        return boja;
    }

    public void setBoja(TextView boja) {
        this.boja = boja;
    }

    public TextView getGodiste() {
        return godiste;
    }

    public void setGodiste(TextView godiste) {
        this.godiste = godiste;
    }

    public TextView getKilometraza() {
        return kilometraza;
    }

    public void setKilometraza(TextView kilometraza) {
        this.kilometraza = kilometraza;
    }

    public TextView getCijenaPoDanu() {
        return cijenaPoDanu;
    }

    public void setCijenaPoDanu(TextView cijenaPoDanu) {
        this.cijenaPoDanu = cijenaPoDanu;
    }

    public TextView getUkupnaCijena() {
        return ukupnaCijena;
    }

    public void setUkupnaCijena(TextView ukupnaCijena) {
        this.ukupnaCijena = ukupnaCijena;
    }

    public Button getIznajmiAutomobil() {
        return iznajmiAutomobil;
    }

    public void setIznajmiAutomobil(Button iznajmiAutomobil) {
        this.iznajmiAutomobil = iznajmiAutomobil;
    }
}
