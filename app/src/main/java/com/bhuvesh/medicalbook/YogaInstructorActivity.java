package com.bhuvesh.medicalbook;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class YogaInstructorActivity extends AppCompatActivity implements FragmentYogaList.YogaListListener {
    /*
    * This activity will provide a list of yoga
    * which user can follow step by step*/


    // add home button on action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = 0;
        itemId = item.getItemId();
        if (itemId == R.id.dashboard)
        {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        else
        {
            return true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yoga_instructor);
    }

    @Override
    public void itemClicked(long id) {
        // when one of the yoga name is clicked the following code will work
        // get correct fragmnet
        View fragment_container = findViewById(R.id.fragment_container);

        if (fragment_container != null) {
            FragmentYogaDetail fragmentYogaDetail = new FragmentYogaDetail();
            //details.setYoga(id);

            // the following code helps to keep all the natigation in memory
            // and whenever user clicks on back button
            // user is redirected to the last activity they visited.
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