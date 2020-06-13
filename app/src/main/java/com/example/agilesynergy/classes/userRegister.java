package com.example.agilesynergy.classes;

import com.example.agilesynergy.api.userapi;
import com.example.agilesynergy.global.global;
import com.example.agilesynergy.models.user;
import com.example.agilesynergy.response.ResponseUser;

import retrofit2.Call;
import retrofit2.Response;

public class userRegister {

    private user user;

    public userRegister(user user) {
        this.user = user;
    }

    public boolean userRegistration() {

        userapi userAPI = global.getInstance().create(userapi.class);
        Call<ResponseUser> userCall = userAPI.userRegister(user);


        try {
            Response<ResponseUser> userResponse = userCall.execute();
            if (userResponse.isSuccessful()) {
                global.token += userResponse.body().getToken();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
