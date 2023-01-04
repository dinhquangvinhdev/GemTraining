package com.example.myapplication.view.fragment;

import android.graphics.NinePatch;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.R;
import com.example.myapplication.adapter.MessAdapter;
import com.example.myapplication.mInterface.OnClickFragmentListener;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

public class Fragment1 extends Fragment {

    View view;
    Button btn;
    EditText edt;
    OnClickFragmentListener listener;
    ViewPager2 viewPager2;
    //dot
    private DotsIndicator dotsIndicator;
    private SpringDotsIndicator springDotsIndicator;
    private WormDotsIndicator wormDotsIndicator;
    //interface of viewPager2
    private ViewPager2.OnPageChangeCallback onPageChangeCallback =  new ViewPager2.OnPageChangeCallback() {
        /**
         * Được gọi khi page hiện tại đã được cuộn xong
         * @param position
         * @param positionOffset
         * @param positionOffsetPixels
         */
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            super.onPageScrolled(position, positionOffset, positionOffsetPixels);
        }

        /**
         * Phương thức này được gọi khi page mới được chọn
         * @param position
         */
        @Override
        public void onPageSelected(int position) {
            super.onPageSelected(position);
        }

        /**
         * Được gọi khi được cuộn
         * @param state
         */
        @Override
        public void onPageScrollStateChanged(int state) {
            super.onPageScrollStateChanged(state);
        }
    };

    public Fragment1() {
        super(R.layout.fragment_1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_1, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.d("bib_Frag1","callOnViewCreated");

        //view pager 2
        viewPager2 = view.findViewById(R.id.vp2_testInFrag1);
        viewPager2.setAdapter(new MessAdapter());
        viewPager2.registerOnPageChangeCallback(onPageChangeCallback);

        //init dot
        dotsIndicator = view.findViewById(R.id.dot);
        springDotsIndicator = view.findViewById(R.id.spring_dot);
        wormDotsIndicator = view.findViewById(R.id.worm_dot);
        dotsIndicator.attachTo(viewPager2);
        springDotsIndicator.attachTo(viewPager2);
        wormDotsIndicator.attachTo(viewPager2);

        //get id
        this.btn = view.findViewById(R.id.btn_sendDataFragment1);
        this.edt = view.findViewById(R.id.edt_inputFragment1);



        //init listener
        if(listener ==  null){
            listener = (OnClickFragmentListener) getActivity();
            Log.d("bib_frag2" , "Not Found listener");
        }

        //send data when click
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String result = edt.getText().toString();
                listener.sendData(result);
                Log.d("bib_Frag1","send data: " + result);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        //getData
        edt.setText(listener.getData());
        Log.d("bib_frag1","Get Data from Activity: " + listener.getData() + "|| data in editText: " + edt.getText().toString());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        viewPager2.unregisterOnPageChangeCallback(onPageChangeCallback);
    }
}