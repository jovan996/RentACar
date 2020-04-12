package com.example.rentacar.Modeli;

public class AutomobilItemModel {

    private int faId;

    private String model;

    private String marka;

    private String slikaPutanja;

    private int cijenaPoDanu;

    public AutomobilItemModel(AutomobilModel modelAutomobila, FirmaAutoModel firmaAuto, SlikaModel slika){
        this.faId=firmaAuto.getFirmaAutoId();
        this.model=modelAutomobila.getModel();
        this.marka=modelAutomobila.getMarka();
        this.slikaPutanja=slika.getSlikaPutanja();
        this.cijenaPoDanu=firmaAuto.getCijenaPoDanu();
    }

    public int getFaId() {
        return faId;
    }

    public String getModel() {
        return model;
    }

    public String getMarka() {
        return marka;
    }

    public String getSlikaPutanja() {
        return slikaPutanja;
    }

    public int getCijenaPoDanu() {
        return cijenaPoDanu;
    }
}
