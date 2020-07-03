package com.example.rentacar.Aktivnosti;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
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
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.rentacar.Adapteri.AutomobilViewAdapter;
import com.example.rentacar.Adapteri.KomentarViewAdapter;
import com.example.rentacar.Adapteri.SlideAdapter;
import com.example.rentacar.BazaPodataka.DatabaseHelper;
import com.example.rentacar.Modeli.AutomobilItemModel;
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

import java.text.ParseException;
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

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);

        Intent intent = getIntent();
        int id = intent.getIntExtra("faId",0);

        toolbar = (Toolbar) findViewById(R.id.detailToolbar);
        toolbar.setTitle(R.string.detalji_automobila);
        setSupportActionBar(toolbar);

        db = new DatabaseHelper(this);

        ArrayList<AutomobilItemModel> lista = db.getAutomobili();
        ArrayList<Integer> slike = new ArrayList<Integer>();

        for (AutomobilItemModel a : lista){
            if (a.getFaId()==id){
                String putanjaId = a.getSlikaPutanja().replace("R.drawable.", "");
                int idslike = getResources().getIdentifier(putanjaId, "drawable", getPackageName());

                slike.add(idslike);
            }
        }

        ViewPager viewPager = findViewById(R.id.slide);

        SlideAdapter slideAdapter = new SlideAdapter(this, slike);

        viewPager.setAdapter(slideAdapter);


        /* ZA BACK DUGME */
        /*if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }*/

        Session sesija = Session.getInstance(this);
        DrawerUtil.getDrawer(this, toolbar, sesija);
        db = new DatabaseHelper(this);

        listView = (ListView)findViewById(R.id.detailViewKomentari);
        //slika=(ImageView) findViewById(R.id.detailViewGlavnaSlika);
        naslov =(TextView) findViewById(R.id.detailViewNaslov);
        brojSjedista = (TextView) findViewById(R.id.detailViewBrojSedista);
        brojVrata = (TextView) findViewById(R.id.detailViewBrojVrata);
        kubikaza = (TextView) findViewById(R.id.detailViewKubikaza);
        tipMotora= (TextView) findViewById(R.id.detailViewMotor);
        snagaMotora = findViewById(R.id.detailViewSnagaMotora);
        boja= (TextView) findViewById(R.id.detailViewBoja);
        godiste= findViewById(R.id.detailViewGodiste);
        kilometraza = (TextView) findViewById(R.id.detailViewKilometraza);
        cijenaPoDanu = (TextView) findViewById(R.id.detailViewCijeniPoDanu);
        omiljeni = (CheckBox) findViewById(R.id.detailViewCekirajOmiljeni);
        ocjena= (RatingBar) findViewById(R.id.detailViewOznaciZvjezdicu);
        firma = (TextView) findViewById(R.id.detailViewImeFirme);
        opis = (TextView) findViewById(R.id.detailViewOpisFirme);
        adresa = (TextView) findViewById(R.id.detailViewFirmaAdresa);
        grad = (TextView) findViewById(R.id.detailViewFirmaGrad);
        email= (TextView) findViewById(R.id.detailViewFirmaEmail);
        brojTelefona = (TextView) findViewById(R.id.detailViewFirmaBrojTelefona);

        tekstKomentara = (MultiAutoCompleteTextView) findViewById(R.id.detailUnesiKomentar);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapAPI);
        mapFragment.getMapAsync(this);


//        imena = new String[]{"Marko", "Slavko", "Zivko"};                //ovi nizovi su samo za potrebe testiranja,svi ovi podaci ce se vuci iz baze
//        prezimena = new String[]{"Petovic", "Minic", "Spajic"};
//        tekstovi = new String[]{"Odlicna kola", "Krs", "Posto su?"};
//        datumi = new Date[]{new Date(), new Date(), new Date()};
//
//        for (int i = 0; i < imena.length; i++) {
//            KomentarItemModel model = new KomentarItemModel(1, 1, imena[i], prezimena[i], tekstovi[i], datumi[i]);
//            listaKomentara.add(model);
//        }


        
        int c = db.setujCijenuPoDanu(id);
        cijenaPoDanu.append(" " + Integer.toString(c));

        //string b = db.setujBoju(id);
        boja.append(" " + db.setujBoju(id));

        int g = db.setujGodiste(id);
        godiste.append(" " + Integer.toString(g));

        int k = db.setujKilometrazu(id);
        kilometraza.append(" " + Integer.toString(k));

        naslov.setText(db.setujImeMarkuAuta(id));

        int brS = db.setujBrSjedista(id);
        brojSjedista.append(" " + Integer.toString(brS));

        int brV = db.setujBrVrata(id);
        brojVrata.append(" " + Integer.toString(brV));

        int kub = db.setujKubikazu(id);
        kubikaza.append(" " + Integer.toString(kub));

        tipMotora.append(" " + db.setujTipMotora(id));

        int snaga = db.setujSnaguMotora(id);
        snagaMotora.append(" " + Integer.toString(snaga));

        firma.append(":"+ " " + db.setujNazivFirme(id));

        opis.setText(db.setujOpisFirme(id));

        grad.append(" " + db.setujGrad(id));

        adresa.append(" " + db.setujAdresu(id));

        email.append(" " + db.setujEmail(id));

        brojTelefona.append(" " + db.setujBrTelefona(id));
        listView= findViewById(R.id.detailViewKomentari);
        listaKomentara = db.getKomentari(id);
        komentarViewAdapter=new KomentarViewAdapter(this, listaKomentara); //samo za potrebe testiranja posto jos nemamo bazu
        listView.setAdapter(komentarViewAdapter);

        if(!sesija.getKorisnikId().equals("") && sesija.getKorisnikId()!=null){
            flagOmiljeni = db.daLiJeOmiljeni(Integer.parseInt(sesija.getKorisnikId()), id);

            if(flagOmiljeni)
                omiljeni.setChecked(true);
            else
                omiljeni.setChecked(false);
        }
        ocjena.setRating(db.uzmiOcjenu(id));


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
                    Toast.makeText(DetailViewActivity.this, R.string.poruka_mora_se_logovati,Toast.LENGTH_SHORT).show();
                }

            }
        });

        ocjena.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if(fromUser){
                    if (!sesija.getKorisnikId().equals("") && sesija.getKorisnikId()!=null) {
                        boolean b = false;
                        try {
                            b = db.provjeriDaLiJeIznajmio(Integer.parseInt(sesija.getKorisnikId()), id);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
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
                            ocjena.setRating(db.uzmiOcjenu(id));
                        }else{
                            ocjena.setRating(db.uzmiOcjenu(id));
                            Toast.makeText(DetailViewActivity.this, R.string.poruka_niste_iznajmili,Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        ocjena.setRating(db.uzmiOcjenu(id));
                        Toast.makeText(DetailViewActivity.this, R.string.poruka_mora_se_logovati,Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(DetailViewActivity.this, R.string.poruka_mora_se_logovati,Toast.LENGTH_SHORT).show();
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
                if (!sesija.getKorisnikId().equals("") && sesija.getKorisnikId()!=null) {
                    boolean b = false;
                    try {
                        b = db.provjeriDaLiJeIznajmio(Integer.parseInt(sesija.getKorisnikId()), id);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if(b){
                        Editable tx = tekstKomentara.getText();
                        String ss = tx.toString();
                        db.dodajKomentarUBazu(ss, "naslov" ,Integer.parseInt(sesija.getKorisnikId()), id);
                        tekstKomentara.setText(R.string.komentar_ostavljanje);
                        finish();
                        overridePendingTransition( 0, 0);
                        startActivity(getIntent());
                        overridePendingTransition( 0, 0);
                    }else{
                        Toast.makeText(DetailViewActivity.this, R.string.poruka_niste_iznajmili,Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(DetailViewActivity.this, R.string.poruka_mora_se_logovati,Toast.LENGTH_SHORT).show();
                    tekstKomentara.setText(R.string.komentar_ostavljanje);
                }
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
