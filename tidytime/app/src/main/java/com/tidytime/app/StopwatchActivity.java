package com.tidytime.app;

import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Chronometer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class StopwatchActivity extends AppCompatActivity {
    private AppCompatButton startStopButton;
    private Chronometer chronometer;
    private boolean isRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);

        startStopButton = findViewById(R.id.startStopButton);
        AppCompatButton resetButton = findViewById(R.id.resetButton);
        chronometer = findViewById(R.id.chronometer);

        startStopButton.setOnClickListener(v -> {
            if (!isRunning) {
                startStopwatch();
                startStopButton.setText(R.string.stop);
            } else {
                stopStopwatch();
                startStopButton.setText(R.string.start);
            }
        });

        resetButton.setOnClickListener(v -> resetStopwatch());
    }

    private void startStopwatch() {
        chronometer.start();
        isRunning = true;
    }

    private void stopStopwatch() {
        chronometer.stop();
        isRunning = false;
    }

    private void resetStopwatch() {
        chronometer.stop();
        chronometer.setBase(SystemClock.elapsedRealtime());
        isRunning = false;
        startStopButton.setText(R.string.start);
    }
}