package com.example.rentacar.Aktivnosti;

import android.os.Bundle;
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

import java.util.ArrayList;

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

    /*@Nullable
    @BindView(R.id.drawerToolbar)
    public Toolbar toolBar;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);

        /*ButterKnife.bind(this);

        toolBar.setTitle(getResources().getString(R.string.tournament));
        setSupportActionBar(toolBar);

        DrawerUtil.getDrawer(this, toolBar);*/

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Lista automobila");

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

        automobilViewAdapter=new AutomobilViewAdapter(this,listaAutomobila); //samo za potrebe testiranja posto jos nemamo bazu
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

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.podesavanja) {
            // napisati kod za pokretanje nove aktivnosti
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
