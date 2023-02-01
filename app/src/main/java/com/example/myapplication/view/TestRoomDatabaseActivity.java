package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;

import android.nfc.FormatException;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.data.ItemRoomDatabase;
import com.example.myapplication.databinding.ActivityTestRoomDatabaseBinding;
import com.example.myapplication.model.Item;

import java.time.LocalDateTime;
import java.util.List;

public class TestRoomDatabaseActivity extends AppCompatActivity {
    private ActivityTestRoomDatabaseBinding binding;
    private ItemRoomDatabase mdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTestRoomDatabaseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //create database
        mdb = ItemRoomDatabase.getINSTANCE(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //remember destroy it, this is just example not solution
        ItemRoomDatabase.destroyInstance();
    }

    /**
     * TODO handle exception input null
     * @param view
     */
    public void addAnItem(View view) {
        Item item = new Item();
        item.setNameItem(binding.edtItemName.getText().toString());
        mdb.itemDao().insertItem(item);
        binding.edtItemName.requestFocus();
        Toast.makeText(this , "add success", Toast.LENGTH_SHORT).show();
    }

    /**
     * In this function just get an item by id
     * you can custom it
     * TODO handle exception input wrong type
     * @param view
     */
    public void getAnItem(View view) {
        String id = binding.edtItemId.getText().toString();
        if(!id.isEmpty()){
            List<Item> arr = mdb.itemDao().getItemById(Integer.parseInt(id));
            if(arr.isEmpty())
                Toast.makeText(this,"Not found item have id: " + id, Toast.LENGTH_SHORT).show();
            for(Item item : arr){
                Log.d("bibi", item.toString());
            }
            return;
        }
        Toast.makeText(this ,"failed to get item", Toast.LENGTH_SHORT).show();
    }

    public void getAllItem(View view) {
        List<Item> arr = mdb.itemDao().getAllItems();
        for(Item item : arr){
            Log.d("bibi", item.toString());
        }
    }

    /**
     * In this function just update an item by id
     * you can custom it
     * TODO handle exception input wrong type
     * TODO not found id in data
     * @param view
     */
    public void updateAnItem(View view) {
        String id = binding.edtItemId.getText().toString();
        String name = binding.edtItemName.getText().toString();
        if(!id.isEmpty()){
            mdb.itemDao().updateItem(new Item(Integer.parseInt(id), name));
            Toast.makeText(this ,"Update success", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this ,"Please fill input id", Toast.LENGTH_SHORT).show();
    }

    public void deleteAnItem(View view) {
        String id = binding.edtItemId.getText().toString();
        if(!id.isEmpty()){
            mdb.itemDao().deleteItem(new Item(Integer.parseInt(id)));
            Toast.makeText(this ,"Delete success", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this ,"Please fill input id", Toast.LENGTH_SHORT).show();
    }
}