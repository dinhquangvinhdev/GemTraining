package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.broadcastReceiver.MBroad;

public class TestBroadcastReceiverActivity extends AppCompatActivity {

    private MBroad mBroad;
    int receiverFlags = 0;
    boolean listenToBroadcastFromOtherApps = false;
    private int data = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_broadcast_receiver);

        //set receiver flag to false for app's broadcast just receive by this app not for other app
        // it need implement androidx library but library required version android 33 ## LOL
//        changeReceiverFlag(false);

        //init broadcast dynamic
        mBroad = new MBroad();
        IntentFilter filter = new IntentFilter("android.intent.action.AIRPLANE_MODE");
        filter.addAction("action.myaction");
        registerReceiver(mBroad , filter);

        //normal test send broad cast normal
        testSendBroadcastNormal();
    }

    private void testSendBroadcastNormal() {
        Intent intent = new Intent("action.myaction");
        intent.putExtra("action",1);
        sendBroadcast(intent);
    }

//    private void changeReceiverFlag(boolean listenToBroadcastFromOtherApps) {
//        //create a boolean to protect app by other app send unprotected  broadcasts to your app
//        if(listenToBroadcastFromOtherApps){
//            //already receive intent
//            receiverFlags = ContextCompat.RECEIVER_EXPORTED;
//        }else{
//            //already to receive intent
//            receiverFlags = ContextCompat.RECEIVER_NOT_EXPORTED;
//        }
//    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(mBroad);
    }
}