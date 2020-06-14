package com.example.agilesynergy;

import androidx.test.espresso.action.ViewActions;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class LoginTest {
    @Rule
    public ActivityTestRule<LoginActivity> testRule =
            new ActivityTestRule<>(LoginActivity.class);
    String expected = "true";
    @Test
    public void Login() {
        onView(withId(R.id.etnumber))
                .perform(typeText("9875149998"))
                .perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.etPassword))
                .perform(typeText("1234"))
                .perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.btnuserlogin))
                .perform(click());

    }


}
