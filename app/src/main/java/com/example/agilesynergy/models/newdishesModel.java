package com.example.agilesynergy.models;

public class newdishesModel {
    private String newdishesname,newdishesprice,newdishespicture;

    public newdishesModel(String newdishesname, String newdishesprice, String newdishespicture) {
        this.newdishesname = newdishesname;
        this.newdishesprice = newdishesprice;
        this.newdishespicture = newdishespicture;
    }

    public String getNewdishesname() {
        return newdishesname;
    }

    public void setNewdishesname(String newdishesname) {
        this.newdishesname = newdishesname;
    }

    public String getNewdishesprice() {
        return newdishesprice;
    }

    public void setNewdishesprice(String newdishesprice) {
        this.newdishesprice = newdishesprice;
    }

    public String getNewdishespicture() {
        return newdishespicture;
    }

    public void setNewdishespicture(String newdishespicture) {
        this.newdishespicture = newdishespicture;
    }
}
