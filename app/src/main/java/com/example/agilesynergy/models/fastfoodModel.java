package com.example.agilesynergy.models;

public class fastfoodModel {

    private String fastfoodname,fastfoodprice,fastfoodpicture;

    public fastfoodModel(String fastfoodname, String fastfoodprice, String fastfoodpicture) {
        this.fastfoodname = fastfoodname;
        this.fastfoodprice = fastfoodprice;
        this.fastfoodpicture = fastfoodpicture;
    }

    public String getFastfoodname() {
        return fastfoodname;
    }

    public void setFastfoodname(String fastfoodname) {
        this.fastfoodname = fastfoodname;
    }

    public String getFastfoodprice() {
        return fastfoodprice;
    }

    public void setFastfoodprice(String fastfoodprice) {
        this.fastfoodprice = fastfoodprice;
    }

    public String getFastfoodpicture() {
        return fastfoodpicture;
    }

    public void setFastfoodpicture(String fastfoodpicture) {
        this.fastfoodpicture = fastfoodpicture;
    }
}
