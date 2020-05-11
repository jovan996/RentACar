package com.example.rentacar.Aktivnosti;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.rentacar.Adapteri.AutomobilViewAdapter;
import com.example.rentacar.Adapteri.KomentarViewAdapter;
import com.example.rentacar.BazaPodataka.DatabaseHelper;
import com.example.rentacar.Modeli.FirmaModel;
import com.example.rentacar.Modeli.KomentarItemModel;
import com.example.rentacar.R;
import com.example.rentacar.utils.DrawerUtil;
import com.example.rentacar.utils.Session;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;

public class DetailViewActivity extends AppCompatActivity implements OnMapReadyCallback {

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

    private Session sesija;

    private DatabaseHelper db;

    GoogleMap mapAPI;

    SupportMapFragment mapFragment;

    private boolean flagOmiljeni;

    @Nullable
    @BindView(R.id.detailToolbar)
    public Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);

        toolbar = (Toolbar) findViewById(R.id.detailToolbar);
        toolbar.setTitle(R.string.detalji_automobila);
        setSupportActionBar(toolbar);

        db = new DatabaseHelper(this);


        /* ZA BACK DUGME */
        /*if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }*/

        Session sesija = Session.getInstance(this);
        DrawerUtil.getDrawer(this, toolbar, sesija);
        db = new DatabaseHelper(this);

        listView = (ListView)findViewById(R.id.detailViewKomentari);
        slika=(ImageView) findViewById(R.id.detailViewGlavnaSlika);
        naslov =(TextView) findViewById(R.id.detailViewNaslov);
        brojSjedista = (TextView) findViewById(R.id.detailViewBrojSedista);
        brojVrata = (TextView) findViewById(R.id.detailViewBrojVrata);
        kubikaza = (TextView) findViewById(R.id.detailViewKubikaza);
        tipMotora= (TextView) findViewById(R.id.detailViewMotor);
        snagaMotora = (TextView) findViewById(R.id.detailViewSnagaMotora);
        boja= (TextView) findViewById(R.id.detailViewBoja);
        godiste= (TextView) findViewById(R.id.detailViewGodiste);
        kilometraza = (TextView) findViewById(R.id.detailViewKilometraza);
        cijenaPoDanu = (TextView) findViewById(R.id.detailViewCijeniPoDanu);
        omiljeni = (CheckBox) findViewById(R.id.detailViewCekirajOmiljeni);
        ocjena= (RatingBar) findViewById(R.id.detailViewOznaciZvjezdicu);
        firma = (TextView) findViewById(R.id.detailViewImeFirme);
        opis = (TextView) findViewById(R.id.detailViewOpisFirme);
        adresa = (TextView) findViewById(R.id.detailViewFirmaAdresa);
        grad = (TextView) findViewById(R.id.detailViewFirmaGrad);
        email= (TextView) findViewById(R.id.detailViewFirmaEmail);
        brojTelefona = (TextView) findViewById(R.id.brojTelefona);

        tekstKomentara = (MultiAutoCompleteTextView) findViewById(R.id.detailUnesiKomentar);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapAPI);
        mapFragment.getMapAsync(this);


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
        int id = intent.getIntExtra("faId",0);

        if(!sesija.getKorisnikId().equals("") && sesija.getKorisnikId()!=null){
            flagOmiljeni = db.daLiJeOmiljeni(Integer.parseInt(sesija.getKorisnikId()), id);

            if(flagOmiljeni)
                omiljeni.setChecked(true);
            else
                omiljeni.setChecked(false);
        }
        ocjena.setRating(db.uzmiOcjenu());


        iznajmi = findViewById(R.id.detailViewIznajmiButton);
        iznajmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!sesija.getKorisnikId().equals("") && sesija.getKorisnikId()!=null) {
                    Intent intent = new Intent(getApplicationContext(), RentActivity.class);
                    intent.putExtra("faId", id);
                    startActivityForResult(intent, 0);
                }
                else {
                    Toast.makeText(DetailViewActivity.this, R.string.poruka_mora_se_logovati,Toast.LENGTH_LONG).show();
                }
            }
        });

        ocjena.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if(fromUser){
                    if (!sesija.getKorisnikId().equals("") && sesija.getKorisnikId()!=null) {
                        boolean b = db.provjeriDaLiJeIznajmio(Integer.parseInt(sesija.getKorisnikId()), id);
                        if(b){
                            int a = 1;
                            if(rating >= 4)
                                a = 5;
                            if(rating >= 3 && rating < 4)
                                a = 4;
                            if(rating >= 2 && rating < 3)
                                a = 3;
                            if(rating >= 1 && rating < 2)
                                a = 2;
                            if(rating >=0 && rating < 1)
                                a = 1;
                            db.ocijeniAutomobil(a, Integer.parseInt(sesija.getKorisnikId()), id);
                            ocjena.setRating(db.uzmiOcjenu());
                        }else{
                            ocjena.setRating(db.uzmiOcjenu());
                            Toast.makeText(DetailViewActivity.this, R.string.poruka_niste_iznajmili,Toast.LENGTH_LONG).show();
                        }
                    }
                    else {
                        ocjena.setRating(db.uzmiOcjenu());
                        Toast.makeText(DetailViewActivity.this, R.string.poruka_mora_se_logovati,Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        omiljeni.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if(isChecked){
                    if (!sesija.getKorisnikId().equals("") && sesija.getKorisnikId()!=null) {
                        db.dodajOmiljeni(Integer.parseInt(sesija.getKorisnikId()), id);
                    }
                    else {
                        Toast.makeText(DetailViewActivity.this, R.string.poruka_mora_se_logovati,Toast.LENGTH_LONG).show();
                        omiljeni.setChecked(false);
                    }
                }else{
                    if (!sesija.getKorisnikId().equals("") && sesija.getKorisnikId()!=null) {
                        db.obrisiOmiljeni(Integer.parseInt(sesija.getKorisnikId().toString()), id);
                    }
                }
            }
        });

        posaljiKomentar = findViewById(R.id.detailViewDodajKomentarBtn);
        posaljiKomentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ovdje se izvrsava upit nad bazom

            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent myIntent = new Intent(getApplicationContext(), MasterViewActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        Intent intent= getIntent();

        FirmaModel firma = db.getFirmaById(intent.getIntExtra("faId", 0));

        mapAPI = googleMap;

        if (firma != null){
            if (firma.getMapaLat() != 0 && firma.getMapaLong() != 0){
                LatLng geo = new LatLng(firma.getMapaLat(),firma.getMapaLong());
                mapAPI.addMarker(new MarkerOptions().position(geo));
                mapAPI.moveCamera(CameraUpdateFactory.newLatLngZoom(geo,10));
            }
            else {
                LatLng Gacko = new LatLng(43.166002, 18.535319);

                mapAPI.addMarker(new MarkerOptions().position(Gacko).title("Gacko"));
                mapAPI.moveCamera(CameraUpdateFactory.newLatLngZoom(Gacko,10));
            }
        } else {
            LatLng Gacko = new LatLng(43.166002, 18.535319);

            mapAPI.addMarker(new MarkerOptions().position(Gacko).title("Gacko"));
            mapAPI.moveCamera(CameraUpdateFactory.newLatLngZoom(Gacko, 10));
        }
    }
}
