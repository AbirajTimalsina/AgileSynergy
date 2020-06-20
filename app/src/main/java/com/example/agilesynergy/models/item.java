package com.example.agilesynergy.models;

public class item {

    private String itemname, itemprice, itemingredient, itempicture;

    public item(String itemname, String itemprice, String itemingredient, String itempicture) {
        this.itemname = itemname;
        this.itemprice = itemprice;
        this.itemingredient = itemingredient;
        this.itempicture = itempicture;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getItemprice() {
        return itemprice;
    }

    public void setItemprice(String itemprice) {
        this.itemprice = itemprice;
    }

    public String getItemingredient() {
        return itemingredient;
    }

    public void setItemingredient(String itemingredient) {
        this.itemingredient = itemingredient;
    }

    public String getItempicture() {
        return itempicture;
    }

    public void setItempicture(String itempicture) {
        this.itempicture = itempicture;
    }
}
