package com.example.agilesynergy.models;

import android.os.Parcel;
import android.os.Parcelable;

public class item implements Parcelable {

    private String itemname, itemprice, itemingredient, itempicture;

    public item(String itemname, String itemprice, String itemingredient, String itempicture) {
        this.itemname = itemname;
        this.itemprice = itemprice;
        this.itemingredient = itemingredient;
        this.itempicture = itempicture;
    }

    protected item(Parcel in) {
        itemname = in.readString();
        itemprice = in.readString();
        itemingredient = in.readString();
        itempicture = in.readString();
    }

    public static final Creator<item> CREATOR = new Creator<item>() {
        @Override
        public item createFromParcel(Parcel in) {
            return new item(in);
        }

        @Override
        public item[] newArray(int size) {
            return new item[size];
        }
    };

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(itemname);
        parcel.writeString(itemprice);
        parcel.writeString(itemingredient);
        parcel.writeString(itempicture);
    }
}
