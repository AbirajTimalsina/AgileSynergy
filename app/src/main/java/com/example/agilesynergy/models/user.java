package com.example.agilesynergy.models;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class user implements Serializable {

    private String fullname, phonenumber, email, password, profile_image;
    private qa qa;
    private ArrayList<feedbackModel> feedback;

    public user(String fullname, String phonenumber, String email, String password, String profile_image, com.example.agilesynergy.models.qa qa, ArrayList<feedbackModel> feedback) {
        this.fullname = fullname;
        this.phonenumber = phonenumber;
        this.email = email;
        this.password = password;
        this.profile_image = profile_image;
        this.qa = qa;
        this.feedback = feedback;
    }

    public String getFullname() {
        return fullname;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public com.example.agilesynergy.models.qa getQa() {
        return qa;
    }

    public ArrayList<feedbackModel> getFeedback() {
        return feedback;
    }
}
