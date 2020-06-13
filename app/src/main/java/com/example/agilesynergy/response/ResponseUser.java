package com.example.agilesynergy.response;

public class ResponseUser {

    private String status;
    private String token;

    //class for receiving login and registration response
    public ResponseUser(String status, String token) {
        this.status = status;
        this.token = token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
