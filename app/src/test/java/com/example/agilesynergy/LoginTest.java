package com.example.agilesynergy;

import com.example.agilesynergy.api.userapi;
import com.example.agilesynergy.classes.LoginBLL;
import com.example.agilesynergy.classes.UserLogin;
import com.example.agilesynergy.global.global;
import com.example.agilesynergy.models.user;
import com.example.agilesynergy.models.userForgotPassword;
import com.example.agilesynergy.response.ResponseUser;

import org.junit.Test;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Url;

import static org.junit.Assert.assertEquals;

public class LoginTest {
   boolean actual=false;
   boolean expected=true;
   @Test
    public  void Login() {
       String phonenumber="9875149998";
       String password="1234";
       LoginBLL loginBLL=new LoginBLL("9875149998","1234");
       if(loginBLL.checkUser(phonenumber,password)){
           actual=true;
       }
       assertEquals(actual,expected);
   }
}
