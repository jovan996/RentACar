package com.example.rentacar.Aktivnosti;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.rentacar.BazaPodataka.DatabaseHelper;
import com.example.rentacar.R;

public class RegisterActivity extends AppCompatActivity {

    private int id;

    private EditText ime;

    private EditText prezime;

    private EditText email;

    private EditText brojTelefona;

    private EditText jmbg;

    private EditText lozinka;

    private CheckBox uslovi;

    private TextView greske;

    private Button submitRegister;

    private DatabaseHelper db;

    //@Nullable
    //@BindView(R.id.detailToolbar)
    public Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        /*ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Registracija");*/

        toolbar = (Toolbar) findViewById(R.id.registerToolbar);
        toolbar.setTitle("Registracija");
        setSupportActionBar(toolbar);

        db = new DatabaseHelper(this);

        ime = (EditText) findViewById(R.id.imeUnos);
        prezime = (EditText) findViewById(R.id.prezimeUnos);
        email = (EditText) findViewById(R.id.emailUnosRegister);
        lozinka= (EditText) findViewById(R.id.lozinkaUnosRegister);
        brojTelefona = (EditText) findViewById(R.id.telefonUnosRegister);
        jmbg = (EditText) findViewById(R.id.jmbgUnos);
        uslovi = (CheckBox) findViewById(R.id.prihvatamUslove);
        greske = (TextView) findViewById(R.id.regGreske);
        submitRegister = (Button) findViewById(R.id.registracija);

        submitRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String imeText = ime.getText().toString();
                String prezimeText = prezime.getText().toString();
                String emailText = email.getText().toString();
                String brojTelefonaText = brojTelefona.getText().toString();
                String jmbgText = jmbg.getText().toString();
                String lozinkaText = lozinka.getText().toString();
                boolean isUsloviPrihvaceni = uslovi.isChecked();

                if (isUsloviPrihvaceni) {
                    String rezultat = db.registracija(imeText, prezimeText, emailText, brojTelefonaText, jmbgText, lozinkaText);
                    if (rezultat == "") {
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        RegisterActivity.this.startActivity(intent);
                    }
                    else {
                        greske.setText("Pogresan format:" + rezultat);
                    }
                }
                else {
                    Toast.makeText(RegisterActivity.this,"Molim vas da prihvatite uslove koriscenja!",Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent myIntent = new Intent(getApplicationContext(), MasterViewActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EditText getIme() {
        return ime;
    }

    public void setIme(EditText ime) {
        this.ime = ime;
    }

    public EditText getPrezime() {
        return prezime;
    }

    public void setPrezime(EditText prezime) {
        this.prezime = prezime;
    }

    public EditText getEmail() {
        return email;
    }

    public void setEmail(EditText email) {
        this.email = email;
    }

    public EditText getBrojTelefona() {
        return brojTelefona;
    }

    public void setBrojTelefona(EditText brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    public EditText getJmbg() {
        return jmbg;
    }

    public void setJmbg(EditText jmbg) {
        this.jmbg = jmbg;
    }

    public EditText getLozinka() {
        return lozinka;
    }

    public void setLozinka(EditText lozinka) {
        this.lozinka = lozinka;
    }

    public Button getSubmitRegister() {
        return submitRegister;
    }

    public void setSubmitRegister(Button submitRegister) {
        this.submitRegister = submitRegister;
    }
}
