package com.example.myapplication.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.R;
import com.example.myapplication.mInterface.OnClickFragmentListener;

public class Fragment2 extends Fragment {

    View view;
    Button btn;
    EditText edt;
    OnClickFragmentListener listener;

    public Fragment2() {
        super(R.layout.fragment_2);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_2, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.d("bib_Frag2","callOnViewCreated");

        //get id
        this.btn = view.findViewById(R.id.btn_sendDataFragment2);
        this.edt = view.findViewById(R.id.edt_inputFragment2);

        //init listener
        if(listener == null){
            listener = (OnClickFragmentListener) getActivity();
            Log.d("bib_frag2" , "Not Found listener");
        }

        //sendData
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String result = edt.getText().toString();
                listener.sendData(result);
                Log.d("bib_Frag2","send data: " + result);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if(listener != null){
            //getData
            Log.d("bib_frag2","data in editText before update: " + edt.getText().toString());
            edt.setText(listener.getData());
            Log.d("bib_frag2","Get Data from Activity: " + listener.getData() + "|| data in editText: " + edt.getText().toString());
        }
    }
}