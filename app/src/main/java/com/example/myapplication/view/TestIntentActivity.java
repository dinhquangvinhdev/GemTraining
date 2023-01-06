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
    }
}