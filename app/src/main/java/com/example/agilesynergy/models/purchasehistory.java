package com.example.agilesynergy.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class purchasehistory {
    @SerializedName("itemname")
    @Expose
    private String itemname;

    @SerializedName("itemprice")
    @Expose
    private String itemprice;

    public purchasehistory(String itemname, String itemprice) {
        this.itemname = itemname;
        this.itemprice = itemprice;
    }

    public String getItemname() {
        return itemname;
    }

    public String getItemprice() {
        return itemprice;
    }
}
