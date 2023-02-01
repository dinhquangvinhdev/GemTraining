package com.example.myapplication.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myapplication.model.Item;

import java.util.List;

@Dao
public interface ItemDAO {
    @Query("SELECT * FROM items")
    List<Item> getAllItems();

    @Query("SELECT * FROM items WHERE id in (:id)") // this :id must same name the parameter in function
    List<Item> getItemById(int id);

    /**
     * ta sử dụng OnConflictStrategy.IGNORE để xử lý trường hợp mã id của item trùng nhau ==> bỏ qua item đó
     * @param item
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertItem(Item item);

    @Update
    void updateItem(Item item);

    @Update
    void updateItem(int id);

    @Delete
    void deleteItem(Item item);

    @Delete
    void deleteItem(int id);

}
