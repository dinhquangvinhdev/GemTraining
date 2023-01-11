package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.EditText;

import com.example.myapplication.R;

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
}