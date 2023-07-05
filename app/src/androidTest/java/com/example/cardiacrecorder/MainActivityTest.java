package com.example.cardiacrecorder;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.content.Context;
import android.os.SystemClock;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */


@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);
        @Test
        public void AddDataTest(){
            SystemClock.sleep(2000);
            onView(withId(R.id.add_note_btn)).perform(click());

            SystemClock.sleep(1000);
            onView(withId(R.id.date)).perform(ViewActions.typeText("10/12/2023"));
            onView(withId(R.id.time)).perform(ViewActions.typeText("10:40"));
            onView(withId(R.id.systolic)).perform(ViewActions.typeText("120"));
            onView(withId(R.id.dia)).perform(ViewActions.typeText("80"));
            onView(withId(R.id.heart)).perform(ViewActions.typeText("100"));
            Espresso.pressBack();
            onView(withId(R.id.cmnt)).perform(ViewActions.typeText("none"));

            Espresso.pressBack(); //hide keyboard

            onView(withId(R.id.save)).perform(click());
            SystemClock.sleep(2000);
        }

        @Test
    public void EditDataTest(){
            SystemClock.sleep(2000);
            onView(withId(R.id.recyclerview)).perform(click());

            SystemClock.sleep(2000);

            onView(withId(R.id.date)).perform(clearText()).perform(ViewActions.typeText("31/06/2022"));
            onView(withId(R.id.time)).perform(clearText()).perform(ViewActions.typeText("11:30"));
            onView(withId(R.id.systolic)).perform(clearText()).perform(ViewActions.typeText("130"));
            onView(withId(R.id.dia)).perform(clearText()).perform(ViewActions.typeText("100"));
            Espresso.pressBack(); //Back button
            onView(withId(R.id.heart)).perform(clearText()).perform(ViewActions.typeText("60"));
            Espresso.pressBack(); //Back button
            onView(withId(R.id.cmnt)).perform(clearText()).perform(ViewActions.typeText("Normal"));
            Espresso.pressBack(); //Back button
            SystemClock.sleep(2000);
            onView(withId(R.id.save)).perform(click());
            SystemClock.sleep(3000);

        }

        @Test
    public void DeleteTest(){
            SystemClock.sleep(2000);
            onView(withId(R.id.recyclerview)).perform(click());
            SystemClock.sleep(1000);
            onView(withId(R.id.deletebtn)).perform(click());
            SystemClock.sleep(2000);
        }

    }