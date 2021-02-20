package com.bhuvesh.medicalbook;

import android.app.Activity;
import android.app.Instrumentation;
import android.view.View;

import androidx.test.rule.ActivityTestRule;

import com.bhuvesh.medicalbook.yogainstructorfeature.YogaInstructorActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertNotNull;

public class YogaGuideTest {
    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule =
            new ActivityTestRule<MainActivity>(MainActivity.class);

    private MainActivity mainActivity = null;

    Instrumentation.ActivityMonitor yogaGuideActivityMonitor = getInstrumentation()
            .addMonitor(YogaInstructorActivity.class.getName(),null,false);




    @Before
    public void setUp() throws Exception {
        // a fixture method called before test case
        mainActivity = mainActivityActivityTestRule.getActivity(); }

    @Test
    public void testMakeARecord(){
        View yogaGuideButton = mainActivity.findViewById(R.id.button_yoga_instructor);
        assertNotNull(yogaGuideButton);

        onView(withId(R.id.button_yoga_instructor)).perform(click());

        Activity yogaGuideActivity = getInstrumentation().waitForMonitorWithTimeout(yogaGuideActivityMonitor,6000);
        assertNotNull(yogaGuideActivity);









    }






    @After
    public void tearDown() throws Exception {
        mainActivity = null;
    }
}

