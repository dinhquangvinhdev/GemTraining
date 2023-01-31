package com.example.myapplication.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;


public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "CUSTOMER";
    private static final int CUSTOMER_AGE = 10;
    private static final String CUSTOMER_NAME = "Some One";


    public DatabaseHandler(@Nullable Context context, @Nullable String name,
                           @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d("bibi", "create a new database");
        String createCustomer = String.format("CREATE TABLE %s(%s INTEGER PRIMARY, %s TEXT)"
                , TABLE_NAME
                , CUSTOMER_AGE
                , CUSTOMER_NAME);
        // execl the command SQL
        sqLiteDatabase.execSQL(createCustomer);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.d("bibi", "update customer database");
    }
}
