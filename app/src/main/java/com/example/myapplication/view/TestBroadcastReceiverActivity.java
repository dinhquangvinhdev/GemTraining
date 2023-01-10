package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.IntentFilter;
import android.os.Bundle;

import com.example.myapplication.R;
import com.example.myapplication.broadcastReceiver.MBroad;

public class TestBroadcastReceiverActivity extends AppCompatActivity {

    private MBroad mBroad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_broadcast_receiver);

        //init broadcast
        mBroad = new MBroad();
        IntentFilter filter = new IntentFilter("android.intent.action.AIRPLANE_MODE");
        registerReceiver(mBroad , filter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(mBroad);
    }
}