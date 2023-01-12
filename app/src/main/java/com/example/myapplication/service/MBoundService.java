package com.example.myapplication.service;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.example.myapplication.R;

public class MBoundService extends Service {
    private final IBinder mBinder = new LocalBinder();
    private final MediaPlayer player = MediaPlayer.create(this , R.raw.drum);

    public class LocalBinder extends Binder{
        public MBoundService getService(){
            return MBoundService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public void playMusic(){
        if(!player.isPlaying())
            player.start();
    }

    public void stopMusic(){
        if(player.isPlaying())
            player.stop();
    }
}
