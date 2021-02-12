package com.bhuvesh.medicalbook;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class YogaInstructorActivity extends AppCompatActivity implements FragmentYogaList.YogaListListener {



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