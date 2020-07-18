package com.example.agilesynergy.models;

public class feedbackModel {
    private String itemname, favorite, rating;

    public feedbackModel(String itemname, String favorite, String rating) {
        this.itemname = itemname;
        this.favorite = favorite;
        this.rating = rating;
    }

    public String getItemname() {
        return itemname;
    }

    public String getFavorite() {
        return favorite;
    }

    public String getRating() {
        return rating;
    }
}
