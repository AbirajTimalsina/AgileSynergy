package com.example.agilesynergy;

import com.example.agilesynergy.classes.feedbackClass;
import com.example.agilesynergy.models.feedbackModel;
import com.example.agilesynergy.global.global;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FeedbackTest {

    private boolean actual;
    private boolean expected = true;

//    @Test
//    public void feedback() {
//        assertEquals(actual, expected);
//    }

//    @Test
//    public void feedback() {
//
//        actual = new feedbackClass(feedbackModel).postFeedback();
//        assertEquals(actual, expected);
//    }

    @Test
    public void feedback() {

        //change token of your account
        global.token += "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySUQiOiI1ZjE4MThlMzExOTE5MDNiNzRhOThlOWQiLCJpYXQiOjE1OTc5MDkwODd9.I3-lZ8XHaPxuCQlIcz4owUlWibw7aJ1MxLp4pkhIByQ";
        feedbackModel feedbackModel = new feedbackModel(null, "Ice cream", "yes", null);
        actual = new feedbackClass(feedbackModel).postFeedback();
        assertEquals(actual, expected);
    }

}
