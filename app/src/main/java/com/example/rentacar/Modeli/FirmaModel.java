package com.example.rentacar.Modeli;

public class FirmaModel {

    private int id;

    private String ime ;

    private int pib;

    private String opis;

    private String adresa;

    private String grad;

    private String email;

    private int brojTelefona;

    private float mapaLat;

    private float mapaLong;

    public FirmaModel() {

    }

    public FirmaModel(int id, String ime, int pib, String opis, String adresa, String grad, String email, int brojTelefona, float mapaLat, float mapaLong) {
        this.id = id;
        this.ime = ime;
        this.pib = pib;
        this.opis = opis;
        this.adresa = adresa;
        this.grad = grad;
        this.email = email;
        this.brojTelefona = brojTelefona;
        this.mapaLat = mapaLat;
        this.mapaLong = mapaLong;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public int getPib() {
        return pib;
    }

    public void setPib(int pib) {
        this.pib = pib;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getBrojTelefona() {
        return brojTelefona;
    }

    public void setBrojTelefona(int brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    public float getMapaLat() {
        return mapaLat;
    }

    public void setMapaLat(float mapaLat) {
        this.mapaLat = mapaLat;
    }

    public float getMapaLong() {
        return mapaLong;
    }

    public void setMapaLong(float mapaLong) {
        this.mapaLong = mapaLong;
    }
}
