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
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class GoToNearByHospitalTest {

    @Rule
    public ActivityTestRule<SelectLoginSignUpActivity> mActivityTestRule = new ActivityTestRule<>(SelectLoginSignUpActivity.class);

    @Rule
    public GrantPermissionRule mGrantPermissionRule =
            GrantPermissionRule.grant(
                    "android.permission.ACCESS_FINE_LOCATION");

    @Test
    public void goToNearByHospitalTest() {
        ViewInteraction materialButton = onView(
allOf(withId(R.id.login), withText("Login"),
childAtPosition(
childAtPosition(
withClassName(is("android.widget.LinearLayout")),
1),
1),
isDisplayed()));
        materialButton.perform(click());

        ViewInteraction appCompatEditText = onView(
allOf(withId(R.id.email),
childAtPosition(
childAtPosition(
withId(android.R.id.content),
0),
0),
isDisplayed()));
        appCompatEditText.perform(replaceText("vv@vv.com"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
allOf(withId(R.id.password),
childAtPosition(
childAtPosition(
withId(android.R.id.content),
0),
1),
isDisplayed()));
        appCompatEditText2.perform(replaceText("11111111"), closeSoftKeyboard());

        ViewInteraction materialButton2 = onView(
allOf(withId(R.id.login), withText("Login"),
childAtPosition(
childAtPosition(
withId(android.R.id.content),
0),
2),
isDisplayed()));
        materialButton2.perform(click());

        ViewInteraction materialButton3 = onView(
allOf(withId(R.id.button_nearby_hospitals), withText("Find hospital"),
childAtPosition(
childAtPosition(
withClassName(is("androidx.cardview.widget.CardView")),
0),
1),
isDisplayed()));
        materialButton3.perform(click());

        ViewInteraction materialButton4 = onView(
allOf(withId(R.id.button_get_nearby_hospitals), withText("FIND"),
childAtPosition(
childAtPosition(
withId(android.R.id.content),
0),
1),
isDisplayed()));
        materialButton4.perform(click());

        ViewInteraction actionMenuItemView = onView(
allOf(withId(R.id.dashboard), withContentDescription("Home"),
childAtPosition(
childAtPosition(
withId(R.id.action_bar),
1),
0),
isDisplayed()));
        actionMenuItemView.perform(click());
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
                        && view.equals(((ViewGroup)parent).getChildAt(position));
            }
        };
    }
    }
