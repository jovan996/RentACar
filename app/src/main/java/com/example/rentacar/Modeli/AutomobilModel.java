package com.example.rentacar.Modeli;

public class AutomobilModel {

    private int id;

    private String model;

    private String marka;

    public AutomobilModel(){

    }

    public AutomobilModel(int id, String model, String marka) {
        this.id = id;
        this.model = model;
        this.marka = marka;
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
}
