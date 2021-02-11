package com.bhuvesh.medicalbook;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button dailyMedicalRecord, yogaInstructor, safeEntry;


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


        safeEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //start scanning
                startScanningActivity();
            }
        });

    }

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
}