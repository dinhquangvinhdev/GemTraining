package com.example.myapplication.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Diff.IntegerDiffUtilCallBack;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import kotlin.io.OnErrorAction;

public class IntegerBaseAdapter extends RecyclerView.Adapter<IntegerBaseAdapter.ViewHolder> implements IIntegerBaseAdapter {

    private List<Integer> oldListInt;
    private static final int FONT1 = 1;
    private static final int FONT2 = 2;


    public IntegerBaseAdapter() {
        if(oldListInt == null){
            this.oldListInt = new ArrayList<>();
        }
    }

    @Override
    public void addNewNumber(int newNumber) {
        if(oldListInt != null){
            List<Integer> newListInt = oldListInt;
            newListInt.add(0,newNumber);
            setData(newListInt);
            Log.d("bibibla_IntAdapter",String.valueOf(oldListInt.size()));
        }
        else{
            Log.d("bibibla_IntAdapter","null list");
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvInt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvInt = itemView.findViewById(R.id.tv_int);
        }
    }

    @NonNull
    @Override
    public IntegerBaseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        switch (viewType){
            case FONT1:{
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_integer1, parent, false);
                break;
            }
            case FONT2:{
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_integer2,parent, false);
                break;
            }
            default:{
                Log.d("bibibla_InAdapter","viewType error");
                itemView = null;
                break;
            }
        }
        return new ViewHolder(itemView);
    }

    @Override
    public int getItemViewType(int position) {
        if(position % 2 == 0) {return FONT1;}
        else {return FONT2;}
    }

    @Override
    public void onBindViewHolder(@NonNull IntegerBaseAdapter.ViewHolder holder, int position) {
        int temp = oldListInt.get(position);
        holder.tvInt.setText(String.valueOf(temp));
    }

    @Override
    public int getItemCount() {
        return oldListInt != null ? oldListInt.size() : 0;
    }

    void setData(List<Integer> newListInt)
    {
        IntegerDiffUtilCallBack mDiff = new IntegerDiffUtilCallBack(newListInt , oldListInt);
        DiffUtil.DiffResult diffResults = DiffUtil.calculateDiff(mDiff);
        oldListInt = newListInt;
        diffResults.dispatchUpdatesTo(this);
    }


}
