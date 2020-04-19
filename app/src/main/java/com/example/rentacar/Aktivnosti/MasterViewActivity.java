package com.example.rentacar.Aktivnosti;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.rentacar.Adapteri.AutomobilViewAdapter;
import com.example.rentacar.Modeli.AutomobilItemModel;
import com.example.rentacar.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Lista automobila");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        @SuppressLint("WrongViewCast") DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_pocetna, R.id.nav_prijava, R.id.nav_registracija, R.id.nav_podesavanja)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        listView=(ListView) findViewById(R.id.listaAutomobila);

        identifikatori=new int[]{1,2,3,4,5};                      //ovi nizovi su samo za potrebe testiranja kao i for petlja
        marke=new String[]{"audi","honda","mercedes","ferarri","citroen"};
        modeli=new String[]{"a6","civic","s300","k67","c4"};
        cijene=new int[]{3400,765,987,45,789};
        slike=new String[]{"R.drawable.slika1","R.drawable.slika2","R.drawable.slika3","R.drawable.slika4","R.drawable.slika5"};

        for(int i=0;i<identifikatori.length;i++){
            AutomobilItemModel a=new AutomobilItemModel(identifikatori[i],marke[i],modeli[i],cijene[i],slike[i]);
            listaAutomobila.add(a);
        }

        automobilViewAdapter=new AutomobilViewAdapter(this,listaAutomobila); //samo za potrebe testiranja posto jos nemamo bazu
        listView.setAdapter(automobilViewAdapter);

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

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
