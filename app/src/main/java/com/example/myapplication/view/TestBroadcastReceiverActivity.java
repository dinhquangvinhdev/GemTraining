package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;

import com.example.myapplication.R;
import com.example.myapplication.broadcastReceiver.MBroad;

public class TestBroadcastReceiverActivity extends AppCompatActivity {

    private MBroad mBroad;
    int receiverFlags = 0;
    boolean listenToBroadcastFromOtherApps = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_broadcast_receiver);

        //set receiver flag to false for app's broadcast just receive by this app not for other app
        changeReceiverFlag(false);

        //init broadcast
        mBroad = new MBroad();
        IntentFilter filter = new IntentFilter("android.intent.action.AIRPLANE_MODE");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            registerReceiver(mBroad , filter , receiverFlags);
        }
    }

    private void changeReceiverFlag(boolean listenToBroadcastFromOtherApps) {
        //create a boolean to protect app by other app send unprotected  broadcasts to your app
        if(listenToBroadcastFromOtherApps){
            //already receive intent
            receiverFlags = ContextCompat.RECEIVER_EXPORTED;
        }else{
            //already to receive intent
            receiverFlags = ContextCompat.RECEIVER_NOT_EXPORTED;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(mBroad);
    }
}