package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;

public class TestIntentActivity extends AppCompatActivity {

    private Button btnIntentImplicit;
    private Button btnIntentExplicit;
    public static String KEY_INTENT_EXPLICIT = "KEY_INTENT_EXPLICIT ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_intent);

        //get id
        btnIntentImplicit = findViewById(R.id.btn_intentImplicit);
        btnIntentExplicit = findViewById(R.id.btn_intentExplicit);

        btnIntentExplicit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TestIntentActivity.this, SplashActivity.class);
                //use Extra
                intent.putExtra(KEY_INTENT_EXPLICIT , 10);
                startActivity(intent);
            }
        });

        btnIntentImplicit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        });
    }
}