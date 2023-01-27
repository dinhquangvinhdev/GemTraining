package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.api.DrinksApi;

public class TestCallbackActivity extends AppCompatActivity implements DrinksApi.DrinkApiCallback {

    private DrinksApi drinksApi;
    private TextView nameDrink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_callback);

        nameDrink = findViewById(R.id.tv_name_drink);

        if(drinksApi == null) {
            drinksApi = new DrinksApi();
            drinksApi.getNewDrink();
            drinksApi.setCallback(this);
        }
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