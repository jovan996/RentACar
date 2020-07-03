package com.example.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Ocjena {

	@Id
	@GeneratedValue
    private int ocjenaId;
    
    @Column
    private int korisnikId;

    @Column
    private int faId;

    @Column
    private int ocjenaBroj;

    public Ocjena(){

    }

    public Ocjena(int ocjenaId, int korisnikId, int faId, int ocjenaBroj) {
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
