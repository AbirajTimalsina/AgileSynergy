package com.example.agilesynergy.api;

import com.example.agilesynergy.models.feedbackModel;
import com.example.agilesynergy.models.user;
import com.example.agilesynergy.models.userCRUD;
import com.example.agilesynergy.models.userForgotPassword;
import com.example.agilesynergy.response.ResponseImage;
import com.example.agilesynergy.response.ResponseUser;

import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface userapi {

    @POST("users/signup")
    Call<ResponseUser> userRegister(@Body user user);

    @POST("users/userforgotpassword")
    Call<user> GetForgottenUserData(@Body user user);

    @PUT("users/userforgotpassword")
    Call<userForgotPassword> updateForgottenUser(@Body userForgotPassword user);

    @FormUrlEncoded
    @POST("users/login")
    Call<ResponseUser> checklogin(@Field("phonenumber") String phonenumber, @Field("password") String password);

    @PUT("users/purchaseupdate")
    Call<JSONObject> userPurchase(@Header("Authorization") String token, @Body ArrayList<JSONObject> CustomUserObj);

    @GET("users/me")
    Call<user> getUserDetails(@Header("Authorization") String token);

    @POST("users/feedback")
    Call<Void> postFeedBack(@Header("Authorization") String token,@Body feedbackModel feedbackModel);

    @PUT("users/me")
    Call<userCRUD> updateuser(@Header("Authorization") String token, @Body userCRUD UserCRUD);


    @Multipart
    @POST("upload")
    Call<ResponseImage> uploadpic(@Part MultipartBody.Part img);

    @DELETE("/users/{id}/feedback/{feedbackid}")
    Call<Void> deletefavouirtelist(@Header("Authorization") String token, @Path("id") String userid, @Path("feedbackid") String feedbackid);

}
