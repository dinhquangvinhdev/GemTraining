package com.example.myapplication.api;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class DrinksApi {
    private String drinkName = "";
    private DrinkApiCallback drinkApiCallback;

    public void setCallback(DrinkApiCallback drinkApiCallback){
        this.drinkApiCallback = drinkApiCallback;
    }

    @SuppressLint("StaticFieldLeak")
    public String getNewDrink(){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
                Log.d("bibi", "in post of asyncTask");
                drinkApiCallback.getNameNewDrink("Red Eye");
            }
        }.execute();

        return drinkName;
    }

    public interface DrinkApiCallback {
        void getNameNewDrink(String nameDrink);
    }
}
