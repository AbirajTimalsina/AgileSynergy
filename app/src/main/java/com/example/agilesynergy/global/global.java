package com.example.agilesynergy.global;

import com.example.agilesynergy.models.item;
import com.example.agilesynergy.models.user;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class global {
    //This is a function class which holds global variables
  public static final String Base_URL = "http://10.0.2.2:3000/";
//    public static final String Base_URL = "http://192.168.100.41:3000/";//localhost ip address
    public static String token = "Bearer ";
    public static String imagePath = Base_URL + "uploads/";

    public static item item;
    public static List<item> itemList;
    public static ArrayList<JSONObject> ItemLists = new ArrayList<>(); //0
    public static int counter=0;

    public static Retrofit getInstance() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Base_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
