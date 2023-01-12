package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;

import com.example.myapplication.R;

public class TestBackgroundServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_background_service);

        MediaPlayer player = MediaPlayer.create(this , Settings.System.DEFAULT_NOTIFICATION_URI);
        player.start();
        //must set loop after start <i do not know why it work>
        player.setLooping(true);
    }
}