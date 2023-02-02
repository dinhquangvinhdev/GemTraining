package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.adapter.NewAdapter;
import com.example.myapplication.controller.ClientController;
import com.example.myapplication.databinding.ActivityTestMapiBinding;
import com.example.myapplication.model.New;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestMApiActivity extends AppCompatActivity {
    private ActivityTestMapiBinding binding;
    private NewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTestMapiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        processData();
    }

    private void processData() {
        //get news through Api
        Call<List<New>> call = ClientController.getInstance().getApi().getData();
        //add request to enqueue
        call.enqueue(new Callback<List<New>>() {
            @Override
            public void onResponse(Call<List<New>> call, Response<List<New>> response) {
                List<New> data = response.body();
                adapter = new NewAdapter(data);
                binding.recyclerView.setAdapter(adapter);
                binding.progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<List<New>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "failed for requesting api" , Toast.LENGTH_SHORT).show();
            }
        });
    }
}