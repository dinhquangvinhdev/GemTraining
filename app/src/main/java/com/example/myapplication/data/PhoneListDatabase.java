package com.example.myapplication.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PhoneListDatabase extends SQLiteOpenHelper {
    private static final String DEBUG_TAG = "PhoneListDatabase";
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "PhoneListDatabase";

    //attribute for Database schema
    public static final String PHONE_TABLE_NAME = "phones";
    public static final String ID = "_id";
    public static final String COL_NUMBER = "phone_number";
    public static final String COL_USER = "user";
    private static final String QUERY_CREATE_TABLE_PHONELIST =
            "create table " + PHONE_TABLE_NAME + " (" + ID + " integer primary key autoincrement, "
            + COL_NUMBER + " text not null, " + COL_USER + " text not null);";
    private static final String DB_SCHEMA = QUERY_CREATE_TABLE_PHONELIST;

    public PhoneListDatabase(Context context){
        super(context,DB_NAME , null , DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //create database
        sqLiteDatabase.execSQL(DB_SCHEMA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
