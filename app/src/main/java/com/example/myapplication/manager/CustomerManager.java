package com.example.myapplication.manager;

import android.content.Context;

import com.example.myapplication.database.DatabaseHandler;
import com.example.myapplication.model.Customer;

import java.util.List;

public class CustomerManager {
    private DatabaseHandler databaseHandler;

    public CustomerManager(Context context){
        databaseHandler = new DatabaseHandler(context,1);
    }

    public long addCustomer(String name , int age){
        return databaseHandler.addCustomer(new Customer(age , name));
    }

    public Customer getCustomer(String name){
        return databaseHandler.queryACustomer(name);
    }

    public List<Customer> getALLCustomer(){
        return databaseHandler.queryAllCustomer();
    }

    public int updateCustomer(Customer customer){return databaseHandler.updateCustomer(customer);}

    public int deleteCustomer(Customer customer){
        return  databaseHandler.deleteCustomer(customer);
    }
}
