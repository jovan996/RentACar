package com.example.rentacar.Modeli;

import java.util.Date;

public class KomentarItemModel {

    private int komentarId;

    private int faId;

    private String korisnikIme;

    private String korisnikPrezime;

    private String komentarTekst;

    private Date komentarDatum;

    public KomentarItemModel(){

    }

    public KomentarItemModel(KomentarModel komentar, KorisnikModel korisnik) {
        this.komentarId = komentar.getKomentarId();
        this.faId = komentar.getFa_id();
        this.komentarTekst = komentar.getKomentarTekst();
        this.korisnikIme = korisnik.getIme();
        this.korisnikPrezime = korisnik.getPrezime();
        this.komentarDatum = komentar.getKomentarDatum();
    }

    public KomentarItemModel(int komentarId, int faId, String korisnikIme, String korisnikPrezime, String komentarTekst, Date komentarDatum) {
        this.komentarId = komentarId;
        this.faId = faId;
        this.korisnikIme = korisnikIme;
        this.korisnikPrezime = korisnikPrezime;
        this.komentarTekst = komentarTekst;
        this.komentarDatum = komentarDatum;
    }
    public KomentarItemModel(String korisnikIme, String korisnikPrezime, String komentarTekst, Date komentarDatum) {
        this.korisnikIme = korisnikIme;
        this.korisnikPrezime = korisnikPrezime;
        this.komentarTekst = komentarTekst;
        this.komentarDatum = komentarDatum;
    }

    public int getKomentarId() {
        return komentarId;
    }

    public void setKomentarId(int komentarId) {
        this.komentarId = komentarId;
    }

    public int getFaId() {
        return faId;
    }

    public void setFaId(int faId) {
        this.faId = faId;
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

    public Date getKomentarDatum() {
        return komentarDatum;
    }

    public void setKomentarDatum(Date komentarDatum) {
        this.komentarDatum = komentarDatum;
    }
}
