package com.example.agilesynergy.models;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class user implements Serializable {

    private String fullname, phonenumber, email, password, profile_image;
    private qa qa;

    public user(String fullname, String phonenumber, String email, String password, String profile_image, qa qa) {
        this.fullname = fullname;
        this.phonenumber = phonenumber;
        this.email = email;
        this.password = password;
        this.profile_image = profile_image;
        this.qa = qa;
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

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public qa getQa() {
        return qa;
    }

    public void setQa(qa qa) {
        this.qa = qa;
    }
}
