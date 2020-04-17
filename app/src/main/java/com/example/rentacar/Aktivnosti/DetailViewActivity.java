package com.example.rentacar.Aktivnosti;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.rentacar.Adapteri.AutomobilViewAdapter;
import com.example.rentacar.Adapteri.KomentarViewAdapter;
import com.example.rentacar.Modeli.KomentarItemModel;
import com.example.rentacar.R;

import java.util.ArrayList;
import java.util.Date;

public class DetailViewActivity extends AppCompatActivity {

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
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent myIntent = new Intent(getApplicationContext(), MasterViewActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }
}
