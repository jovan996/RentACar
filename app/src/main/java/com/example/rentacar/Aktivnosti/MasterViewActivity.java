package com.example.rentacar.Aktivnosti;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.SearchView;

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
        listView=(ListView) findViewById(R.id.listaAutomobila);
        setContentView(R.layout.activity_main);

        identifikatori=new int[]{1,2,3};                      //ovi nizovi su samo za potrebe testiranja
        marke=new String[]{"audi","honda","mercedes"};
        modeli=new String[]{"a6","civic","s300"};
        cijene=new int[]{3400,765,987};
        slike=new String[]{"R.drawable.slika1","R.drawable.slika2","R.drawable.slika3"};

        for(int i=0;i<identifikatori.length;i++){
            AutomobilItemModel a=new AutomobilItemModel(identifikatori[i],marke[i],modeli[i],cijene[i],slike[i]);
            listaAutomobila.add(a);
        }

        automobilViewAdapter=new AutomobilViewAdapter(this,listaAutomobila);
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
                if(TextUtils.isEmpty(newText)){
                    automobilViewAdapter.search("");
                    listView.clearTextFilter();
                }else{
                    automobilViewAdapter.search(newText);
                }
                return true;
            }
        });
        return true;
    }

    public boolean onOptionsItemSelected(){
        return false;
    }
}
