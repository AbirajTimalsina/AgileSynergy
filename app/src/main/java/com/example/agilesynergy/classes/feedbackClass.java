package com.example.agilesynergy.classes;

import com.example.agilesynergy.api.userapi;
import com.example.agilesynergy.global.global;
import com.example.agilesynergy.models.feedbackModel;

import retrofit2.Call;
import retrofit2.Response;

public class feedbackClass {

    feedbackModel feedbackModel;

    public feedbackClass(feedbackModel feedbackModel) {
        this.feedbackModel = feedbackModel;
    }

    public boolean postFeedback() {
        userapi userapi = global.getInstance().create(userapi.class);
        Call<Void> userCall = userapi.postFeedBack(global.token,feedbackModel);
        try {

            Response<Void> noResponse = userCall.execute();
            if (noResponse.isSuccessful()) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
