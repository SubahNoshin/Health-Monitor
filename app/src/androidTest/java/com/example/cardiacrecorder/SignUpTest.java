package com.example.cardiacrecorder;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SignUpTest {
    @Rule
    public ActivityScenarioRule<Sign_up> activityRule =
            new ActivityScenarioRule<>(Sign_up.class);
    @Test
    public void testSignUp(){
        onView(withId(R.id.SignUpScreen)).check(matches(isDisplayed()));
        onView(withId(R.id.signup_icon)).check(matches(isDisplayed()));
        onView(withId(R.id.usernameet)).perform(ViewActions.typeText("Farzana"));
        onView(withId(R.id.emailet)).perform(ViewActions.typeText("subahfarzana@gmail.com"));
        onView(withId(R.id.passwordet)).perform(ViewActions.typeText("123456"));
        onView(withId(R.id.confirmPasswordet)).perform(ViewActions.typeText("123456"));
        onView(withId(R.id.regbtn)).perform(click());

    }
}
