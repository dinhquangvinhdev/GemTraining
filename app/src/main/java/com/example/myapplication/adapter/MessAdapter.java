package com.example.myapplication.adapter;

import android.content.res.ColorStateList;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.Arrays;

public class MessAdapter extends RecyclerView.Adapter<MessAdapter.ViewHolder> {

    private ArrayList<Integer> colors = new ArrayList<>(
        Arrays.asList(
                R.color.black,
                R.color.white,
                R.color.teal_200,
                R.color.purple_200)
    );

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv_fragMess);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_mess,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String tempTitle = holder.tv.getText().toString();
        holder.tv.setText(tempTitle + " " + position);
        holder.itemView.setBackgroundColor(
                holder.itemView.getResources().getColor(colors.get(position)));
    }

    @Override
    public int getItemCount() {
        return colors.size();
    }
}
