package com.example.agilesynergy.api;

import com.example.agilesynergy.models.item;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface itemapi {

    @GET("item/all")
    Call<List<item>> getAllItem();
}
