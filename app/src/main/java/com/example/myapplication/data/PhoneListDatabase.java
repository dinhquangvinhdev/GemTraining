package com.example.myapplication.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PhoneListDatabase extends SQLiteOpenHelper {
    private static final String DEBUG_TAG = "PhoneListDatabase";
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "PhoneListDatabase";

    public PhoneListDatabase(Context context){
        super(context,DB_NAME , null , DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
