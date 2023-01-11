package com.example.myapplication.broadcastReceiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class OrderBroadcast2 extends BroadcastReceiver {
    private int data;
    private final String THIS_PACKAGE= "OrderBroadcast2";

    @Override
    public void onReceive(Context context, Intent intent) {
        switch (intent.getAction()){
            case "action.myaction":
                int resultCode = getResultCode();
                String resultData = getResultData();
                Bundle resultExtras = getResultExtras(true);
                String stringExtra = resultExtras.getString("stringExtra");
                /**
                 * Activity.RESULT_OK = -1 || Activity.RESULT_CANCELED = 0 || Activity.RESULT_FIRST_USER = 1
                 * So we need change resultCode from 0 to 1 to make it continue go to other broadcast
                 */
                resultCode++;
                //update data after get from intent
                stringExtra += "->OrderBroadcast2";

                String toastText = "OrderBroadcast2\n" +
                        "resultCode: " + resultCode + "\n" +
                        "resultData: " + resultData + "\n" +
                        "intExtra: " + stringExtra;
                //Toast data to see
                Toast.makeText(context , toastText , Toast.LENGTH_LONG).show();
                resultData = "OrderBroadcast2";
                //send bundle to next broadcast
                resultExtras.putString("stringExtra" , stringExtra);
                //set result and send data to the other broadcast
                setResult(resultCode , resultData ,resultExtras);
                break;
            default:
                Log.d("bibi",intent.getAction() + " can not run in this app");
                break;
        }
    }
}
