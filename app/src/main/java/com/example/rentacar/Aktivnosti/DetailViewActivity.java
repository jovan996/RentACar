package com.example.rentacar.Aktivnosti;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
import com.example.rentacar.Modeli.KomentarItemModel;
import com.example.rentacar.R;
import com.example.rentacar.utils.DrawerUtil;
import com.example.rentacar.utils.Session;
import com.google.android.gms.maps.MapView;

import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;

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

    private Session sesija;

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

        /* ZA BACK DUGME */
        /*if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }*/

        Session sesija = Session.getInstance(this);

        DrawerUtil.getDrawer(this, toolbar, sesija);

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
        mapa= (MapView) findViewById(R.id.detailMapView);
        tekstKomentara = (MultiAutoCompleteTextView) findViewById(R.id.detailUnesiKomentar);

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
                    Toast.makeText(DetailViewActivity.this,"Molimo vas da se prethodno ulogujete!",Toast.LENGTH_LONG).show();
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
}
