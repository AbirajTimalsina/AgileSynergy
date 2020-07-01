package com.example.agilesynergy.models;

public class fastfoodModel {

    private String _id, fastfoodname, fastfoodprice, fastfoodpicture;


    public fastfoodModel(String _id, String fastfoodname, String fastfoodprice, String fastfoodpicture) {
        this._id = _id;
        this.fastfoodname = fastfoodname;
        this.fastfoodprice = fastfoodprice;
        this.fastfoodpicture = fastfoodpicture;
    }

    public String get_id() { return _id; }

    public void set_id(String _id) { this._id = _id; }

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
