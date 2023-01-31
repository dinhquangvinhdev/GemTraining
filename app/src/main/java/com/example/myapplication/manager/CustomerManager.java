package com.example.myapplication.manager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.database.DatabaseHandler;
import com.example.myapplication.model.Customer;

public class CustomerManager {
    private DatabaseHandler databaseHandler;

    public CustomerManager(Context context){
        databaseHandler = new DatabaseHandler(context,1);
    }

    public void addCustomer(String name , int age){
        databaseHandler.addCustomer(new Customer(age , name));
    }

    public Customer getCustomer(String name){
        return databaseHandler.queryACustomer(name);
    }
}
