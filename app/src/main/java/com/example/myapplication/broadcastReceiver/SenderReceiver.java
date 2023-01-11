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
        int resultCode = getResultCode();
        String resultData = getResultData();
        Bundle resultExtras = getResultExtras(true);
        String stringExtra = resultExtras.getString("stringExtra");
        //why we need to add resultCode???
        resultCode++;
        //update data after get from intent
        stringExtra += "->SenderReceiver";

        String toastText = "SenderReceiver\n" +
                "resultCode: " + resultCode + "\n" +
                "resultData: " + resultData + "\n" +
                "intExtra: " + stringExtra;
        //Toast data to see
        Toast.makeText(context , toastText , Toast.LENGTH_LONG).show();
        resultData = "SenderReceiver";
        //send bundle to next broadcast
        resultExtras.putString("stringExtra" , stringExtra);
        //set result and send data to the other broadcast
        setResult(resultCode , resultData ,resultExtras);
    }
}
