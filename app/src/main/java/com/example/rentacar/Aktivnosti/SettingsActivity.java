package com.example.rentacar.Aktivnosti;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import com.example.rentacar.Aktivnosti.MasterViewActivity;

import com.example.rentacar.R;
import com.example.rentacar.utils.DrawerUtil;
import com.example.rentacar.utils.Session;

import java.util.Locale;

import butterknife.BindView;


public class SettingsActivity extends AppCompatActivity {

    private Switch promjenaTeme;

    private TextView detaljiAplikacije;

    private Spinner spinner;

    public Toolbar toolBar;

    private Boolean initialDisplay = true;

    private Session sesija;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoadLocale();
        setContentView(R.layout.activity_settings);
        setSpinnerToLanguage();
        /*ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Podesavanja");*/

        toolBar = (Toolbar) findViewById(R.id.settingsToolbar);
        toolBar.setTitle(R.string.podesavanja);
        setSupportActionBar(toolBar);


        sesija = Session.getInstance(this);
        //DrawerUtil.getDrawer(this, toolBar, sesija);

        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();

        promjenaTeme = (Switch) findViewById(R.id.settingPromijeniTemu);
        promjenaTeme.setChecked(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_NO);

//        getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY);

        promjenaTeme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    editor.putInt("theme",AppCompatDelegate.MODE_NIGHT_NO);
                    editor.apply();
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editor.putInt("theme",AppCompatDelegate.MODE_NIGHT_YES);
                    editor.apply();
                }
            }
        });

        initialDisplay = true;
        spinner = (Spinner) findViewById(R.id.izaberiJezik);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if(initialDisplay){
                    initialDisplay = false;
                }else{
                    if(position == 0){
                        Locale l =Locale.getDefault();
                        setLocale("sr");
//                        recreate();
                        finish();
                        overridePendingTransition( 0, 0);
                        startActivity(getIntent());
                        overridePendingTransition( 0, 0);
                        Toast.makeText(getApplicationContext(), "Srpski!",
                                Toast.LENGTH_SHORT).show();



                    }else if(position == 1){
                        Locale l =Locale.getDefault();

                        setLocale("en");
//                         recreate();
                        finish();
                        overridePendingTransition( 0, 0);
                        startActivity(getIntent());
                        overridePendingTransition( 0, 0);
                        Toast.makeText(getApplicationContext(), "English!",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });
        detaljiAplikacije = (TextView) findViewById(R.id.oAplikacijiNaslov);

        detaljiAplikacije.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), AboutAppActivity.class);
                startActivity(myIntent);

            }
        });
    }

    private void setLocale(String sr) {
        Locale locale = new Locale(sr);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_Lang", sr);
        editor.putBoolean("Update", true);
        editor.putBoolean("update", true);
        editor.apply();
    }

    public void LoadLocale(){
        SharedPreferences prefs = getSharedPreferences("Settings", MODE_PRIVATE);
        String language = prefs.getString("My_Lang", "");
        setLocale(language);
    }

    public void setSpinnerToLanguage() {
        spinner = (Spinner) findViewById(R.id.izaberiJezik);
        SharedPreferences prefs = getSharedPreferences("Settings", MODE_PRIVATE);
        String language = prefs.getString("My_Lang", "");
        if(language.equals("en")){
            String compareValue = "English";
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.language_arrays, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            if (compareValue != null) {
                int spinnerPosition = adapter.getPosition(compareValue);
                spinner.setSelection(spinnerPosition);
            }

        }
        //MasterViewActivity.update();
    }



    public boolean onOptionsItemSelected(MenuItem item) {
        Intent myIntent = new Intent(getApplicationContext(), MasterViewActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }
}
