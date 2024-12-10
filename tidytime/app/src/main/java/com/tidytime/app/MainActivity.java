package com.tidytime.app;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Set padding for system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        AppCompatButton startTimerButton = findViewById(R.id.startButton);
        startTimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent to start the TimerActivity
                Intent intent = new Intent(MainActivity.this, TimerActivity.class);
                startActivity(intent);
            }
        });


        // Load the rotation animation
//        Animation rotation = AnimationUtils.loadAnimation(this, R.anim.rotate_animation);
//        startButton.startAnimation(rotation);
//
//        // Check if the background is an AnimationDrawable and start it
//        if (startButton.getBackground() instanceof AnimationDrawable) {
//            AnimationDrawable frameAnimation = (AnimationDrawable) startButton.getBackground();
//            frameAnimation.setEnterFadeDuration(500);
//            frameAnimation.setExitFadeDuration(500);
//            frameAnimation.start();
//        }
    }
}
