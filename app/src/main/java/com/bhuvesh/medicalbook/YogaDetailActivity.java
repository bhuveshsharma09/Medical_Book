package com.bhuvesh.medicalbook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class YogaDetailActivity extends AppCompatActivity {

    public static final String YOGA_ID_PKT = "id";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yoga_detail);

        FragmentYogaDetail fragmentYogaDetail = (FragmentYogaDetail) getSupportFragmentManager().findFragmentById(R.id.fragment_yoga_detail);
        int yogaId = (int) getIntent().getExtras().get(YOGA_ID_PKT);
        fragmentYogaDetail.setYogaId(yogaId);
    }
}