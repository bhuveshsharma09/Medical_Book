package com.bhuvesh.medicalbook;

import android.app.Activity;
import android.app.Instrumentation;
import android.view.View;

import androidx.test.rule.ActivityTestRule;

import com.bhuvesh.medicalbook.medicalrecordfeature.DialyMedicalRecordActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertNotNull;

public class DailyRecordTest {
    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule =
            new ActivityTestRule<MainActivity>(MainActivity.class);

    private MainActivity mainActivity = null;

    Instrumentation.ActivityMonitor dailyRecordActivityMonitor = getInstrumentation()
            .addMonitor(DialyMedicalRecordActivity.class.getName(),null,false);

    Instrumentation.ActivityMonitor dialogBox = getInstrumentation()
            .addMonitor(DialyMedicalRecordActivity.class.getName(),null,false);



    @Before
    public void setUp() throws Exception {
        // a fixture method called before test case
        mainActivity = mainActivityActivityTestRule.getActivity(); }

    @Test
    public void testMakeARecord(){
        View dailyRecordButton = mainActivity.findViewById(R.id.button_daily_record);
        assertNotNull(dailyRecordButton);

        onView(withId(R.id.button_daily_record)).perform(click());

        Activity dailyRecordActivity = getInstrumentation().waitForMonitorWithTimeout(dailyRecordActivityMonitor,6000);
        assertNotNull(dailyRecordActivity);

        View imageAddRecord = dailyRecordActivity.findViewById(R.id.image_add_record);
        assertNotNull(imageAddRecord);

        onView(withId(R.id.image_add_record)).perform(click());

        Activity dialogBox = getInstrumentation().waitForMonitorWithTimeout(dailyRecordActivityMonitor,6000);
        assertNotNull(dailyRecordActivity);






    }






    @After
    public void tearDown() throws Exception {
        mainActivity = null;
    }
}

