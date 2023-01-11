package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

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
import com.example.myapplication.broadcastReceiver.OrderBroadcast1;
import com.example.myapplication.broadcastReceiver.OrderBroadcast2;
import com.example.myapplication.broadcastReceiver.SenderReceiver;

public class TestBroadcastReceiverActivity extends AppCompatActivity {
    private SenderReceiver senderReceiver;
    private MBroad mBroad;
    private LocalBroadcastManager localBroadcastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_broadcast_receiver);

        //set receiver flag to false for app's broadcast just receive by this app not for other app
        // it need implement androidx library but library required version android 33 ## LOL
//        changeReceiverFlag(false);

        //init localBroadcastManager
        localBroadcastManager = LocalBroadcastManager.getInstance(this);

        //register a receiver
        IntentFilter filter = new IntentFilter("action.myaction");
        senderReceiver = new SenderReceiver();
        mBroad = new MBroad();
        localBroadcastManager.registerReceiver(senderReceiver , filter);
        localBroadcastManager.registerReceiver(mBroad , filter);

        //test sendBroadcast(Intent)
        //testSendBroadcastNormal();
        //test sendOrderedBroadcast(Intent , String)
        //testSendBroadcastHigher();
        //test LocalBroadcastManager
        testLocalBroadcastManager();
    }

    private void testLocalBroadcastManager() {
        Intent intent = new Intent("action.myaction");
        intent.setPackage("com.example.myapplication");
        Bundle bundle = new Bundle();
        bundle.putString("stringExtra", "Start");
        intent.putExtra("bundle" , bundle);
        localBroadcastManager.sendBroadcast(intent);
    }

    private void testSendBroadcastHigher() {
        Intent intent = new Intent("action.myaction");
        Bundle bundle = new Bundle();
        bundle.putString("stringExtra", "Start");
        sendOrderedBroadcast(intent,null, new OrderBroadcast2(), null , 0 , "Start" , bundle);
    }

    private void testSendBroadcastNormal() {
        Intent intent = new Intent("action.myaction");
        intent.putExtra("stringExtra", "Start");
        sendBroadcast(intent);
    }

    @Override
    protected void onStop() {
        super.onStop();
        localBroadcastManager.unregisterReceiver(senderReceiver);
        localBroadcastManager.unregisterReceiver(mBroad);
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
//    }
}