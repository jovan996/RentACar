package com.example.rentacar.Modeli;

public class SlikaModel {

    private int  slikaId;

    private String slikaPutanja;

    private int automobilId;

    public SlikaModel(){

    }

    public SlikaModel(int slikaId, String slikaPutanja, int automobilId) {
        this.slikaId = slikaId;
        this.slikaPutanja = slikaPutanja;
        this.automobilId = automobilId;
    }

    public int getSlikaId() {
        return slikaId;
    }

    public void setSlikaId(int slikaId) {
        this.slikaId = slikaId;
    }

    public String getSlikaPutanja() {
        return slikaPutanja;
    }

    public void setSlikaPutanja(String slikaPutanja) {
        this.slikaPutanja = slikaPutanja;
    }

    public int getAutomobilId() {
        return automobilId;
    }

    public void setAutomobilId(int automobilId) {
        this.automobilId = automobilId;
    }
}
