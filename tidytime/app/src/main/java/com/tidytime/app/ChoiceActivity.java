package com.tidytime.app;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class ChoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        AppCompatButton btnTimer = findViewById(R.id.btnTimer);
        btnTimer.setOnClickListener(v -> startActivity(new Intent(this, TimerActivity.class)));

        AppCompatButton btnStopwatch = findViewById(R.id.btnStopwatch);  // Assume you have StopwatchActivity
        btnStopwatch.setOnClickListener(v -> startActivity(new Intent(this, StopwatchActivity.class)));

        AppCompatButton btnTodo = findViewById(R.id.btnTodo);  // Assume you have TodoActivity
        btnTodo.setOnClickListener(v -> startActivity(new Intent(this, TodoActivity.class)));
    }
}
