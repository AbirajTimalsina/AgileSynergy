package com.example.agilesynergy.models;

public class feedbackModel {
    private String _id, itemname, favorite, rating;

    public feedbackModel(String _id, String itemname, String favorite, String rating) {
        this._id = _id;
        this.itemname = itemname;
        this.favorite = favorite;
        this.rating = rating;
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

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
