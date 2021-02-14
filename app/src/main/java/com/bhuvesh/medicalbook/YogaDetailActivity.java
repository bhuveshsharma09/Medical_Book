package com.bhuvesh.medicalbook;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class YogaDetailActivity extends AppCompatActivity {
    /*
    *This activity provides user with step by step guide for yoga.
    * Along with steps there is a timer which user can use to estimate their routine and steps.
    * */


    // add home button on action bar
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
        {
            //Log.d("ID number", String.valueOf("at home"));
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        else
        {
            //Log.d("ID number", String.valueOf("at set"));
            return true;
        }
    }

    public static final String YOGA_ID_PKT = "id";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yoga_detail);
        // Add the fragmnet of detailed yoga based on yoga ID
        FragmentYogaDetail fragmentYogaDetail = (FragmentYogaDetail) getSupportFragmentManager().findFragmentById(R.id.fragment_yoga_detail);
        int yogaId = (int) getIntent().getExtras().get(YOGA_ID_PKT);
        fragmentYogaDetail.setYogaId(yogaId);
    }
}