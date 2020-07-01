package com.example.agilesynergy.classes;

import com.example.agilesynergy.api.userapi;
import com.example.agilesynergy.global.global;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

public class userPurchase {

    JSONObject userCustomObj = new JSONObject();

    public boolean userPurchaseFood() {
        try {
            userCustomObj.put("purchase", global.ItemLists);
            userapi userapi = global.getInstance().create(userapi.class);
            Call<JSONObject> userCall = userapi.userPurchase(global.token, userCustomObj);

            //Not working for now.
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
