package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ItemNewBinding;
import com.example.myapplication.model.New;

import java.util.List;


public class NewAdapter extends RecyclerView.Adapter<NewAdapter.MyViewHolder> {
    private List<New> data;

    public NewAdapter(List<New> arr){
        this.data = arr;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView idTv,titleTv,bodyTv;

        public MyViewHolder(@NonNull ItemNewBinding binding) {
            super(binding.getRoot());
            idTv = binding.id;
            titleTv = binding.title;
            bodyTv = binding.body;
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemNewBinding binding = ItemNewBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent , false
        );
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.idTv.setText(data.get(position).getId());
        holder.bodyTv.setText(data.get(position).getId());
        holder.titleTv.setText(data.get(position).getId());
    }

    @Override
    public int getItemCount() {
        if(data == null){return 0;}
        else {return data.size();}
    }


}
