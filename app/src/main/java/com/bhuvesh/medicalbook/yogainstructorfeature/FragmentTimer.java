package com.bhuvesh.medicalbook.yogainstructorfeature;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bhuvesh.medicalbook.R;

import java.util.Locale;


public class FragmentTimer extends Fragment implements View.OnClickListener {
    /*
    this fragment has a timer and three buttons
    start, stop and reset.
    this fragmnet has been called by yoga detail activity.
    * */


    // declare all the variables
    private int timeSeconds = 0;
    private boolean isRunning, wasRunning;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            // this piece of code will load the saved state of timer on create
            timeSeconds = savedInstanceState.getInt("seconds");
            isRunning = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timer, container, false);
        startTimer(view);

        // link all the variables with correct element
        Button startButton = (Button)view.findViewById(R.id.start_button);
        startButton.setOnClickListener(this);
        Button stopButton = (Button)view.findViewById(R.id.stop_button);
        stopButton.setOnClickListener(this);
        Button resetButton = (Button)view.findViewById(R.id.reset_button);
        resetButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start_button:
                start();
                break;
            case R.id.stop_button:
                stop();
                break;
            case R.id.reset_button:
                reset();
                break;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        wasRunning = isRunning;
        isRunning = false;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (wasRunning) {
            isRunning = true;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // save the state of timer
        savedInstanceState.putInt("seconds", timeSeconds);
        savedInstanceState.putBoolean("running", isRunning);
        savedInstanceState.putBoolean("wasRunning", wasRunning);
    }

    private void start() {
        isRunning = true;
    }

    private void stop() {
        isRunning = false;
    }

    private void reset() {
        isRunning = false;
        timeSeconds = 0;
    }

    private void startTimer(View view) {
        final TextView timeView = (TextView) view.findViewById(R.id.time_display);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            // the thread to start timer
            @Override
            public void run() {
                //actual increment of timer
                int hours = timeSeconds/3600;
                int minutes = (timeSeconds%3600)/60;
                int secs = timeSeconds%60;
                String time = String.format(Locale.getDefault(),
                        "%02d:%02d:%02d", hours, minutes, secs); timeView.setText(time);
                if (isRunning) {
                    timeSeconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }
}






