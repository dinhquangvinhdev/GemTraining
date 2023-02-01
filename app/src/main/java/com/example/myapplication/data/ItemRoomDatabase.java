package com.example.myapplication.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.myapplication.model.Item;

import kotlin.jvm.Volatile;

/**
 * entities là các lớp đối tượng
 * version là phiên bản
 * exportSchema : false => update mất hết dữ liệu cũ / true là ngược lại
 */
@Database(entities = {Item.class}, version = 1, exportSchema = false)
public abstract class ItemRoomDatabase extends RoomDatabase {
    public abstract ItemDAO itemDao();
    private static ItemRoomDatabase INSTANCE;

    /**
     * use singleton for save the memory
     * @param context
     * @return
     */
    public static ItemRoomDatabase getINSTANCE(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.inMemoryDatabaseBuilder(context.getApplicationContext(), ItemRoomDatabase.class)
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
    
    public static void destroyInstance(){
        INSTANCE = null;
    }
}
