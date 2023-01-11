package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.myapplication.R;
import com.example.myapplication.service.ExampleForegroundService;

public class TestForegroundActivity extends AppCompatActivity {

    public static final String CHANNEL_ID = "exampleServiceChannel";
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_foreground);

        createChannelNotification();

        editText = findViewById(R.id.edt_input_data);
    }

    private void createChannelNotification() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            //create notificationChannel
            NotificationChannel notificationChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "This is my test notification channel",
                    NotificationManager.IMPORTANCE_HIGH
            );
            //create notification manager to run it
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    public void startService(View v){
        String input = editText.getText().toString();

        Intent intent = new Intent(this , ExampleForegroundService.class);
        Bundle bundle = new Bundle();
        bundle.putString("inputString", input);
        intent.putExtra("data" ,bundle);
        startService(intent);
    }

    public void stopService(View v){
        Intent intent = new Intent(this , ExampleForegroundService.class);
        stopService(intent);
    }
}