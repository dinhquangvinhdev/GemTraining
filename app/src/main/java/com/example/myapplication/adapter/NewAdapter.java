package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ItemNewBinding;
import com.example.myapplication.mInterface.OnClickItemAdapter;
import com.example.myapplication.model.New;

import java.util.List;


public class NewAdapter extends RecyclerView.Adapter<NewAdapter.MyViewHolder> {
    private List<New> data;
    private OnClickItemAdapter onClickItemAdapter;

    public NewAdapter(List<New> arr, Context onClickItemAdapter){
        this.data = arr;
        this.onClickItemAdapter = (OnClickItemAdapter) onClickItemAdapter;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView idTv,titleTv,bodyTv;
        private OnClickItemAdapter onClickItemAdapter;

        public MyViewHolder(@NonNull ItemNewBinding binding) {
            super(binding.getRoot());
            idTv = binding.id;
            titleTv = binding.title;
            bodyTv = binding.body;
            //set onClickListener for adapter
            binding.getRoot().setOnClickListener(this);
        }

        public void setItemClickListener(OnClickItemAdapter onItemClickListener){
            this.onClickItemAdapter = onItemClickListener;
        }

        @Override
        public void onClick(View view) {
            onClickItemAdapter.onClickItem(getAdapterPosition());
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
        holder.idTv.setText(String.valueOf(data.get(position).getId()));
        holder.bodyTv.setText(data.get(position).getBody());
        holder.titleTv.setText(data.get(position).getTitle());
        //set callback click item
        holder.setItemClickListener(this.onClickItemAdapter);
    }

    @Override
    public int getItemCount() {
        if(data == null){return 0;}
        else {return data.size();}
    }


}
