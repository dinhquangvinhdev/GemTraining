package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class JobAdapter extends BaseAdapter {
    private Context context;
    private List<String> mArrJob;

    public JobAdapter(Context context, List<String> arr) {
        updateData(arr);
        this.context = context;
    }

    private void updateData(List<String> arr) {
        if (mArrJob == null) {
            mArrJob = new ArrayList<>();
            mArrJob.addAll(arr);
        } else {
            mArrJob.clear();
            mArrJob.addAll(arr);
        }
    }

    @Override
    public int getCount() {
        return mArrJob.size();
    }

    @Override
    public Object getItem(int i) {
        return mArrJob.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        //inflate the layout for each row
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_job, viewGroup, false);
            viewHolder = new ViewHolder(view);
            //using tag for storing references of rows' view
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        //get item
        String item = (String) getItem(i);

        //set the text for TextView
        viewHolder.tv.setText(item);

        return view;
    }

    private class ViewHolder {
        TextView tv;

        public ViewHolder(View view) {
            tv = view.findViewById(R.id.tv_title_job);
        }

    }

}
