package com.tidytime.app;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class TimerActivity extends AppCompatActivity {
    private TextView timerTextView;
    private AppCompatButton startStopButton, pauseButton; // Changed back to pauseButton
    private CountDownTimer countDownTimer;
    private boolean timerRunning;
    private long remainingTimeMillis; // Track remaining time for pause/resume
    private EditText minutesInput, secondsInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        timerTextView = findViewById(R.id.timerText);
        startStopButton = findViewById(R.id.startTimerButton);
        pauseButton = findViewById(R.id.pauseButton); // Matching XML layout ID
        minutesInput = findViewById(R.id.minutesInput);
        secondsInput = findViewById(R.id.secondsInput);

        // Initially hide pause button
        pauseButton.setVisibility(View.GONE);

        startStopButton.setOnClickListener(v -> {
            if (!timerRunning) {
                startTimer();
                pauseButton.setVisibility(View.VISIBLE);
                pauseButton.setText(R.string.pause);
                startStopButton.setText(R.string.stop);
            } else {
                stopTimer();
                startStopButton.setText(R.string.start);
                pauseButton.setVisibility(View.GONE);
            }
        });

        pauseButton.setOnClickListener(v -> {
            if (timerRunning) {
                pauseTimer();
            } else {
                resumeTimer();
            }
        });
    }

    private void startTimer() {
        remainingTimeMillis = parseTimerDuration();
        createAndStartTimer(remainingTimeMillis);
        timerRunning = true;
    }

    private void createAndStartTimer(long milliseconds) {
        // Cancel any existing timer
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }

        countDownTimer = new CountDownTimer(milliseconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                remainingTimeMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timerRunning = false;
                startStopButton.setText(R.string.start);
                pauseButton.setVisibility(View.GONE);
                timerTextView.setText(R.string.initial_time);
            }
        }.start();
    }

    private void stopTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        timerRunning = false;
        timerTextView.setText(R.string.initial_time);
        remainingTimeMillis = 0;
    }

    private void pauseTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        timerRunning = false;
        pauseButton.setText(R.string.resume);
    }

    private void resumeTimer() {
        createAndStartTimer(remainingTimeMillis);
        timerRunning = true;
        pauseButton.setText(R.string.pause);
    }

    private long parseTimerDuration() {
        int minutes = minutesInput.getText().toString().isEmpty() ? 0 :
                Integer.parseInt(minutesInput.getText().toString());
        int seconds = secondsInput.getText().toString().isEmpty() ? 0 :
                Integer.parseInt(secondsInput.getText().toString());

        return TimeUnit.MINUTES.toMillis(minutes) + TimeUnit.SECONDS.toMillis(seconds);
    }

    private void updateCountDownText() {
        int minutes = (int) (remainingTimeMillis / 1000) / 60;
        int seconds = (int) (remainingTimeMillis / 1000) % 60;
        String timeFormatted = String.format(Locale.UK, "%02d:%02d", minutes, seconds);
        timerTextView.setText(timeFormatted);
    }
}