package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.myapplication.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //do not need to set content view for splash screen
//        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //start other activity
                startActivity(new Intent(SplashActivity.this , TestWithFragmentActivity.class));
                //remember closing the splash activity
                finish();
            }
        }, 4000);

    }
}