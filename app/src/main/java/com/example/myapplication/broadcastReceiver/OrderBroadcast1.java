package com.example.myapplication.broadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class OrderBroadcast1 extends BroadcastReceiver {
    private int data;
    private final String THIS_PACKAGE= "OrderBroadcast1";

    @Override
    public void onReceive(Context context, Intent intent) {
        switch (intent.getAction()){
            case "action.myaction":
                int resultCode = getResultCode();
                String resultData = getResultData();
                Bundle resultExtras = getResultExtras(true);
                String stringExtra = resultExtras.getString("stringExtra");
                //why we need to add resultCode???
                resultCode++;
                //update data after get from intent
                stringExtra += "->OrderBroadcast1";

                String toastText = "OrderBroadcast1\n" +
                        "resultCode: " + resultCode + "\n" +
                        "resultData: " + resultData + "\n" +
                        "intExtra: " + stringExtra;
                //Toast data to see
                Toast.makeText(context , toastText , Toast.LENGTH_LONG).show();
                resultData = "OrderBroadcast1";
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
