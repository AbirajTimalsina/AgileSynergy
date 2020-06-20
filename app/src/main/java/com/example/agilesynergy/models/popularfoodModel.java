package com.example.agilesynergy.models;

public class popularfoodModel {
    private String  popularfoodname,popularfoodprice,popularfoodpicture;

    public popularfoodModel(String popularfoodname, String popularfoodprice, String popularfoodpicture) {
        this.popularfoodname = popularfoodname;
        this.popularfoodprice = popularfoodprice;
        this.popularfoodpicture = popularfoodpicture;
    }

    public String getPopularfoodname() {
        return popularfoodname;
    }

    public void setPopularfoodname(String popularfoodname) {
        this.popularfoodname = popularfoodname;
    }

    public String getPopularfoodprice() {
        return popularfoodprice;
    }

    public void setPopularfoodprice(String popularfoodprice) {
        this.popularfoodprice = popularfoodprice;
    }

    public String getPopularfoodpicture() {
        return popularfoodpicture;
    }

    public void setPopularfoodpicture(String popularfoodpicture) {
        this.popularfoodpicture = popularfoodpicture;
    }
}
