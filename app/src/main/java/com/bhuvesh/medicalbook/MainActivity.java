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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class MainActivity extends AppCompatActivity  {

    Button dailyMedicalRecord, yogaInstructor, safeEntry;

    // initialise sensor - accelerometer
    // initialise variables for shake detection
    private SensorManager mSensorManager;
    private float mAccel;
    private float mAccelCurrent;
    private float mAccelLast;
    







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);


        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Log.d("ID number", String.valueOf(item.getItemId()));

        int itemId = 0;
        itemId = item.getItemId();

        if (itemId == R.id.dashboard)
        {
            Log.d("ID number", String.valueOf("at home"));

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

            return true;




        }
        else
        {
            Log.d("ID number", String.valueOf("at set"));
            return true;
        }





        //return super.onOptionsItemSelected(item);
    }

    // TODO: Change names
    public static final int CAMERA_REQUEST_PERMISSION = 1;
    public static final int SCANNING_REQUEST_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dailyMedicalRecord = (Button) findViewById(R.id.button_daily_record);
        yogaInstructor = (Button) findViewById(R.id.button_yoga_instructor);
        safeEntry = (Button) findViewById(R.id.button_safe_entry);

        String date = new SimpleDateFormat("dd-MM-yyy").format(new Date());
       // Date currentTime = Calendar.getInstance().getTime();
        Log.d("date", String.valueOf(date));



        //sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Objects.requireNonNull(mSensorManager).registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
        mAccel = 10f;
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;



        safeEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //start scanning
                startScanningActivity();
            }
        });

    }

    private final SensorEventListener mSensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            mAccelLast = mAccelCurrent;
            mAccelCurrent = (float) Math.sqrt((double) (x * x + y * y + z * z));
            float delta = mAccelCurrent - mAccelLast;
            mAccel = mAccel * 0.9f + delta;
            if (mAccel > 12) {
                Context context = getApplicationContext();
                Toast.makeText(getApplicationContext(), "Shake event detected", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, NearByHospitals.class);
                startActivity(intent);
            }
        }
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };



    private void startScanningActivity() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                //ask for the permision
                requestPermissions(new String[] {Manifest.permission.CAMERA},CAMERA_REQUEST_PERMISSION);
            }else {
                //start scanning
                startActivityForResult(new Intent(MainActivity.this,SafeEntry.class),SCANNING_REQUEST_CODE);
            }
        }else {
            //start scanning
            startActivityForResult(new Intent(MainActivity.this,SafeEntry.class),SCANNING_REQUEST_CODE);
        }
    }

    public void launchDailyNotes(View view) {

        Intent intent = new Intent(this, DialyMedicalRecordActivity.class);
        startActivity(intent);


    }







    public void launchYogaInstructor(View view) {
        Intent intent = new Intent(this, YogaInstructorActivity.class);
        startActivity(intent);
    }

    public void launchSafeEntry(View view) {
        Intent intent = new Intent(this, SafeEntry.class);
        startActivity(intent);
    }




    // TODO: modify
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_REQUEST_PERMISSION){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                startActivityForResult(new Intent(MainActivity.this,SafeEntry.class),SCANNING_REQUEST_CODE);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SCANNING_REQUEST_CODE){
            if (resultCode == RESULT_OK){
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(data.getStringExtra("scanning_result")));
                startActivity(browserIntent);
                //safeEntry.setText(data.getStringExtra("scanning_result"));
            }
        }
    }

    public void launchMap(View view) {
        Intent intent = new Intent(this, NearByHospitals.class);
        startActivity(intent);
    }



    public void launchFood(View view) {
        Intent intent = new Intent(this, HealthyFood.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
        super.onResume();
    }
    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(mSensorListener);
        super.onPause();
    }
}