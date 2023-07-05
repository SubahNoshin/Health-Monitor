package com.example.cardiacrecorder;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.util.Log;

import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.google.android.material.tabs.TabLayout;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginTest {
    @Rule
    public ActivityScenarioRule<Login> activityRule =
            new ActivityScenarioRule<>(Login.class);

    @Test
    public void testLogin()
    {
        onView(withId(R.id.LoginScreen)).check(matches(isDisplayed()));
        onView(withId(R.id.login_icon)).check(matches(isDisplayed()));
        onView(withId(R.id.emailet)).perform(ViewActions.typeText("subahfarzana@gmail.com"));
        onView(withId(R.id.passwordet)).perform(ViewActions.typeText("123456"));
        onView(withId(R.id.login_btn)).perform(click());
    }
}
