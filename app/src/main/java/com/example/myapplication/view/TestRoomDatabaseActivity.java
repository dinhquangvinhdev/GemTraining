package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityTestRoomDatabaseBinding;

public class TestRoomDatabaseActivity extends AppCompatActivity {
    private ActivityTestRoomDatabaseBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTestRoomDatabaseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    public void addAnItem(View view) {
    }

    public void getAnItem(View view) {
    }

    public void getAllItem(View view) {
    }

    public void updateAnItem(View view) {
    }

    public void deleteAnItem(View view) {
    }
}