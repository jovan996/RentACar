package com.example.rentacar.Modeli;

public class KomentarDTO {
    private int komentarId;

    private int korisnikId;

    private int fa_id;

    private String komentarTekst;

    private String naslov;

    public KomentarDTO() {

    }

    public KomentarDTO(int komentarId, int korisnikId, int fa_id, String komentarTekst, String naslov) {
        this.komentarId = komentarId;
        this.korisnikId = korisnikId;
        this.fa_id = fa_id;
        this.komentarTekst = komentarTekst;
        this.naslov = naslov;
    }

    public int getKomentarId() {
        return komentarId;
    }

    public void setKomentarId(int komentarId) {
        this.komentarId = komentarId;
    }

    public int getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(int korisnikId) {
        this.korisnikId = korisnikId;
    }

    public int getFa_id() {
        return fa_id;
    }

    public void setFa_id(int fa_id) {
        this.fa_id = fa_id;
    }

    public String getKomentarTekst() {
        return komentarTekst;
    }

    public void setKomentarTekst(String komentarTekst) {
        this.komentarTekst = komentarTekst;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }
}
