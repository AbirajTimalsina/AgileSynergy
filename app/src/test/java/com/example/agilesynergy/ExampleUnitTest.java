package com.example.agilesynergy;

import com.example.agilesynergy.classes.forgotPasswordUpdate;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }



    @Test
    public void checkingForgotPassword(){

        forgotPasswordUpdate forgotPasswordUpdate= new forgotPasswordUpdate("shrestha05@yahoo.com","softwarica college duh",
                "What is your college name?","5432");

        boolean istrue= forgotPasswordUpdate.UpdateForgottenPassword();
        assertTrue(istrue);
    }
}