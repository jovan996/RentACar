package com.example.rentacar.Modeli;

public class AutomobilModel {

    private int id;

    private String model;

    private String marka;

    private int brojVrata;

    private int brojSjedista;

    private int kubikaza;

    private String motor;

    private int snagaMotora;

    public AutomobilModel(){

    }

    public AutomobilModel(int id, String model, String marka, int brojVrata, int brojSjedista, int kubikaza, String motor, int snagaMotora) {
        this.id = id;
        this.model = model;
        this.marka = marka;
        this.brojVrata = brojVrata;
        this.brojSjedista = brojSjedista;
        this.kubikaza = kubikaza;
        this.motor = motor;
        this.snagaMotora = snagaMotora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public int getBrojVrata() {
        return brojVrata;
    }

    public void setBrojVrata(int brojVrata) {
        this.brojVrata = brojVrata;
    }

    public int getBrojSjedista() {
        return brojSjedista;
    }

    public void setBrojSjedista(int brojSjedista) {
        this.brojSjedista = brojSjedista;
    }

    public int getKubikaza() {
        return kubikaza;
    }

    public void setKubikaza(int kubikaza) {
        this.kubikaza = kubikaza;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public int getSnagaMotora() {
        return snagaMotora;
    }

    public void setSnagaMotora(int snagaMotora) {
        this.snagaMotora = snagaMotora;
    }
}
