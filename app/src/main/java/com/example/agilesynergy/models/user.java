package com.example.agilesynergy.models;

import java.io.Serializable;
import java.util.ArrayList;

public class user{

    private String _id, fullname, phonenumber, email, password, profile_image;
    private qa qa;
    private ArrayList<feedbackModel> feedback;
    private ArrayList<purchasehistory> purchase;


    public user(String _id, String fullname, String phonenumber, String email, String password, String profile_image, com.example.agilesynergy.models.qa qa, ArrayList<feedbackModel> feedback, ArrayList<purchasehistory> purchase) {
        this._id = _id;
        this.fullname = fullname;
        this.phonenumber = phonenumber;
        this.email = email;
        this.password = password;
        this.profile_image = profile_image;
        this.qa = qa;
        this.feedback = feedback;
        this.purchase = purchase;
    }


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public com.example.agilesynergy.models.qa getQa() {
        return qa;
    }

    public void setQa(com.example.agilesynergy.models.qa qa) {
        this.qa = qa;
    }

    public ArrayList<feedbackModel> getFeedback() {
        return feedback;
    }

    public void setFeedback(ArrayList<feedbackModel> feedback) {
        this.feedback = feedback;
    }

    public ArrayList<purchasehistory> getPurchase() {
        return purchase;
    }

    public void setPurchase(ArrayList<purchasehistory> purchase) {
        this.purchase = purchase;
    }
}