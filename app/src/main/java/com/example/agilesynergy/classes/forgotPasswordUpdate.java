package com.example.agilesynergy.classes;


import android.widget.Toast;

import com.example.agilesynergy.api.userapi;
import com.example.agilesynergy.global.global;
import com.example.agilesynergy.models.qa;
import com.example.agilesynergy.models.user;
import com.example.agilesynergy.models.userForgotPassword;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Response;

public class forgotPasswordUpdate {

    private String email, answer, question, password;

    public forgotPasswordUpdate(String email, String answer, String question, String password) {
        this.email = email;
        this.answer = answer;
        this.question = question;
        this.password = password;
    }

<<<<<<<<< Temporary merge branch 1
    public forgotPasswordUpdate(userForgotPassword passwordForgot) {
    }


=========
>>>>>>>>> Temporary merge branch 2
    public Boolean UpdateForgottenPassword() {

        userapi userapi = global.getInstance().create(userapi.class);
        user user = new user(null, null, email, null, null, null);
        Call<user> userCall = userapi.GetForgottenUserData(user);


        try {
            Response<user> response = userCall.execute();
            if (response.isSuccessful()) {

                if (response.body().getQa().getQuestion().equals(question) &&
                        response.body().getQa().getAnswer().equals(answer)) {
                    return UpdatePassword();
                } else {
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean UpdatePassword() {

        userapi userapi = global.getInstance().create(userapi.class);

        userForgotPassword userForgotPassword = new userForgotPassword(email, password);

        Call<userForgotPassword> UserForgotPasswordCall = userapi.updateForgottenUser(userForgotPassword);
        try {
            Response<userForgotPassword> userForgotPasswordResponse = UserForgotPasswordCall.execute();
            if (userForgotPasswordResponse.isSuccessful()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
