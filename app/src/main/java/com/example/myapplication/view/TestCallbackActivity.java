package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.JobAdapter;
import com.example.myapplication.api.DrinksApi;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestCallbackActivity extends AppCompatActivity implements DrinksApi.DrinkApiCallback {

    private DrinksApi drinksApi;
    private TextView nameDrink;
    private ListView lvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_callback);

        setUI();
        // set adapter for lv
        ArrayList<String> mArr = new ArrayList<String>(Arrays.asList("Job1", "Job2","Job3","Job4","Job5","Job6","Job7","Job8","Job9"));
        JobAdapter arrayAdapter = new JobAdapter(this, mArr);
        lvData.setAdapter(arrayAdapter);

        //test call back
        if(drinksApi == null) {
            drinksApi = new DrinksApi();
            drinksApi.getNewDrink();
            drinksApi.setCallback(this);
        }
    }

    private void setUI() {
        nameDrink = findViewById(R.id.tv_name_drink);
        lvData = findViewById(R.id.lv_data);
    }

    @Override
    protected void onStop() {
        super.onStop();
        drinksApi = null;
    }

    @Override
    public void getNameNewDrink(String nameDrink) {
        Log.d("bibi", nameDrink);
        this.nameDrink.setText(this.nameDrink.getText() + " " + nameDrink);
    }
}