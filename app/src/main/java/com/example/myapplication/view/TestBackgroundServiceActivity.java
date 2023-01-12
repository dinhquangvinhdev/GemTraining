package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;
import com.example.myapplication.service.MBackgroundService;

public class TestBackgroundServiceActivity extends AppCompatActivity {
    private Button btnStart, btnStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_background_service);

        btnStart = findViewById(R.id.btn_start_background_service);
        btnStop = findViewById(R.id.btn_stop_background_service);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService(new Intent(TestBackgroundServiceActivity.this, MBackgroundService.class));
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(new Intent(TestBackgroundServiceActivity.this , MBackgroundService.class));
            }
        });


    }
}