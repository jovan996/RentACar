package com.example.server.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Komentar {

	@Id
	@GeneratedValue
    private int komentarId;

	@Column
    private int korisnikId;

	@Column
    private int fa_id;

	@Column
    private String komentarTekst;

	@Column
    private String naslov;

    public Komentar(){

    }

    public Komentar(int komentarId, int korisnikId, int fa_id, String komentarTekst, String komentarDatum) {
        this.komentarId = komentarId;
        this.korisnikId = korisnikId;
        this.fa_id = fa_id;
        this.komentarTekst = komentarTekst;
        this.naslov = komentarDatum;
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

    public void setKomentarDatum(String komentarDatum) {
        this.naslov = komentarDatum;
    }
}
