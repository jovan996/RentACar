package com.example.rentacar.Modeli;

import java.util.Date;

public class EvidencijaModel {

    private int evidencijaId;

    private int korisnikId;

    private int automobilId;

    private Date evidencijaDatum;

    private Date datumUzimanja;

    private Date datumVracanja;

    public EvidencijaModel(){

    }

    public EvidencijaModel(int evidencijaId, int korisnikId, int automobilId, Date evidencijaDatum, Date datumUzimanja, Date datumVracanja) {
        this.evidencijaId = evidencijaId;
        this.korisnikId = korisnikId;
        this.automobilId = automobilId;
        this.evidencijaDatum = evidencijaDatum;
        this.datumUzimanja = datumUzimanja;
        this.datumVracanja = datumVracanja;
    }

    public int getEvidencijaId() {
        return evidencijaId;
    }

    public void setEvidencijaId(int evidencijaId) {
        this.evidencijaId = evidencijaId;
    }

    public int getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(int korisnikId) {
        this.korisnikId = korisnikId;
    }

    public int getAutomobilId() {
        return automobilId;
    }

    public void setAutomobilId(int automobilId) {
        this.automobilId = automobilId;
    }

    public Date getEvidencijaDatum() {
        return evidencijaDatum;
    }

    public void setEvidencijaDatum(Date evidencijaDatum) {
        this.evidencijaDatum = evidencijaDatum;
    }

    public Date getDatumUzimanja() {
        return datumUzimanja;
    }

    public void setDatumUzimanja(Date datumUzimanja) {
        this.datumUzimanja = datumUzimanja;
    }

    public Date getDatumVracanja() {
        return datumVracanja;
    }

    public void setDatumVracanja(Date datumVracanja) {
        this.datumVracanja = datumVracanja;
    }
}
