package com.bhuvesh.medicalbook;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bhuvesh.medicalbook.medicalrecordfeature.DialyMedicalRecordActivity;
import com.bhuvesh.medicalbook.nearbyhospitalfeature.NearByHospitals;
import com.bhuvesh.medicalbook.safeentryfeature.SafeEntry;
import com.bhuvesh.medicalbook.yogainstructorfeature.YogaInstructorActivity;

import java.util.Objects;

public class MainActivity extends AppCompatActivity  {
    /*
    * This is the main activity and dashboard screen of the application.
    * this screen helps user to navigate to various services which the application has to offer.
    *
    * Author: Bhuvesh Kumar
    * */

    // Initialise all the variables
    Button  yogaInstructor, safeEntry;
    Button dailyMedicalRecord;
    TextView temperatureValue, humidityValue;
    Weather weather;
    String weatherData;




    // initialise sensor - accelerometer
    // initialise variables for shake detection
    private SensorManager sensorManager;
    private float thresholdValue, accelerometerCurrentValue, accelerometerLastValue;

    // fixed values for camera permissions
    public static final int REQUEST_CAMERA_PERMISSION = 1;
    public static final int REQUEST_CODE_SCANNING = 2;

    // the following code creates a menu on action bar.
    // it helps the user to navigate to home page.
    // it servers a a shortcut.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //Log.d("ID number", String.valueOf(item.getItemId()));
        int itemId = 0;
        itemId = item.getItemId();
        if (itemId == R.id.dashboard)
        {  //Log.d("ID number", String.valueOf("at home"));
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;        }
        else
        {//Log.d("ID number", String.valueOf("at set"));
            return true; }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen





        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        try {

            weatherData = weather.execute("http://api.openweathermap.org/data/2.5/weather?q=Singapore&appid="
                    + getResources().getString(R.string.weather_api)).get();
            Log.d("dattt", weatherData);



        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/






        // connect the objects to the correct view elements
        dailyMedicalRecord = (Button) findViewById(R.id.button_daily_record);
        yogaInstructor = (Button) findViewById(R.id.button_yoga_instructor);
        safeEntry = (Button) findViewById(R.id.button_safe_entry);

        temperatureValue = (TextView) findViewById(R.id.temperature_value);
        humidityValue = (TextView) findViewById(R.id.humidity_value);

        // start sensor service to get values from accelerometer.
        // accelerometer is a sensor used to detect the shake on mobile device.
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Objects.requireNonNull(sensorManager).registerListener(sensorEventListener, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);

        // can change this value to change the sensitivity
        thresholdValue = 5f;

        accelerometerCurrentValue = SensorManager.GRAVITY_EARTH;
        accelerometerLastValue = SensorManager.GRAVITY_EARTH;


        // when the safe entry button is clicked.
        // the new activity will be called using following code.
        safeEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //start scanning
                startScanningActivity();
            }
        });
    }

    //
    private final SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float xValue = event.values[0];
            float yValue = event.values[1];
            float zValue = event.values[2];
            accelerometerLastValue = accelerometerCurrentValue;
            accelerometerCurrentValue = (float) Math.sqrt((double) (xValue * xValue + yValue * yValue + zValue * zValue));
            float delta = accelerometerCurrentValue - accelerometerLastValue;

            if (delta > thresholdValue) {
                Context context = getApplicationContext();
                Toast.makeText(getApplicationContext(), "Shaking", Toast.LENGTH_SHORT).show();
                // open nearby hospital activity on shake detection
                Intent intent = new Intent(MainActivity.this, NearByHospitals.class);
                startActivity(intent);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };

    private void startScanningActivity() {
        // ask for camera permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[] {Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
            }else {
                //start scanning
                startActivityForResult(new Intent(MainActivity.this, SafeEntry.class), REQUEST_CODE_SCANNING);
            }
        }else {
            //start scanning
            startActivityForResult(new Intent(MainActivity.this,SafeEntry.class), REQUEST_CODE_SCANNING);
        }
    }

    public void launchDailyNotes(View view) {
        // this function will start the Daily note activity
        Intent intent = new Intent(this, DialyMedicalRecordActivity.class);
        startActivity(intent);
    }

    public void launchYogaInstructor(View view) {
        // this function will start the Yoga activity
        Intent intent = new Intent(this, YogaInstructorActivity.class);
        startActivity(intent);
    }

    public void launchSafeEntry(View view) {
        // this function will start the Safe entry (QR scanning)
        Intent intent = new Intent(this, SafeEntry.class);
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA_PERMISSION){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                startActivityForResult(new Intent(MainActivity.this,SafeEntry.class), REQUEST_CODE_SCANNING);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_SCANNING){
            if (resultCode == RESULT_OK){
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(data.getStringExtra("scanning_result")));
                startActivity(browserIntent);
                //safeEntry.setText(data.getStringExtra("scanning_result"));
            }
        }
    }

    public void launchMap(View view) {
        // this function will start the nearby hospital activity
        Intent intent = new Intent(this, NearByHospitals.class);
        startActivity(intent);
    }



    @Override
    protected void onResume() {
        sensorManager.registerListener(sensorEventListener, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
        super.onResume();
    }
    @Override
    protected void onPause() {
        sensorManager.unregisterListener(sensorEventListener);
        super.onPause();
    }
}