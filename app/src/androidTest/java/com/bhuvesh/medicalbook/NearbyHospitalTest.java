package com.bhuvesh.medicalbook;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.rule.GrantPermissionRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class NearbyHospitalTest {

    @Rule
    public ActivityTestRule<SplashScreen> mActivityTestRule = new ActivityTestRule<>(SplashScreen.class);

    @Rule
    public GrantPermissionRule mGrantPermissionRule =
            GrantPermissionRule.grant(
                    "android.permission.ACCESS_FINE_LOCATION");


    @Test
    public void nearbyHospitalTest() throws InterruptedException {
        // add delay of 7sec as splash screen is 5 sec
        Thread.sleep(7000);
        ViewInteraction materialButton = onView(
                allOf(withId(R.id.login), withText("Login"),
                        childAtPosition(
                                allOf(withId(R.id.layout_main),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                4),
                        isDisplayed()));
        Thread.sleep(2000);
        materialButton.perform(click());
        Thread.sleep(2000);

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.email),
                        childAtPosition(
                                allOf(withId(R.id.layout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                2),
                        isDisplayed()));
        Thread.sleep(2000);
        appCompatEditText.perform(replaceText("vv@vv.com"), closeSoftKeyboard());
        Thread.sleep(2000);

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.password),
                        childAtPosition(
                                allOf(withId(R.id.layout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                3),
                        isDisplayed()));
        Thread.sleep(2000);
        appCompatEditText2.perform(replaceText("11111111"), closeSoftKeyboard());
        Thread.sleep(2000);

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.login), withText("Login"),
                        childAtPosition(
                                allOf(withId(R.id.layout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                4),
                        isDisplayed()));
        Thread.sleep(2000);
        materialButton2.perform(click());
        Thread.sleep(2000);

        ViewInteraction imageView = onView(
                allOf(withId(R.id.imageView), withContentDescription("TODO"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class))),
                        isDisplayed()));
        Thread.sleep(2000);
        imageView.check(matches(isDisplayed()));
        Thread.sleep(2000);

        ViewInteraction imageView2 = onView(
                allOf(withId(R.id.imageView), withContentDescription("TODO"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class))),
                        isDisplayed()));
        Thread.sleep(2000);
        imageView2.check(matches(isDisplayed()));
        Thread.sleep(2000);

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.button_nearby_hospitals), withText("Find hospital"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.cardview.widget.CardView")),
                                        0),
                                1),
                        isDisplayed()));
        Thread.sleep(2000);
        materialButton3.perform(click());
        Thread.sleep(2000);

        ViewInteraction materialButton4 = onView(
                allOf(withId(R.id.button_get_nearby_hospitals), withText("Nearby Hospitals"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        Thread.sleep(2000);
        materialButton4.perform(click());
        Thread.sleep(2000);

        ViewInteraction actionMenuItemView = onView(
                allOf(withId(R.id.dashboard), withContentDescription("Home"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.action_bar),
                                        1),
                                0),
                        isDisplayed()));
        Thread.sleep(2000);
        actionMenuItemView.perform(click());
        Thread.sleep(2000);

        ViewInteraction materialButton5 = onView(
                allOf(withId(R.id.button_nearby_hospitals), withText("Find hospital"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.cardview.widget.CardView")),
                                        0),
                                1),
                        isDisplayed()));
        Thread.sleep(2000);
        materialButton5.perform(click());
        Thread.sleep(2000);

        ViewInteraction view = onView(
                allOf(withContentDescription("Google Map"),
                        withParent(withParent(withId(R.id.fragment_nearby_hospital))),
                        isDisplayed()));
        Thread.sleep(2000);
        view.check(matches(isDisplayed()));
        Thread.sleep(2000);

        ViewInteraction actionMenuItemView2 = onView(
                allOf(withId(R.id.dashboard), withContentDescription("Home"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.action_bar),
                                        1),
                                0),
                        isDisplayed()));
        Thread.sleep(2000);
        actionMenuItemView2.perform(click());
        Thread.sleep(2000);
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
