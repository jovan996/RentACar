package com.example.rentacar.Aktivnosti;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.Nullable;
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

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OmiljeniActivity extends AppCompatActivity {
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

    //@Nullable
    //@BindView(R.id.drawerToolbar)
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
        setContentView(R.layout.omiljeni);

        db = new DatabaseHelper(this);
        SQLiteDatabase database = db.getWritableDatabase();

//        MasterViewActivity.Task task=new MasterViewActivity.Task();
//        task.execute();
        //SQLiteDatabase database = db.getReadableDatabase();

        ButterKnife.bind(this);

        toolBar = (Toolbar) findViewById(R.id.omiljeniToolbar);
        toolBar.setTitle(R.string.omiljeni);
        setSupportActionBar(toolBar);

//        toolBar.setTitle(getResources().getString(R.string.tournament));
//        setSupportActionBar(toolBar);

        SharedPreferences prefs = getSharedPreferences("Settings", MODE_PRIVATE);
        AppCompatDelegate.setDefaultNightMode(prefs.getInt("theme",1));

        sesija = Session.getInstance(this);

        DrawerUtil.getDrawer(this, toolBar, sesija);

        listView = findViewById(R.id.listaAutomobila);
        int idKorisnika = Integer.parseInt(sesija.getKorisnikId());
        //db.listaOmiljenih(idKorisnika);

        listaAutomobila = db.listaOmiljenih(idKorisnika);
        //listaAutomobila = db.getAutomobili();

        automobilViewAdapter = new AutomobilViewAdapter(this, listaAutomobila);
        listView.setAdapter(automobilViewAdapter);

        //db.obrisiBazu(this);

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
}

