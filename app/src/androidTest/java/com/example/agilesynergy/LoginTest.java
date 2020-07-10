package com.example.agilesynergy;

import android.os.SystemClock;
import android.view.View;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matcher;
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


    public void Login() {
        onView(withId(R.id.etnumber))
                .perform(typeText("098765432"))
                .perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.etPassword))
                .perform(typeText("54321"))
                .perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.btnuserlogin))
                .perform(click());
    }



        public ViewAction clickChildViewWithId(final int id) {
            return new ViewAction() {
                @Override
                public Matcher<View> getConstraints() {
                    return null;
                }

                @Override
                public String getDescription() {
                    return "Click on a child view with specified id.";
                }

                @Override
                public void perform(UiController uiController, View view) {
                    View v = view.findViewById(id);
                    v.performClick();
                }
            };
        }



    @Test
    public void userpurchase() {
        Login();
        onView(withId(R.id.fastfoodrecycleview)).perform(
                RecyclerViewActions.actionOnItemAtPosition(0, clickChildViewWithId(R.id.btnadd)));
        onView(withId(R.id.fastfoodrecycleview)).perform(
                RecyclerViewActions.actionOnItemAtPosition(0, clickChildViewWithId(R.id.btncart)));
        SystemClock.sleep(2000);
        onView(withId(R.id.checkoutButton))
                .perform(click());
        SystemClock.sleep(2000);
        onView(withId(R.id.linearlayoutpurchase))
                .perform(click());
        SystemClock.sleep(13000);
    }


}
