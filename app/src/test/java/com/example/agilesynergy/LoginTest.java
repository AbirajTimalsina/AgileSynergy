package com.example.agilesynergy;

import com.example.agilesynergy.classes.LoginBLL;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

//creating function
public class LoginTest {
    boolean actual=false;
    boolean expected=true;
    @Test
    public  void Login() {
        assertEquals(actual,expected);
    }

    //checking user

//    @Test
//    public  void LoginClass() {
//        if(loginBLL.checkUser()){
//            actual=true;
//        }
//        assertEquals(actual,expected);
//    }

    @Test
    public  void LoginClassname() {
        LoginBLL loginBLL=new LoginBLL("9860560109","1234");
        if(loginBLL.checkUser()){
            actual=true;
        }
        assertEquals(actual,expected);
    }

}
