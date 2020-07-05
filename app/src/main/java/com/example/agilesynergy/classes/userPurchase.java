package com.example.agilesynergy.classes;

import com.example.agilesynergy.api.userapi;
import com.example.agilesynergy.global.global;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

public class userPurchase {

    public boolean userPurchaseFood() {
        try {
            userapi userapi = global.getInstance().create(userapi.class);
            Call<JSONObject> userCall = userapi.userPurchase(global.token,global.ItemLists); //retrieving lists from static global

            Response<JSONObject> userResponse = userCall.execute();
            if (userResponse.isSuccessful()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
