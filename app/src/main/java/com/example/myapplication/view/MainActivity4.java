package com.example.myapplication.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Toast;

import com.example.myapplication.adapter.IntegerBaseAdapter;
import com.example.myapplication.animation.MyRecyclerViewAnimator;
import com.example.myapplication.databinding.ActivityMain4Binding;

import java.util.ArrayList;

public class MainActivity4 extends AppCompatActivity {

    ActivityMain4Binding binding;
    private IntegerBaseAdapter adapter;
    /*value in save state for rotating the screen*/
    private final String KEY_STATE = "KEY_STATE";
    private int sum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain4Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /*check instance state for revival data*/
        checkRevivalData(savedInstanceState);

        //create adapter
        adapter = new IntegerBaseAdapter();
        binding.rvData.setAdapter(adapter);
        LinearLayoutManager  llm = new LinearLayoutManager(this , LinearLayoutManager.VERTICAL , true);
        llm.setStackFromEnd(false);
        binding.rvData.setLayoutManager(llm);
        binding.rvData.setItemAnimator(new MyRecyclerViewAnimator());

        binding.btnSum.setOnClickListener(new View.OnClickListener(){
            @Override
                public void onClick(View view) {
                caculAB();
                //update recycleView
                Toast.makeText(getApplication(),String.valueOf(sum),Toast.LENGTH_SHORT).show();
                adapter.addNewNumber(sum);
            }
        });
    }

    private void checkRevivalData(Bundle savedInstanceState) {
        if(savedInstanceState != null){
            sum = savedInstanceState.getInt(KEY_STATE , 0);
            binding.tvResultSum.setText(String.valueOf(sum));
        }
    }

    private void caculAB() {
        //TODO need to check string not null
        int a = Integer.parseInt(binding.edtInputA.getText().toString());
        int b = Integer.parseInt(binding.edtInputB.getText().toString());
        this.sum = a + b;
        binding.tvResultSum.setText(String.valueOf(sum));
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_STATE,sum);
    }
}