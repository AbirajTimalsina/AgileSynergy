package com.example.agilesynergy.classes;

public class UserLogin {

    private String phonenumber;
    private String password;

    public UserLogin(String phonenumber, String password) {
        this.phonenumber = phonenumber;
        this.password = password;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
