package com.bhuvesh.medicalbook;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

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

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class DailyRecordTest {

    @Rule
    public ActivityTestRule<SplashScreen> mActivityTestRule = new ActivityTestRule<>(SplashScreen.class);

    @Test
    public void dailyRecordTest() throws InterruptedException {
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
                allOf(withId(R.id.button_daily_record), withText("DAILY RECORDS"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class))),
                        isDisplayed()));
        Thread.sleep(2000);
        button.check(matches(isDisplayed()));
        Thread.sleep(2000);

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.button_daily_record), withText("Daily Records"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.cardview.widget.CardView")),
                                        0),
                                1),
                        isDisplayed()));
        Thread.sleep(2000);
        materialButton3.perform(click());
        Thread.sleep(2000);

        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.image_add_record),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        Thread.sleep(2000);
        appCompatImageButton.perform(click());
        Thread.sleep(2000);

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.dialog_record_title), withText("Date:21-02-2021"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.custom),
                                        0),
                                0),
                        isDisplayed()));
        Thread.sleep(2000);
        appCompatEditText3.perform(click());
        Thread.sleep(2000);

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.dialog_record_title), withText("Date:21-02-2021"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.custom),
                                        0),
                                0),
                        isDisplayed()));
        Thread.sleep(2000);
        appCompatEditText4.perform(replaceText("Date:21-02-2021 test"));
        Thread.sleep(2000);

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.dialog_record_title), withText("Date:21-02-2021 test"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.custom),
                                        0),
                                0),
                        isDisplayed()));
        Thread.sleep(2000);
        appCompatEditText5.perform(closeSoftKeyboard());
        Thread.sleep(2000);

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.dialog_record_description),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.custom),
                                        0),
                                1),
                        isDisplayed()));
        Thread.sleep(2000);
        appCompatEditText6.perform(replaceText("test"), closeSoftKeyboard());
        Thread.sleep(2000);

        ViewInteraction materialButton4 = onView(
                allOf(withId(android.R.id.button1), withText("Save"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        Thread.sleep(2000);
        materialButton4.perform(scrollTo(), click());
        Thread.sleep(2000);

        ViewInteraction appCompatImageButton2 = onView(
                allOf(withId(R.id.image_add_record),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        Thread.sleep(2000);
        appCompatImageButton2.perform(click());
        Thread.sleep(2000);

        ViewInteraction appCompatEditText7 = onView(
                allOf(withId(R.id.dialog_record_title), withText("Date:21-02-2021"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.custom),
                                        0),
                                0),
                        isDisplayed()));
        Thread.sleep(2000);
        appCompatEditText7.perform(replaceText("Date:21-02-2021 test 2"));
        Thread.sleep(2000);

        ViewInteraction appCompatEditText8 = onView(
                allOf(withId(R.id.dialog_record_title), withText("Date:21-02-2021 test 2"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.custom),
                                        0),
                                0),
                        isDisplayed()));
        Thread.sleep(2000);
        appCompatEditText8.perform(closeSoftKeyboard());
        Thread.sleep(2000);

        ViewInteraction appCompatEditText9 = onView(
                allOf(withId(R.id.dialog_record_description),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.custom),
                                        0),
                                1),
                        isDisplayed()));
        Thread.sleep(2000);
        appCompatEditText9.perform(replaceText("test 2"), closeSoftKeyboard());
        Thread.sleep(2000);

        ViewInteraction materialButton5 = onView(
                allOf(withId(android.R.id.button1), withText("Save"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        Thread.sleep(2000);
        materialButton5.perform(scrollTo(), click());
        Thread.sleep(2000);

        pressBack();
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
