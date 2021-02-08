package com.bhuvesh.medicalbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class YogaInstructorActivity extends AppCompatActivity implements FragmentYogaList.YogaListListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yoga_instructor);


    }


    @Override
    public void itemClicked(long id) {
        View fragment_container = findViewById(R.id.fragment_container);
        if (fragment_container != null) {

            FragmentYogaDetail fragmentYogaDetail = new FragmentYogaDetail();
            //details.setYoga(id);


            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentYogaDetail.setYogaId(id);
            fragmentTransaction.replace(R.id.fragment_container, fragmentYogaDetail);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            fragmentTransaction.commit();

        } else {
            Intent intent = new Intent(this, YogaDetailActivity.class);
            intent.putExtra(YogaDetailActivity.YOGA_ID_PKT, (int) id);
            startActivity(intent);
        }
    }
}