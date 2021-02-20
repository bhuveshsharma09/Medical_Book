package com.bhuvesh.medicalbook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    // declare all the objects
    private Button loginButton;
    private EditText emailInput, passwordInput;

    // declare firebase objects to be used for authentication purpose
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthStateListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // link objects with correct view element
        loginButton = (Button) findViewById(R.id.login);
        emailInput = (EditText) findViewById(R.id.email);
        passwordInput = (EditText) findViewById(R.id.password);

        // initialise the firebase authentication object
        // this object will help setup authentication system for the application
        // where user can login and sign-up using email and password
        firebaseAuth = FirebaseAuth.getInstance();

        // the function called when login button is clicked
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get the value of email and password editText fields
                final String email = emailInput.getText().toString();
                final String password = passwordInput.getText().toString();

                // check if user has input email and password values or not
                if(email.matches("") || password.matches("")) {
                    Toast.makeText(LoginActivity.this, "Please check email and password", Toast.LENGTH_LONG).show();

                }
                else
                {
                    // if user has input email and password values
                    // we are ready to login the user
                    // following code helps to login the user
                    firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener
                            (LoginActivity.this, new OnCompleteListener<AuthResult>()
                            {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (!task.isSuccessful()) {
                                        Toast.makeText(LoginActivity.this, "Login error!", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(LoginActivity.this, "Login done!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

        // this listener is very useful.
        // if a user has already logged in the application.
        // this listener will let the user go to main activity
        // rather than asking user to input email and password every time.
        firebaseAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                if (firebaseUser !=null){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    return;
                }
            }
        };

    }

    // to get firebase user state at onStart of activity.
    // this helps automatic login if user has already logged in
    // on that device.
    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(firebaseAuthStateListener);
    }

    // to save the firebase auth on Stop so that the state can be called
    // onStart to automatically login the user
    @Override
    protected void onStop() {
        super.onStop();
        firebaseAuth.removeAuthStateListener(firebaseAuthStateListener);
    }
}
