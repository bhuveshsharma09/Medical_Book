package com.bhuvesh.medicalbook;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
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
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class YogaGuideTest {

    @Rule
    public ActivityTestRule<SplashScreen> mActivityTestRule = new ActivityTestRule<>(SplashScreen.class);

    @Test
    public void yogaGuideTest() throws InterruptedException {
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

        ViewInteraction button = onView(
                allOf(withId(R.id.button_yoga_instructor), withText("YOGA GUIDE"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class))),
                        isDisplayed()));
        Thread.sleep(2000);
        button.check(matches(isDisplayed()));
        Thread.sleep(2000);

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.button_yoga_instructor), withText("Yoga Guide"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.cardview.widget.CardView")),
                                        0),
                                1),
                        isDisplayed()));
        Thread.sleep(2000);
        materialButton3.perform(click());
        Thread.sleep(2000);

        DataInteraction materialTextView = onData(anything())
                .inAdapterView(allOf(withId(android.R.id.list),
                        childAtPosition(
                                withClassName(is("android.widget.FrameLayout")),
                                1)))
                .atPosition(0);
        Thread.sleep(2000);
        materialTextView.perform(click());
        Thread.sleep(2000);


        pressBack();

        DataInteraction materialTextView2 = onData(anything())
                .inAdapterView(allOf(withId(android.R.id.list),
                        childAtPosition(
                                withClassName(is("android.widget.FrameLayout")),
                                1)))
                .atPosition(1);
        Thread.sleep(2000);
        materialTextView2.perform(click());
        Thread.sleep(2000);


        pressBack();
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
