package com.example.rentacar.Aktivnosti;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.rentacar.BazaPodataka.DatabaseHelper;
import com.example.rentacar.R;
import com.example.rentacar.utils.Datum;
import com.example.rentacar.utils.Session;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

import butterknife.BindView;

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

    private DatePicker datumUzimanja;

    private DatePicker datumVracanja;

    private DatabaseHelper db;

    private Session sesija;

    private TextView iznajmiBrojSedista;

    private TextView iznajmiBrojVrata;

    private TextView iznajmiKubikaza;

    private TextView iznajmiTipMotor;

    private TextView iznajmiSnagaMotora;

    private TextView iznajmiBoja;

    private TextView iznajmiGodiste;

    private TextView iznajmiKilometraza;

    private TextView iznajmiCijenaPoDanu;

    @Nullable
    @BindView(R.id.rentToolbar)
    public Toolbar toolBar;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent);

        /*ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Iznajmljivanje automobila");*/

        db = new DatabaseHelper(this);

        sesija = Session.getInstance(this);

        toolBar = (Toolbar) findViewById(R.id.rentToolbar);
        toolBar.setTitle("Iznajmljivanje automobila");
        setSupportActionBar(toolBar);

        Intent intent = getIntent();
        int faId = intent.getIntExtra("faId",0);
        HashMap<String, String> podaci = db.getDetails(faId);

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
        datumUzimanja = (DatePicker) findViewById(R.id.rentDatumUzimanja);
        datumVracanja = (DatePicker) findViewById(R.id.rentDatumVracanja);

        iznajmiBrojSedista = findViewById(R.id.iznajmiBrojSedista);
        iznajmiBrojVrata = findViewById(R.id.iznajmiBrojVrata);
        iznajmiBoja = findViewById(R.id.iznajmiBoja);
        iznajmiGodiste=  findViewById(R.id.iznajmiGodiste);
        iznajmiTipMotor =  findViewById(R.id.iznajmiTipMotor);
        iznajmiSnagaMotora =  findViewById(R.id.iznajmiSnagaMotora);
        iznajmiKilometraza =  findViewById(R.id.iznajmiKilometraza);
        iznajmiKubikaza = findViewById(R.id.iznajmiKubikaza);
        iznajmiCijenaPoDanu = findViewById(R.id.iznajmiCijenaPoDanu);

        rentNaslov.setText(podaci.get("naslov"));
        brojSjedista.setText(iznajmiBrojSedista.getText() + " " + podaci.get("broj_sjedista"));
        brojVrata.setText(iznajmiBrojVrata.getText() + " " + podaci.get("broj_vrata"));
        kubikaza.setText(iznajmiKubikaza.getText() + " " + podaci.get("broj_kubikaza") + " cm3");
        tipMotora.setText(iznajmiTipMotor.getText() + " " + podaci.get("tip_motora"));
        snagaMotora.setText(iznajmiSnagaMotora.getText() + " " + podaci.get("snaga_motora") + " kW");
        boja.setText(iznajmiBoja.getText() + " " + podaci.get("boja"));
        godiste.setText(iznajmiGodiste.getText() + " " + podaci.get("godiste"));
        kilometraza.setText(iznajmiKilometraza.getText() + " " + podaci.get("kilometraza") + " km");
        cijenaPoDanu.setText(iznajmiCijenaPoDanu.getText() + ":" + "  " + podaci.get("cijena") + " €/dan");

        int cijena = Integer.parseInt(podaci.get("cijena"));

        datumVracanja.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                //Toast.makeText(RentActivity.this," You are changed date is : "+dayOfMonth +" -  "+ ++monthOfYear+ " - "+year,Toast.LENGTH_SHORT).show();

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

                Date datum2 = null;
                Date datum1 = null;
                try {
                    datum2 = format.parse(year + "-" + monthOfYear + "-" + dayOfMonth);
                    datum1 = format.parse(datumUzimanja.getYear() + "-" + datumUzimanja.getMonth() + "-" + datumUzimanja.getDayOfMonth());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                ukupnaCijena.setText(ukupnaCijena.getText() + " " + Integer.toString(izracunajUkupnuCijenu(datum1, datum2, cijena)) + "€");
            }
        });

        iznajmiAutomobil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date datum1 = null;
                Date datum2 = null;
                Date datumEvidencije = null;
                String evidencija = format.format(new Date());

                try {
                    datum1 = format.parse(datumUzimanja.getYear() + "-" + datumUzimanja.getMonth() + "-" + datumUzimanja.getDayOfMonth());
                    datum2 = format.parse(datumVracanja.getYear() + "-" + datumVracanja.getMonth() + "-" + datumVracanja.getDayOfMonth());
                    datumEvidencije = format.parse(evidencija);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                db.iznajmiAutomobil(Integer.parseInt(sesija.getKorisnikId()), faId, datumEvidencije, datum1, datum2);
                //db.setStatusAutomobil(faId);   samo podesiti u ovoj metodi da se promijeni status automobila u bazi
                Toast.makeText(RentActivity.this,"Uspjesno ste iznajmili automobil!",Toast.LENGTH_SHORT).show();
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

    public int izracunajUkupnuCijenu(Date datumUzimanja, Date datumVracanja, int cijenaPoDanu) {
        int brojDana = Datum.getBrojDana(datumUzimanja, datumVracanja);
        return brojDana * cijenaPoDanu;
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
