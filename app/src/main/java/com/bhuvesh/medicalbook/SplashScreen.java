package com.bhuvesh.medicalbook;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {
    // defined animation variables
    Animation logoAnimation, nameAnimation, bottomTextAnimation;
    View firstImage, secondImage, thirdImage, fourthImage;
    TextView appName, devName;
    static int TIMEOUT = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash_screen);

        logoAnimation = AnimationUtils.loadAnimation(this,R.anim.logo_animation);
        nameAnimation = AnimationUtils.loadAnimation(this,R.anim.name_animation);
        bottomTextAnimation = AnimationUtils.loadAnimation(this,R.anim.bottom_text_animation);

        firstImage = findViewById(R.id.first_logo);
        secondImage = findViewById(R.id.second_logo);
        thirdImage = findViewById(R.id.third_logo);
        fourthImage = findViewById(R.id.fourth_logo);

        appName = findViewById(R.id.main_text);
        devName = findViewById(R.id.bottom_text);

        firstImage.setAnimation(logoAnimation);
        secondImage.setAnimation(logoAnimation);
        thirdImage.setAnimation(logoAnimation);
        fourthImage.setAnimation(logoAnimation);

        appName.setAnimation(nameAnimation);

        devName.setAnimation(bottomTextAnimation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this,SelectLoginSignUpActivity.class);
                startActivity(intent);
                finish();

            }
        },TIMEOUT);








    }
}