package com.example.rentacar.Aktivnosti;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;


import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.rentacar.Adapteri.AutomobilViewAdapter;
import com.example.rentacar.BazaPodataka.DatabaseHelper;
import com.example.rentacar.Modeli.AutomobilItemModel;
import com.example.rentacar.Modeli.KomentarDTO;
import com.example.rentacar.Modeli.OcjenaModel;
import com.example.rentacar.R;
import com.example.rentacar.utils.DrawerUtil;
import com.example.rentacar.utils.Session;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MasterViewActivity  extends AppCompatActivity {

    private ListView listView;

    private AutomobilViewAdapter automobilViewAdapter;

    private ArrayList<AutomobilItemModel> listaAutomobila = new ArrayList<>();

    private AppBarConfiguration mAppBarConfiguration;

    private int[] identifikatori;

    private String[] marke;

    private String[] modeli;

    private int[] cijene;

    private String[] slike;

    private DatabaseHelper db;

    private Session sesija;

    @Nullable
    @BindView(R.id.drawerToolbar)
    public Toolbar toolBar;

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = getSharedPreferences("Settings", MODE_PRIVATE);
        Boolean update = prefs.getBoolean("update", false);
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();

        if (update) {
            editor.putBoolean("update", false);
            editor.apply();
            recreate();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoadLocale();
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);
        SQLiteDatabase database = db.getWritableDatabase();

        Task task=new Task();
        task.execute();
        //SQLiteDatabase database = db.getReadableDatabase();

        ButterKnife.bind(this);

        toolBar.setTitle(getResources().getString(R.string.tournament));
        setSupportActionBar(toolBar);

        SharedPreferences prefs = getSharedPreferences("Settings", MODE_PRIVATE);
        AppCompatDelegate.setDefaultNightMode(prefs.getInt("theme",1));

        sesija = Session.getInstance(this);

        DrawerUtil.getDrawer(this, toolBar, sesija);

        listView = findViewById(R.id.listaAutomobila);

        /*identifikatori=new int[]{1,2,3,4,5};                      //ovi nizovi su samo za potrebe testiranja kao i for petlja
        marke=new String[]{"audi","honda","mercedes","ferarri","citroen"};
        modeli=new String[]{"a6","civic","s300","k67","c4"};
        cijene=new int[]{3400,765,987,45,789};
        slike=new String[]{"R.drawable.slika1","R.drawable.slika2","R.drawable.slika3","R.drawable.slika4","R.drawable.slika5"};

        for(int i=0;i<identifikatori.length;i++){
            AutomobilItemModel a=new AutomobilItemModel(identifikatori[i],marke[i],modeli[i],cijene[i],slike[i]);
            listaAutomobila.add(a);
        }*/

        //db.insertAutomobil("audi", "a5", 4, 5, 2000, "benzin", 150);

        listaAutomobila = db.getAutomobili();

        automobilViewAdapter = new AutomobilViewAdapter(this, filtriraj(listaAutomobila));
        listView.setAdapter(automobilViewAdapter);

        //db.obrisiBazu(this);

    }

    public boolean onCreateOptionsMenu(Menu meni){
        getMenuInflater().inflate(R.menu.menu,meni);
        MenuItem search=meni.findItem(R.id.pretraga);
        SearchView sv=(SearchView) search.getActionView();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                automobilViewAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    public void LoadLocale(){
        SharedPreferences prefs = getSharedPreferences("Settings", MODE_PRIVATE);
        String language = prefs.getString("My_Lang", "");
        setLocale(language);
    }
    private void setLocale(String sr) {
        Locale locale = new Locale(sr);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_Lang", sr);
        editor.apply();
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }


    private class Task extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            String ip="192.168.1.8";
            Boolean s1=false;
            Boolean s2=false;
            ArrayList<KomentarDTO> komentari = db.getSviKomentari();
            String url= "http://"+ip+":11000/syncKomentari";
            RestTemplate restTemplate = new RestTemplate();
            try {
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                HttpHeaders headers = new HttpHeaders();
                headers.add("Content-Type", "application/json");
                HttpEntity<ArrayList<KomentarDTO>> entity = new HttpEntity<ArrayList<KomentarDTO>>(komentari, headers);
                ResponseEntity<KomentarDTO> response = restTemplate.exchange(url, HttpMethod.POST, entity, KomentarDTO.class);
                HttpStatus status = response.getStatusCode();
                if (status == HttpStatus.OK) {
                    s1=true;
                }
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
                MasterViewActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MasterViewActivity.this, R.string.sync_neuspjesno,Toast.LENGTH_SHORT).show();
                    }
                });
                return null;
            }

            ArrayList<OcjenaModel> ocjene = db.getSveOcjene();
            url= "http://"+ip+":11000/syncOcjene";
            try {
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                HttpHeaders headers = new HttpHeaders();
                headers.add("Content-Type", "application/json");
                HttpEntity<ArrayList<OcjenaModel>> entity = new HttpEntity<ArrayList<OcjenaModel>>(ocjene, headers);
                ResponseEntity<OcjenaModel> response = restTemplate.exchange(url, HttpMethod.POST, entity, OcjenaModel.class);
                HttpStatus status = response.getStatusCode();
                if (status == HttpStatus.OK) {
                    s2=true;

                }
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
                MasterViewActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MasterViewActivity.this, R.string.sync_neuspjesno,Toast.LENGTH_SHORT).show();
                    }
                });
                return null;
            }
            if (s1 && s2){
                MasterViewActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MasterViewActivity.this, R.string.sync_uspjesno,Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                MasterViewActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MasterViewActivity.this, R.string.sync_neuspjesno,Toast.LENGTH_SHORT).show();
                    }
                });
            }
            return null;

        }
    }

    private ArrayList<AutomobilItemModel> filtriraj(ArrayList<AutomobilItemModel> lista){
        ArrayList<AutomobilItemModel> pom= new ArrayList<AutomobilItemModel>();

        for(AutomobilItemModel a : lista){
            boolean t=false;
            for(AutomobilItemModel b : pom) {
                if (a.getFaId()==b.getFaId())
                    t=true;
            }
            if(!t)
                pom.add(a);
        }
        return pom;
    }
}
