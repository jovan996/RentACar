package com.example.rentacar.Modeli;

public class OcjenaModel {

    private int ocjenaId;

    private int korisnikId;

    private int faId;

    private int ocjenaBroj;

    public OcjenaModel(){

    }

    public OcjenaModel(int ocjenaId, int korisnikId, int faId, int ocjenaBroj) {
        this.ocjenaId = ocjenaId;
        this.korisnikId = korisnikId;
        this.faId = faId;
        this.ocjenaBroj = ocjenaBroj;
    }

    public int getOcjenaId() {
        return ocjenaId;
    }

    public void setOcjenaId(int ocjenaId) {
        this.ocjenaId = ocjenaId;
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

    public int getOcjenaBroj() {
        return ocjenaBroj;
    }

    public void setOcjenaBroj(int ocjenaBroj) {
        this.ocjenaBroj = ocjenaBroj;
    }
}
