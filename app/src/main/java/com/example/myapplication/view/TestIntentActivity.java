package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.example.myapplication.R;

public class TestIntentActivity extends AppCompatActivity {

    private Button btnIntentImplicit;
    private Button btnIntentExplicit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_intent);

        //get id
        btnIntentImplicit = findViewById(R.id.btn_intentImplicit);
        btnIntentExplicit = findViewById(R.id.btn_intentExplicit);

        //do something in here
    }
}