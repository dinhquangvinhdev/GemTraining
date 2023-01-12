package com.example.myapplication.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;

import java.io.IOException;

public class MessengerService extends Service {
    public static final int MSG_PLAY_MUSIC = 1;
    public static final int MSG_STOP_MUSIC = 2;
    public static final int MSG_PAUSE_MUSIC = 3;

    static class IncomingHandler extends Handler{
        private Context applicationContext;
        private MediaPlayer player;

        IncomingHandler (Context context){
            applicationContext = context.getApplicationContext();
            player = MediaPlayer.create(context , R.raw.drum);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case MSG_PLAY_MUSIC:
                    playMusic();
                    break;
                case  MSG_PAUSE_MUSIC:
                    pauseMusic();
                    break;
                case MSG_STOP_MUSIC:
                    stopMusic();
                    break;
                default:
                    super .handleMessage(msg);
            }
        }

        public void playMusic(){
            if (player!=null && !player.isPlaying())
                player.start();
        }

        public void pauseMusic(){
            if(player!=null && player.isPlaying())
                player.pause();
        }

        public void stopMusic(){
            if(player!= null && player.isPlaying()){
                player.stop();
                try {
                    player.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Messenger messenger;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("bibi", "call onBind create a new Messenger");
        messenger = new Messenger(new IncomingHandler(this));
        return messenger.getBinder();
    }
}
