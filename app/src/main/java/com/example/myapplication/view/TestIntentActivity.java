package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;

import java.util.List;

public class TestIntentActivity extends AppCompatActivity {

    private Button btnIntentExplicit;
    private Button btnSendMail;
    private Button btnOpenApp;
    public static String KEY_INTENT_EXPLICIT = "KEY_INTENT_EXPLICIT ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_intent);

        //get id
        btnIntentExplicit = findViewById(R.id.btn_intentExplicit);
        btnSendMail = findViewById(R.id.btn_intentImplicit1);
        btnOpenApp = findViewById(R.id.btn_intentImplicit2);


        btnIntentExplicit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TestIntentActivity.this, SplashActivity.class);
                //use Extra
                intent.putExtra(KEY_INTENT_EXPLICIT , 10);
                startActivity(intent);
            }
        });

        btnSendMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMail();
            }
        });

        btnOpenApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openOtherApp("com.example.placeofdestruction");
            }
        });
    }

    private void openOtherApp(String mPackage) {
        Intent openApp = getPackageManager().getLaunchIntentForPackage(mPackage);
        //check if not found
        if(openApp != null){
            Log.d("bibi","intent can open");
            //add Flag <option>
            openApp.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(openApp);
        }else {
            Log.d("bibi","can not open itent");
        }
    }

    private void sendMail() {
        // create Intent with action = ACTION_SEND
        Intent sendMail = new Intent(Intent.ACTION_SEND);
        // set Type for data send
        sendMail.setType("message/rfc822");
        // put Extra (key-value)
        sendMail.putExtra(Intent.EXTRA_EMAIL, new String[]{"dinhquangvinhdev@gmail.com"});
        sendMail.putExtra(Intent.EXTRA_SUBJECT , "Title :This is a test for using Intent Implicit to send email");
        sendMail.putExtra(Intent.EXTRA_TEXT , "Text: This is Text , MY BODY ~~~~");
        startActivity(Intent.createChooser(sendMail,"Choose Mail app"));
    }
}