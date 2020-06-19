package com.example.agilesynergy.classes;

import android.content.Context;

import com.example.agilesynergy.api.userapi;
import com.example.agilesynergy.global.global;
import com.example.agilesynergy.response.ResponseUser;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class LoginBLL {
    private String phonenumber;
    private String password;
    boolean isSuccess = false;

    public LoginBLL(String phonenumber, String password){
        this.phonenumber = phonenumber;
        this.password = password;
    }

    public boolean checkUser(String phonenumber, String password){


        UserLogin userLogin = new UserLogin(phonenumber,password);



        userapi userApi = global.getInstance().create(userapi.class);
        Call<ResponseUser> usercall = userApi.checklogin(phonenumber,password);
        try{
            Response<ResponseUser> loginResponse = usercall.execute();
            if(loginResponse.isSuccessful() && loginResponse.body().getStatus().equals("Successfully logged in")){
                global.token += loginResponse.body().getToken();
                isSuccess=true;
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return isSuccess;

    }

    public boolean checkUser(){
        return isSuccess;
    }

    public LoginBLL(Context context){

    }
}
