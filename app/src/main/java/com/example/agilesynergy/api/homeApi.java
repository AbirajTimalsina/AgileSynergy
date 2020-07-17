package com.example.agilesynergy.api;

import com.example.agilesynergy.models.newdishesModel;
import com.example.agilesynergy.models.popularfoodModel;
import com.example.agilesynergy.models.regularfoodModel;
import com.example.agilesynergy.models.upcomingfoodModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface homeApi {

    @GET("popularfood/all1")
    Call<List<popularfoodModel>> getpopularfooddetails();

    @GET("newdishesfood1")
    Call<List<newdishesModel>> getnewdishesdetails();

    @GET("upcomingfood1")
    Call<List<upcomingfoodModel>> getupcomingfooddetails();

    @GET("regularfood1")
    Call<List<regularfoodModel>> getregularfooddetails();

}
