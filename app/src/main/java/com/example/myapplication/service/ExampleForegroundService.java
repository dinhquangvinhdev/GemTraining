package com.example.myapplication.service;

import static com.example.myapplication.view.TestForegroundActivity.CHANNEL_ID;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.myapplication.R;
import com.example.myapplication.view.TestForegroundActivity;

public class ExampleForegroundService  extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //receive data
        Bundle bundle = intent.getExtras();
        String inputString = bundle.getString("inputString");
        //create pending intent
        Intent notificationIntent = new Intent(this , TestForegroundActivity.class);
        /**
         * flag just defines what happens when we update
         */
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this ,
                0,
                notificationIntent,
                0
        );

        //create notification
        Notification notification = new NotificationCompat.Builder(this ,CHANNEL_ID)
                .setContentTitle("Example Foreground Service")
                .setContentText(inputString)
                .setSmallIcon(R.drawable.img)
                .setContentIntent(pendingIntent)
                .build();

        //in normal we call notify() on the NotificationManager to call notification
        // but in this case the service do itself.
        //start service ( the id is used for updating the notification)
        startForeground(1,notification);

        //return the parameter to decision how to system control the service when kill
        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
