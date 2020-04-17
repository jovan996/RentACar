package com.example.rentacar.Aktivnosti;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.rentacar.Adapteri.AutomobilViewAdapter;
import com.example.rentacar.Modeli.AutomobilItemModel;
import com.example.rentacar.R;

import java.util.ArrayList;

public class MasterViewActivity  extends AppCompatActivity {

    private ListView listView;

    private AutomobilViewAdapter automobilViewAdapter;

    private ArrayList<AutomobilItemModel> listaAutomobila=new ArrayList<>();

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

        listView=(ListView) findViewById(R.id.listaAutomobila);

        identifikatori=new int[]{1,2,3};                      //ovi nizovi su samo za potrebe testiranja kao i for petlja
        marke=new String[]{"audi","honda","mercedes"};
        modeli=new String[]{"a6","civic","s300"};
        cijene=new int[]{3400,765,987};
        slike=new String[]{"R.drawable.slika1","R.drawable.slika2","R.drawable.slika3"};

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
}
