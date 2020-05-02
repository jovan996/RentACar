package com.example.rentacar.Aktivnosti;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.rentacar.Adapteri.AutomobilViewAdapter;
import com.example.rentacar.BazaPodataka.DatabaseHelper;
import com.example.rentacar.Modeli.AutomobilItemModel;
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

    private ArrayList<AutomobilItemModel> listaAutomobila=new ArrayList<>();

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoadLocale();
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);
        SQLiteDatabase database = db.getWritableDatabase();

        db.iznajmiAutomobil(1, 1, new Date(), new Date(), new Date());

        //SQLiteDatabase database = db.getReadableDatabase();

        ButterKnife.bind(this);

        toolBar.setTitle(getResources().getString(R.string.tournament));
        setSupportActionBar(toolBar);

        sesija = Session.getInstance(this);

        DrawerUtil.getDrawer(this, toolBar, sesija);

        listView=(ListView) findViewById(R.id.listaAutomobila);

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

        automobilViewAdapter=new AutomobilViewAdapter(this,listaAutomobila);
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

}
