package com.example.agilesynergy.global;

import com.example.agilesynergy.models.user;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class global {
<<<<<<<<< Temporary merge branch 1
//    public static final String Base_URL = "http://10.0.2.2:3000/"; //localhost ip address
    public static final String Base_URL="http://192.168.0.107:3000/";//fortesting
=========
    public static final String Base_URL = "http://10.0.2.2:3000/";
    //public static final String Base_URL = "http://192.168.1.71:3000/";//localhost ip address
>>>>>>>>> Temporary merge branch 2
    public static String token = "Bearer ";
    public static String imagePath = Base_URL + "uploads/" ;

    public static user user;

    public static Retrofit getInstance() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Base_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;

    }
}
