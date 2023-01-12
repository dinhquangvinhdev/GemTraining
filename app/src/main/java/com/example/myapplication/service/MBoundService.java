package com.example.myapplication.service;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.myapplication.R;

import java.io.IOException;

public class MBoundService extends Service {
    private final IBinder mBinder = new LocalBinder();
    private MediaPlayer player;

    @Override
    public void onCreate() {
        Log.d("bibi", "running onCreate of Service");
        player = MediaPlayer.create(this , R.raw.drum);
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("bibi", "running onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    public class LocalBinder extends Binder{
        public MBoundService getService(){
            return MBoundService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("bibi", "running onBind of Service");
        return mBinder;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("bibi", "running onDestroy of Service");
        if(player != null){
            player.stop();
            player.release();
            player = null;
        }
    }

    public void playMusic(){
        if(!player.isPlaying())
            player.start();
    }

    public void stopMusic(){
        if(player.isPlaying()){
            player.stop();
            try {
                player.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
