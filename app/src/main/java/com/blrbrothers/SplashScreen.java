package com.blrbrothers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {

    private int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            //This method will be executed once the wait is over
            @Override
            public void run() {

                startActivity(new Intent(SplashScreen.this, MainActivity.class));

                //Close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
