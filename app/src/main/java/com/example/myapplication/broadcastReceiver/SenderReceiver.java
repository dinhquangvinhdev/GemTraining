package com.example.myapplication.broadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Toast;

public class SenderReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().compareTo("action.myaction") == 0){
            Toast.makeText(context , "Sender Receiver" , Toast.LENGTH_LONG).show();
        }
    }
}
