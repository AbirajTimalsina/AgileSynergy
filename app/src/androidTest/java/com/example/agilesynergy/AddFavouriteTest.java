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

public class AddFavouriteTest {
    @Rule
    //
    public ActivityTestRule<LoginActivity> testRule =
            new ActivityTestRule<>(LoginActivity.class);
    public void Login() {
        onView(withId(R.id.etnumber))
                .perform(typeText("9849601684"))
                .perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.etPassword))
                .perform(typeText("1234"))
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
    public void addfavouritetest(){
        Login();
        onView(withId(R.id.navigation_menu)).perform(click());
        onView(withId(R.id.recyclerviewmenu)).perform(
                RecyclerViewActions.actionOnItemAtPosition(0, clickChildViewWithId(R.id.animationheart)));
        SystemClock.sleep(2000);
    }
}
