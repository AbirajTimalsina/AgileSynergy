package com.example.agilesynergy;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static java.util.concurrent.CompletableFuture.allOf;
import static org.hamcrest.Matchers.anything;

public class RegisterTest {

    @Rule
    public ActivityTestRule<RegisterActivity> testRule = new ActivityTestRule<>(RegisterActivity.class);

    @Test
    public void testregister()
    {
        onView(withId(R.id.editTextName))
        .perform(typeText("SumanShahi"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.editTextphonenumber))
                .perform(typeText("9849532864"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.editTextEmail))
                .perform(typeText("suman12345@gmail.com"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.editTextPassword))
                .perform(typeText("1234"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.SpinnerQuestion))
                .perform(click());
        onData(anything()).atPosition(0).perform(click());
        onView(withId(R.id.editTextAnswer))
                .perform(typeText("tiger"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.cirRegisterButton))
                .perform(click());



    }
}
