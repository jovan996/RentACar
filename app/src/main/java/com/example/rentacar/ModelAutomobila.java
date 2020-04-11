package com.example.rentacar;

public class ModelAutomobila {

    private int id;

    private String model;

    private String marka;

    public ModelAutomobila(){

    }

    public ModelAutomobila(int id, String model, String marka) {
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
