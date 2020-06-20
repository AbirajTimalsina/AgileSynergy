package com.example.agilesynergy.models;

public class regularfoodModel {

    private String regularfoodname, regularfoodprice, regularfoodpicture;

    public regularfoodModel(String regularfoodname, String regularfoodprice, String regularfoodpicture) {
        this.regularfoodname = regularfoodname;
        this.regularfoodprice = regularfoodprice;
        this.regularfoodpicture = regularfoodpicture;
    }

    public String getRegularfoodname() {
        return regularfoodname;
    }

    public void setRegularfoodname(String regularfoodname) {
        this.regularfoodname = regularfoodname;
    }

    public String getRegularfoodprice() {
        return regularfoodprice;
    }

    public void setRegularfoodprice(String regularfoodprice) {
        this.regularfoodprice = regularfoodprice;
    }

    public String getRegularfoodpicture() {
        return regularfoodpicture;
    }

    public void setRegularfoodpicture(String regularfoodpicture) {
        this.regularfoodpicture = regularfoodpicture;
    }
}
