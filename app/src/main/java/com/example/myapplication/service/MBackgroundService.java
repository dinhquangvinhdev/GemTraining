package com.example.myapplication.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.provider.Settings;

import androidx.annotation.Nullable;

import com.example.myapplication.R;

public class MBackgroundService extends Service {
    private MediaPlayer player;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if(player == null){
            player = MediaPlayer.create(this , R.raw.baddest);
            player.start();
        } else if (!player.isPlaying()){
            player.start();
        }

        //in this test you will not recognize the difference between them but keep it in mind
        //START_NOT_STICKY: the service will be destroy can not recover
        return START_NOT_STICKY;
        //START_STICKY: the service will return intent to null
//        return START_STICKY;
        //START_REDELIVER_INTENT: the service will return the last intent
//        return START_REDELIVER_INTENT;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(player != null) {
            player.stop();
            player.release();
            player = null;
        }
    }
}
