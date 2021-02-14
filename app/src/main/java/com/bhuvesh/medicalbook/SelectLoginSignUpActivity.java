package com.bhuvesh.medicalbook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SelectLoginSignUpActivity extends AppCompatActivity {

    //Initialise the variables
    private Button loginButton, registerButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_login_signup);

        // get button resources objects liked with appropriate view element
        loginButton = (Button) findViewById(R.id.login);
        registerButton = (Button) findViewById(R.id.signup);

        // the function to be called when login button is clicked
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectLoginSignUpActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });

        // the function to be called when register button is clicked
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectLoginSignUpActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });
    }
}