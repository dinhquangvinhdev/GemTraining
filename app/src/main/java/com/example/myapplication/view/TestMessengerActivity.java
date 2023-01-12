package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.service.MessengerService;

public class TestMessengerActivity extends AppCompatActivity {

    private Messenger mService = null;
    boolean mBound = false;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mService = new Messenger(iBinder);
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mService = null;
            mBound = false;
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        /**
         * The first parameter is explicit intent
         * The second parameter is ServiceConnection
         * The thỉdd parameter should set Context.BIND_AUTO_CREATE
         */
        bindService(new Intent(this , MessengerService.class), serviceConnection , Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_messenger);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("bibi", "Call onStop in Activity");
        if(mBound){
            unbindService(serviceConnection);
            mBound = false;
        }
    }

    public void sendMessStartMusic(View view){
        Log.d("bibi" , "on click start mess service");
        if(mBound){
            Message msg = Message.obtain(null , MessengerService.MSG_PLAY_MUSIC , 0 , 0);
            sendMess(msg);
        }
    }

    public void sendMessPauseMusic(View view){
        Log.d("bibi" , "on click pause mess service");
        if(mBound){
            Message msg = Message.obtain(null , MessengerService.MSG_PAUSE_MUSIC , 0 , 0);
            sendMess(msg);
        }
    }

    public void sendMessStopMusic(View view){
        Log.d("bibi" , "on click stop mess service");
        if(mBound){
            Message msg = Message.obtain(null , MessengerService.MSG_STOP_MUSIC , 0 , 0);
            sendMess(msg);
        }
    }

    private void sendMess(Message msg) {
        try {
            mService.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void openOtherActivity(View view) {
        Intent intent = new Intent(this , SplashActivity.class);
        startActivity(intent);
    }
}