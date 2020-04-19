package com.example.rentacar.Aktivnosti;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.rentacar.Adapteri.AutomobilViewAdapter;
import com.example.rentacar.Adapteri.KomentarViewAdapter;
import com.example.rentacar.Modeli.KomentarItemModel;
import com.example.rentacar.R;
import com.google.android.gms.maps.MapView;

import java.util.ArrayList;
import java.util.Date;

public class DetailViewActivity extends AppCompatActivity {

    private ImageView slika;

    private TextView naslov;

    private TextView brojSjedista;

    private TextView brojVrata;

    private TextView kubikaza;

    private TextView tipMotora;

    private TextView snagaMotora;

    private TextView boja;

    private TextView godiste;

    private TextView kilometraza;

    private TextView cijenaPoDanu;

    private Button iznajmi;

    private CheckBox omiljeni;

    private RatingBar ocjena;

    private TextView firma;

    private TextView opis;

    private TextView grad;

    private TextView adresa;

    private TextView email;

    private TextView brojTelefona;

    private MapView mapa;

    private MultiAutoCompleteTextView tekstKomentara;

    private Button posaljiKomentar;

    private ListView listView;

    private KomentarViewAdapter komentarViewAdapter;

    private ArrayList<KomentarItemModel> listaKomentara = new ArrayList<>();

    private String[] imena;

    private String[] prezimena;

    private String[] tekstovi;

    private Date[] datumi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        listView = (ListView)findViewById(R.id.detailViewKomentari);

        //TextView naslov = findViewById(R.id.detailViewNaslov);
        //TextView detalji = findViewById(R.id.detailViewDetalji);

        imena = new String[]{"Marko", "Slavko", "Zivko"};                //ovi nizovi su samo za potrebe testiranja,svi ovi podaci ce se vuci iz baze
        prezimena = new String[]{"Petovic", "Minic", "Spajic"};
        tekstovi = new String[]{"Odlicna kola", "Krs", "Posto su?"};
        datumi = new Date[]{new Date(), new Date(), new Date()};

        for (int i = 0; i < imena.length; i++) {
            KomentarItemModel model = new KomentarItemModel(1, 1, imena[i], prezimena[i], tekstovi[i], datumi[i]);
            listaKomentara.add(model);
        }

        komentarViewAdapter=new KomentarViewAdapter(this, listaKomentara); //samo za potrebe testiranja posto jos nemamo bazu
        listView.setAdapter(komentarViewAdapter);

        Intent intent = getIntent();
        String textNaslov = intent.getStringExtra("naslov");
        String textDetalji = intent.getStringExtra("detalji");

        actionBar.setTitle("Detalji");
        //naslov.setText(textNaslov);
        //detalji.setText(textDetalji);

        iznajmi = findViewById(R.id.detailViewIznajmiButton);
        iznajmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RentActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        posaljiKomentar = findViewById(R.id.detailViewDodajKomentarBtn);
        posaljiKomentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ovde se izvrsava upit nad bazom
                /*Intent intent = new Intent(getApplicationContext(), RentActivity.class);
                startActivityForResult(intent, 0);*/
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent myIntent = new Intent(getApplicationContext(), MasterViewActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }
}
