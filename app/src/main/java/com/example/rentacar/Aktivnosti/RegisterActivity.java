package com.example.rentacar.Aktivnosti;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.rentacar.R;

public class RegisterActivity extends AppCompatActivity {

    private int id;

    private EditText ime;

    private EditText prezime;

    private EditText email;

    private EditText brojTelefona;

    private EditText jmbg;

    private EditText lozinka;

    private Button submitRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        /*ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Registracija");*/

        ime = (EditText) findViewById(R.id.imeUnos);
        prezime = (EditText) findViewById(R.id.prezimeUnos);
        email = (EditText) findViewById(R.id.emailUnosRegister);
        lozinka= (EditText) findViewById(R.id.lozinkaUnosRegister);
        brojTelefona = (EditText) findViewById(R.id.telefonUnosRegister);
        jmbg = (EditText) findViewById(R.id.jmbgUnos);
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
