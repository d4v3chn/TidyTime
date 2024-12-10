package com.tidytime.app;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.res.ResourcesCompat;
import java.util.Locale;

public class TimerActivity extends AppCompatActivity {

    private TextView timerTextView;
    private AppCompatButton startStopButton, pauseButton;
    private CountDownTimer countDownTimer;
    private boolean timerRunning;
    private long timeLeftInMillis = 60000; // 1 minute for demonstration

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        timerTextView = findViewById(R.id.timerText);
        startStopButton = findViewById(R.id.startTimerButton);

        startStopButton.setOnClickListener(v -> {
            if (!timerRunning) {
                startTimer();
                setupButtonsForRunning();
            } else {
                stopTimer();
            }
        });
    }

    private void setupButtonsForRunning() {
        startStopButton.setText(R.string.stop);

        // Create the Pause button dynamically
        if (pauseButton == null) {
            pauseButton = new AppCompatButton(this);
            pauseButton.setId(View.generateViewId());
            pauseButton.setText(R.string.pause);
            pauseButton.setTextSize(40);
            pauseButton.setTextColor(ResourcesCompat.getColor(getResources(), R.color.lightGray, null));
            pauseButton.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.circle_button, null));
            pauseButton.setOnClickListener(v -> pauseTimer());

            ((ConstraintLayout) startStopButton.getParent()).addView(pauseButton);

            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone((ConstraintLayout) startStopButton.getParent());
            constraintSet.connect(pauseButton.getId(), ConstraintSet.START, startStopButton.getId(), ConstraintSet.END, 8);
            constraintSet.connect(pauseButton.getId(), ConstraintSet.TOP, startStopButton.getId(), ConstraintSet.TOP);
            constraintSet.connect(pauseButton.getId(), ConstraintSet.BOTTOM, startStopButton.getId(), ConstraintSet.BOTTOM);
            constraintSet.applyTo((ConstraintLayout) startStopButton.getParent());
        }

        pauseButton.setVisibility(View.VISIBLE);
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timerRunning = false;
                startStopButton.setText(R.string.start);
                if (pauseButton != null) pauseButton.setVisibility(View.GONE);
            }
        }.start();
        timerRunning = true;
    }

    private void stopTimer() {
        countDownTimer.cancel();
        timerRunning = false;
        startStopButton.setText(R.string.start);
        if (pauseButton != null) pauseButton.setVisibility(View.GONE);
    }

    private void pauseTimer() {
        countDownTimer.cancel();
        timerRunning = false;
        startStopButton.setText(R.string.resume);
    }

    private void updateCountDownText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;
        String timeFormatted = String.format(Locale.UK, "%02d:%02d", minutes, seconds);
        timerTextView.setText(timeFormatted);
    }
}
