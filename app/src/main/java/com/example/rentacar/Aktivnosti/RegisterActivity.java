package com.example.rentacar.Aktivnosti;

import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity {

    private int id;

    private EditText ime;

    private EditText prezime;

    private EditText email;

    private EditText brojTelefona;

    private EditText jmbg;

    private EditText lozinka;

    private Button submit;

    public RegisterActivity(){

    }

    public RegisterActivity(EditText ime, EditText prezime, EditText email, EditText brojTelefona, EditText jmbg, EditText lozinka, Button submit) {
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.brojTelefona = brojTelefona;
        this.jmbg = jmbg;
        this.lozinka = lozinka;
        this.submit = submit;
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

    public Button getSubmit() {
        return submit;
    }

    public void setSubmit(Button submit) {
        this.submit = submit;
    }
}
