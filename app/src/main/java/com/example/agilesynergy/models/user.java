package com.example.agilesynergy.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class user implements Serializable {

    private String fullname, phonenumber, email, password, address, gender, profile_image;
    private qa qa;
    private ArrayList<feedbackModel> feedback;
    private ArrayList<purchasehistory> purchase;


    public user(String fullname, String phonenumber, String email, String password, String address, String gender, String profile_image, com.example.agilesynergy.models.qa qa, ArrayList<feedbackModel> feedback, ArrayList<purchasehistory> purchase) {
        this.fullname = fullname;
        this.phonenumber = phonenumber;
        this.email = email;
        this.password = password;
        this.address = address;
        this.gender = gender;
        this.profile_image = profile_image;
        this.qa = qa;
        this.feedback = feedback;
        this.purchase = purchase;
    }

    public user() {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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