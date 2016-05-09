package com.rebtel.restflags;

/**
 * Created by gabordudas on 09/05/16.
 * Copyright (c) 2015 RestFlags. All rights reserved.
 */

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayingAtLeast;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * This class is for testing the UI
 * run "./gradlew cAT" in terminal
 */
@RunWith(AndroidJUnit4.class)
public class ActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void checkItemCount() {
        mActivityRule.getActivity();

        // Checking the count of the items in recyclerview
        onView(withId(R.id.recyclerMain)).check(RecyclerViewAssertions.greaterThan(0));

        performOpenListItem();
    }


    public void performOpenListItem() {
        performSwipeRefresh();

        // Perform item click in recycler view
        onView(withId(R.id.recyclerMain)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));

        ViewInteraction fragmentImage = onView(withId(R.id.imageDetail));
        // Checking that the fragment is created and showed
        fragmentImage.check(ViewAssertions.matches(isDisplayed()));
    }


    public void performSwipeRefresh() {

        // Perform swipe to refresh
        onView(withId(R.id.swipeRefreshMain)).perform(withCustomConstraints(swipeDown(), isDisplayingAtLeast(1)));

        // Checks the number of list items if it's more than 0
        onView(withId(R.id.recyclerMain)).check(RecyclerViewAssertions.greaterThan(0));
    }

    public static ViewAction withCustomConstraints(final ViewAction action, final Matcher<View> constraints) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return constraints;
            }

            @Override
            public String getDescription() {
                return action.getDescription();
            }

            @Override
            public void perform(UiController uiController, View view) {
                action.perform(uiController, view);
            }
        };
    }
}
