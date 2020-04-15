package com.example.rentacar.Modeli;

public class OmiljeniModel {

    private int omiljeniId;

    private int korisnikId;

    private int faId;

    public OmiljeniModel(){

    }

    public OmiljeniModel(int korisnikId, int faId) {
        this.korisnikId = korisnikId;
        this.faId = faId;
    }

    public int getOmiljeniId() {
        return omiljeniId;
    }

    public void setOmiljeniId(int omiljeniId) {
        this.omiljeniId = omiljeniId;
    }

    public int getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(int korisnikId) {
        this.korisnikId = korisnikId;
    }

    public int getFaId() {
        return faId;
    }

    public void setFaId(int faId) {
        this.faId = faId;
    }
}
