package com.example.agilesynergy.models;

import android.os.Parcel;
import android.os.Parcelable;

public class item  {

    private String _id, itemname, itemprice, itemingredient, itempicture;

    public item(String _id, String itemname, String itemprice, String itemingredient, String itempicture) {
        this._id = _id;
        this.itemname = itemname;
        this.itemprice = itemprice;
        this.itemingredient = itemingredient;
        this.itempicture = itempicture;
    }


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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
