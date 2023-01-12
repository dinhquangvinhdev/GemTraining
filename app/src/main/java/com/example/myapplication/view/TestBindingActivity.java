package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;
import com.example.myapplication.service.MBoundService;
public class TestBindingActivity extends AppCompatActivity {

    MBoundService mService;
    Boolean mBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_binding);

        Button btnStart = findViewById(R.id.btn_start_bound_service);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mBound){
                    Log.d("bibi", "Have bind and play music");
                    mService.playMusic();
                } else{
                    Log.d("bibi", "unbind service to play music");
                }
            }
        });

        Button btnStop = findViewById(R.id.btn_stop_bound_service);
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mBound){
                    Log.d("bibi", "Have bound and stop music");
                    mService.stopMusic();
                } else {
                    Log.d("bibi", "unbind service to stop music");
                }
            }
        });

        //This is a comment for commit git about end of step extend class Binder
        //The next step is about use Messenger
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this , MBoundService.class);
        //automatically create the service as long as the binding exists
        bindService(intent , mConnection , Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("bibi", "running onStop of Activity");
        unbindService(mConnection);
        mBound = false;
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MBoundService.LocalBinder binder = (MBoundService.LocalBinder) iBinder;
            mService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mBound = false;
            mService.stopMusic();
        }
    };
}