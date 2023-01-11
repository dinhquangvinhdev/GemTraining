package com.example.myapplication.broadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

/**
 * This class is use for processing the intent receive by broadcast
 */
public class MBroad extends BroadcastReceiver {
    private String data = "";
    private final String THIS_PACKAGE= "MBroad";

    @Override
    public void onReceive(Context context, Intent intent) {
        switch (intent.getAction()){
            case "action.myaction":
                Toast.makeText(context , "MBroad", Toast.LENGTH_LONG).show();
                break;
            case "android.intent.action.AIRPLANE_MODE":
                Toast.makeText(context, "change airplant", Toast.LENGTH_SHORT ).show();
                break;
            default:
                Log.d("bibi",intent.getAction() + " can not run in this app");
                break;
        }
    }
}
