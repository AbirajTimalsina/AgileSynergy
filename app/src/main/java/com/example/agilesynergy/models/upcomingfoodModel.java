package com.example.agilesynergy.models;

public class upcomingfoodModel {

    private  String upcomingfoodname,upcomingfoodprice,upcomingfoodpicture;


    public upcomingfoodModel(String upcomingfoodname, String upcomingfoodprice, String upcomingfoodpicture) {
        this.upcomingfoodname = upcomingfoodname;
        this.upcomingfoodprice = upcomingfoodprice;
        this.upcomingfoodpicture = upcomingfoodpicture;
    }

    public String getUpcomingfoodname() {
        return upcomingfoodname;
    }

    public void setUpcomingfoodname(String upcomingfoodname) {
        this.upcomingfoodname = upcomingfoodname;
    }

    public String getUpcomingfoodprice() {
        return upcomingfoodprice;
    }

    public void setUpcomingfoodprice(String upcomingfoodprice) {
        this.upcomingfoodprice = upcomingfoodprice;
    }

    public String getUpcomingfoodpicture() {
        return upcomingfoodpicture;
    }

    public void setUpcomingfoodpicture(String upcomingfoodpicture) {
        this.upcomingfoodpicture = upcomingfoodpicture;
    }
}
