package com.example.agilesynergy;

import android.os.SystemClock;
import android.view.View;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

public class userPurchaseTest {
    @Rule
    public ActivityTestRule<LoginActivity> testRule = new ActivityTestRule<>(LoginActivity.class);

@Test
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


//    public void userpurchase() {
//        Login();
//        Matcher<View> matcher = allOf(withText("Menu"),
//                isDescendantOfA(withId(R.id.navigation)));
//        onView(matcher).perform(click());
//        SystemClock.sleep(800);
//
//        Matcher<View> matcher1 = allOf(withText("Menu"),
//                isDescendantOfA(withId(R.id.navigation_menu)));
//        onView(matcher).perform(click());
//        SystemClock.sleep(800);
//
//        onView(withId(R.id.recyclerviewmenu))
//                .perform(actionOnItemAtPosition(0, click()));
//        SystemClock.sleep(800);
//    }

}
//    public void checkLogin() {
////        onView(withId(R.id.etnumber))
////                .perform(typeText("9849601684"))
////                .perform(ViewActions.closeSoftKeyboard());
////        onView(withId(R.id.etPassword))
////                .perform(typeText("1234"))
////                .perform(ViewActions.closeSoftKeyboard());
////        onView(withId(R.id.btnuserlogin))
////                .perform(click());
//
//        onView(withId(R.id.etnumber)).perform(typeText("9849601684"));
//        Espresso.closeSoftKeyboard();
//        onView(withId(R.id.etPassword)).perform(typeText("1234"));
//        Espresso.closeSoftKeyboard();
//        onView(withId(R.id.btnuserlogin)).perform(click());
//        onView(withId(R.id.const_layout)).check(ViewAssertions.matches(isDisplayed()));
//    }