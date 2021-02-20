package com.bhuvesh.medicalbook;

import android.app.Activity;
import android.app.Instrumentation;
import android.view.View;

import androidx.test.rule.ActivityTestRule;

import com.bhuvesh.medicalbook.safeentryfeature.SafeEntry;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertNotNull;

public class SafeEntryTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule =
            new ActivityTestRule<MainActivity>(MainActivity.class);

    private MainActivity mainActivity = null;

    Instrumentation.ActivityMonitor safeEntryActivityMonitor = getInstrumentation()
            .addMonitor(SafeEntry.class.getName(),null,false);



    @Before
    public void setUp() throws Exception {
        // a fixture method called before test case
        mainActivity = mainActivityActivityTestRule.getActivity(); }

    @Test
    public void testSafeEntry(){
        View safeEntryButton = mainActivity.findViewById(R.id.button_safe_entry);
        assertNotNull(safeEntryButton);

        onView(withId(R.id.button_safe_entry)).perform(click());



        Activity safeEntryActivity = getInstrumentation().waitForMonitorWithTimeout(safeEntryActivityMonitor,6000);
        assertNotNull(safeEntryActivity);
         // I expect this to launch the ZXing QR scanner










        //dailyRecordActivity.finish();



    }






    @After
    public void tearDown() throws Exception {
        mainActivity = null;
    }
}

