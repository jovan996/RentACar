package com.example.rentacar.Modeli;

public class KomentarItemModel {

    private int komentarId;

    private String korisnikIme;

    private String korisnikPrezime;

    private String komentarTekst;

    public KomentarItemModel(){

    }

    public KomentarItemModel(int komentarId, String korisnikIme, String korisnikPrezime, String komentarTekst) {
        this.komentarId = komentarId;
        this.korisnikIme = korisnikIme;
        this.korisnikPrezime = korisnikPrezime;
        this.komentarTekst = komentarTekst;
    }

    public int getKomentarId() {
        return komentarId;
    }

    public void setKomentarId(int komentarId) {
        this.komentarId = komentarId;
    }

    public String getKorisnikIme() {
        return korisnikIme;
    }

    public void setKorisnikIme(String korisnikIme) {
        this.korisnikIme = korisnikIme;
    }

    public String getKorisnikPrezime() {
        return korisnikPrezime;
    }

    public void setKorisnikPrezime(String korisnikPrezime) {
        this.korisnikPrezime = korisnikPrezime;
    }

    public String getKomentarTekst() {
        return komentarTekst;
    }

    public void setKomentarTekst(String komentarTekst) {
        this.komentarTekst = komentarTekst;
    }
}
