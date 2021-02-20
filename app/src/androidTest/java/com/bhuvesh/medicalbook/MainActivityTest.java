package com.bhuvesh.medicalbook;

import android.app.Activity;
import android.app.Instrumentation;
import android.view.View;

import androidx.test.rule.ActivityTestRule;

import com.bhuvesh.medicalbook.medicalrecordfeature.DialyMedicalRecordActivity;
import com.bhuvesh.medicalbook.nearbyhospitalfeature.NearByHospitals;
import com.bhuvesh.medicalbook.safeentryfeature.SafeEntry;
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

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule =
            new ActivityTestRule<MainActivity>(MainActivity.class);

    private MainActivity mainActivity = null;

    Instrumentation.ActivityMonitor safeEntryActivityMonitor = getInstrumentation()
            .addMonitor(SafeEntry.class.getName(),null,false);

    Instrumentation.ActivityMonitor nearByHospitalActivityMonitor = getInstrumentation()
            .addMonitor(NearByHospitals.class.getName(),null,false);

    Instrumentation.ActivityMonitor dailyRecordActivityMonitor = getInstrumentation()
            .addMonitor(DialyMedicalRecordActivity.class.getName(),null,false);

    Instrumentation.ActivityMonitor yogaGuideActivityMonitor = getInstrumentation()
            .addMonitor(YogaInstructorActivity.class.getName(),null,false);


    @Before
    public void setUp() throws Exception {
        // a fixture method called before test case
        mainActivity = mainActivityActivityTestRule.getActivity();
    }

    @Test
    public void testLaunchMainActivity()
    {
        View view = mainActivity.findViewById(R.id.title_name_main);
        assertNotNull(view);

    }

    @Test
    public void findAllElements(){
        // MainActivity has a number of view objects like
        // buttons in this test, we will get all the view
        // objects and assert to check if they are not null
        View safeEntryButton = mainActivity.findViewById(R.id.button_safe_entry);
        View nearByHospitalButton = mainActivity.findViewById(R.id.button_nearby_hospitals);
        View yogaGuideButton = mainActivity.findViewById(R.id.button_yoga_instructor);
        View dailyRecordButton = mainActivity.findViewById(R.id.button_daily_record);

        assertNotNull(safeEntryButton);
        assertNotNull(nearByHospitalButton);
        assertNotNull(yogaGuideButton);
        assertNotNull(dailyRecordButton);
    }

    @Test
    public void testLaunchSafeEntryActivity(){
        View safeEntryButton = mainActivity.findViewById(R.id.button_safe_entry);
        assertNotNull(safeEntryButton);
        onView(withId(R.id.button_safe_entry)).perform(click());

        Activity safeEntryActivity = getInstrumentation().waitForMonitorWithTimeout(safeEntryActivityMonitor,6000);
        assertNotNull(safeEntryActivity);
        safeEntryActivity.finish();

    }

    @Test
    public void testLaunchNearbyHospitalActivity(){
        View nearByHospital = mainActivity.findViewById(R.id.button_nearby_hospitals);
        assertNotNull(nearByHospital);
        onView(withId(R.id.button_nearby_hospitals)).perform(click());
        Activity nearByHospitalActivity = getInstrumentation().waitForMonitorWithTimeout(nearByHospitalActivityMonitor,6000);
        assertNotNull(nearByHospitalActivity);
        nearByHospitalActivity.finish();

    }

    @Test
    public void testLaunchDailyRecordActivity(){
        View dailyRecordButton = mainActivity.findViewById(R.id.button_daily_record);
        assertNotNull(dailyRecordButton);
        onView(withId(R.id.button_daily_record)).perform(click());
        Activity dailyRecordActivity = getInstrumentation().waitForMonitorWithTimeout(dailyRecordActivityMonitor,6000);
        assertNotNull(dailyRecordActivity);
        dailyRecordActivity.finish();

    }


    @Test
    public void testLaunchYogaActivity(){
        View yogaGuideButton = mainActivity.findViewById(R.id.button_yoga_instructor);
        assertNotNull(yogaGuideButton);
        onView(withId(R.id.button_yoga_instructor)).perform(click());
        Activity yogaActivity = getInstrumentation().waitForMonitorWithTimeout(yogaGuideActivityMonitor,6000);
        assertNotNull(yogaActivity);
        yogaActivity.finish();

    }




    @After
    public void tearDown() throws Exception {
        mainActivity = null;
    }
}