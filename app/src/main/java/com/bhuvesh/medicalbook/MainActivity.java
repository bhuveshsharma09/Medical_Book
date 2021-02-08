package com.bhuvesh.medicalbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button dailyMedicalRecord, yogaInstructor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dailyMedicalRecord = (Button) findViewById(R.id.button_daily_record);
        yogaInstructor = (Button) findViewById(R.id.button_yoga_instructor);




    }

    public void launchDailyNotes(View view) {

        Intent intent = new Intent(this, DialyMedicalRecordActivity.class);
        startActivity(intent);


    }




    public void launchYogaInstructor(View view) {
        Intent intent = new Intent(this, YogaInstructorActivity.class);
        startActivity(intent);
    }
}