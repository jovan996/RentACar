package com.example.rentacar;

public class FirmaAuto {

    private int firmaAutoId;

    private int automobilId;

    private int firmaId;

    private int polisaOsiguranja;

    private int cijenaPoDanu;

    private boolean status;

    private String boja;

    private int godiste;

    private int kilometraza;

    public FirmaAuto(){

    }

    public FirmaAuto(int firmaAutoId, int automobilId, int firmaId, int polisaOsiguranja, int cijenaPoDanu, boolean status, String boja, int godiste, int kilometraza) {
        this.firmaAutoId = firmaAutoId;
        this.automobilId = automobilId;
        this.firmaId = firmaId;
        this.polisaOsiguranja = polisaOsiguranja;
        this.cijenaPoDanu = cijenaPoDanu;
        this.status = status;
        this.boja = boja;
        this.godiste = godiste;
        this.kilometraza = kilometraza;
    }

    public int getFirmaAutoId() {
        return firmaAutoId;
    }

    public void setFirmaAutoId(int firmaAutoId) {
        this.firmaAutoId = firmaAutoId;
    }

    public int getAutomobilId() {
        return automobilId;
    }

    public void setAutomobilId(int automobilId) {
        this.automobilId = automobilId;
    }

    public int getFirmaId() {
        return firmaId;
    }

    public void setFirmaId(int firmaId) {
        this.firmaId = firmaId;
    }

    public int getPolisaOsiguranja() {
        return polisaOsiguranja;
    }

    public void setPolisaOsiguranja(int polisaOsiguranja) {
        this.polisaOsiguranja = polisaOsiguranja;
    }

    public int getCijenaPoDanu() {
        return cijenaPoDanu;
    }

    public void setCijenaPoDanu(int cijenaPoDanu) {
        this.cijenaPoDanu = cijenaPoDanu;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getBoja() {
        return boja;
    }

    public void setBoja(String boja) {
        this.boja = boja;
    }

    public int getGodiste() {
        return godiste;
    }

    public void setGodiste(int godiste) {
        this.godiste = godiste;
    }

    public int getKilometraza() {
        return kilometraza;
    }

    public void setKilometraza(int kilometraza) {
        this.kilometraza = kilometraza;
    }
}
