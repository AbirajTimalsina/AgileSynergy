package com.example.agilesynergy.api;

import com.example.agilesynergy.models.item;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface itemapi {

    @GET("item")
    Call<List<item>> getAllItem();


}
