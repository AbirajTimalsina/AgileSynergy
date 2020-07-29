package com.example.agilesynergy;

import com.example.agilesynergy.classes.forgotPasswordUpdate;
import com.example.agilesynergy.classes.userRegister;
import com.example.agilesynergy.models.user;
import com.example.agilesynergy.models.userForgotPassword;

import static junit.framework.Assert.assertTrue;

import org.junit.Test;

public class RegisterTestCase {

    @Test
    public void RegisterTest(){

        user newuser = new user("DumanShahi","9849532863","suman1234@gmail.com"
        ,"1234","null",null,null,null,null);
        userRegister UserRegister = new userRegister(newuser);
        boolean userRegistration = UserRegister.userRegistration();
        assertTrue(userRegistration);
    }

    @Test
    public void PasswordUpadte(){

        forgotPasswordUpdate passwordForgot = new forgotPasswordUpdate("s@gmail.com","What is yours first pets name?","tiger","5678");

        boolean UpdatePassword = passwordForgot.UpdateForgottenPassword();
        assertTrue(UpdatePassword);

    }
}
