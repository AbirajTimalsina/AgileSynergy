package com.example.agilesynergy.api;

import com.example.agilesynergy.models.fastfoodModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface fastfoodApi {
    @GET("fastfood/all")
    Call<List<fastfoodModel>> getfastfooddetails();

}
