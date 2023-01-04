package com.example.myapplication.view.fragment;

import android.content.Context;
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
import android.widget.TextView;

import com.example.myapplication.R;

import java.sql.Time;

public class TimeFragment extends Fragment {

    private TextView tvFrag1;
    private EditText edtFrag1;
    private Button btnFrag1;
    private int someInt;
    private String someString;
    private TextChangeListener listener;

    public interface TextChangeListener {
        public void onTextChange(String newText);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof TextChangeListener){
            listener = (TextChangeListener) context;
        } else{
            throw new RuntimeException(context.toString() +
                    "must implement TimeFragment");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public TimeFragment() {
        super(R.layout.fragment_time);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //go here to findViewById or get bundle from activity to show up
        if(getArguments() != null){
            someInt = getArguments().getInt("KeyToFragment");
        }
        //get view id
        tvFrag1 = view.findViewById(R.id.tv_frag1);
        edtFrag1 = view.findViewById(R.id.edt_inputFrag1);
        btnFrag1 = view.findViewById(R.id.btn_sendData1);

        btnFrag1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendDataToOtherFrag();
            }
        });

        //update data on ui
        tvFrag1.setText(String.valueOf(someInt));
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public void sendDataToOtherFrag()
    {
        listener.onTextChange(edtFrag1.getText().toString());
    }



    public void setTextChangeListener(TextChangeListener listener) {
        this.listener = listener;
    }


    private void getData(){
        getParentFragmentManager().setFragmentResultListener("requestKey", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                Log.d("bibi", requestKey.toString() + result.toString());
                someString = result.getString(requestKey);
            }
        });
    }

    private void sendData(){
        Bundle result = new Bundle();
        result.putString("bundleKey", "result");
        getParentFragmentManager().setFragmentResult("requestKey", result);
    }


}