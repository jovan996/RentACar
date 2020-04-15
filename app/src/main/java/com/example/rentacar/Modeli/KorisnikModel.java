package com.example.rentacar.Modeli;

public class KorisnikModel {

    private int korisnikId;

    private String ime;

    private String prezime;

    private String email;

    private String brojTelefona;

    private String jmbg;

    private String lozinka;

    private String salt;

    public KorisnikModel(){

    }

    public KorisnikModel(int korisnikId, String ime, String prezime, String email, String brojTelefona, String jmbg, String lozinka, String salt) {
        this.korisnikId = korisnikId;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.brojTelefona = brojTelefona;
        this.jmbg = jmbg;
        this.lozinka = lozinka;
        this.salt = salt;
    }

    public int getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(int korisnikId) {
        this.korisnikId = korisnikId;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
