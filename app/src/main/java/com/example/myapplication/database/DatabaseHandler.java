package com.example.myapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.myapplication.model.Customer;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "customer_db";
    private static final String TABLE_NAME = "CUSTOMER";
    private static final String KEY_NAME = "name";
    private static final String KEY_AGE = "age";

    private static final int CUSTOMER_AGE = 10;
    private static final String CUSTOMER_NAME = "Some One";



    public DatabaseHandler(@Nullable Context context, int version) {
        super(context, DATABASE_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d("bibi", "create a new database");
        String createCustomer = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY,%s TEXT)"
                , TABLE_NAME
                , KEY_AGE
                , KEY_NAME);
        // exec the command SQL
        sqLiteDatabase.execSQL(createCustomer);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.d("bibi", "update customer database");
        String drop_customer_table =  "DROP TABLE IF EXISTS " + TABLE_NAME;
        sqLiteDatabase.execSQL(drop_customer_table);
        onCreate(sqLiteDatabase);
    }

    public long addCustomer(Customer customer){
        //using SQLiteDatabase for have more instance
        SQLiteDatabase db = this.getReadableDatabase();
        //using contentValues for update data for each row
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, customer.getName());
        values.put(KEY_AGE, customer.getAge());
        long isAdd = db.insert(TABLE_NAME, null, values); // it will return the id row
        //remember for closing
        db.close();

        return isAdd;
    }

    /**
     * This func will return null if not find any customer in database
     * @param name
     * @return
     */
    public Customer queryACustomer(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        //create a cursor for read the db
        Cursor cursor = db.query(TABLE_NAME , null , KEY_NAME + " = ?",
                new String[]{name}, null , null, null);
        if(cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            Customer customer = new Customer(cursor.getInt(0), cursor.getString(1));
            return customer;
        } else {
            Log.d("bibi", "not found any customer");
        }
        cursor.close();
        return null;
    }

    public List<Customer> queryAllCustomer(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Customer> arr = new ArrayList<>();
        //create a cursor for read the db
        Cursor cursor = db.query(TABLE_NAME , null ,null , null ,null, null,null);
        if(cursor != null && cursor.getCount() > 0){
            while (cursor.moveToNext()){
                Customer customer = new Customer(cursor.getInt(0), cursor.getString(1));
                Log.d("bibi", customer.toString());
                arr.add(customer);
            }
        }
        return arr;
    }

    public int updateCustomer(Customer customer){
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_AGE, customer.getAge());
        contentValues.put(KEY_NAME , customer.getName());

        int isUpdating = db.update(TABLE_NAME , contentValues , KEY_AGE + " = ?", new String[]{String.valueOf(customer.getAge())});
        db.close();

        return isUpdating;
    }

    public int deleteCustomer(Customer customer){
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME , customer.getName());
        contentValues.put(KEY_AGE , customer.getAge());

        int isDelete = db.delete(TABLE_NAME, KEY_AGE + " = ?", new String[]{String.valueOf(customer.getAge())});
        db.close();

        return isDelete;
    }
}
