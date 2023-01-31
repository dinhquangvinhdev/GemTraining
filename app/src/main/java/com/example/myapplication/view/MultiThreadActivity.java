package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.mInterface.RegisterEventListener;

import java.util.ArrayList;

public class MultiThreadActivity extends AppCompatActivity implements RegisterEventListener {

    private ArrayList<MThread> arr;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private Button btn;
    private Button btnClickMe;
    private Boolean isRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_thread);

        getUI();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                runMultiThread();
            }
        });

        btnClickMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext() , "You are Clicking me", Toast.LENGTH_SHORT).show();
                Log.d("bibi", "The status of Thear1 is: " + arr.get(0).isRunning);
            }
        });


    }

    private void createThreads() {
        arr = new ArrayList<>(3);
        MThread mThread1 = new MThread(1, this);
        MThread mThread2 = new MThread(2, this);
        MThread mThread3 = new MThread(3, this);
        arr.clear(); // Must handle thread to interrupt after clear this arr Threads
        arr.add(mThread1); arr.add(mThread2); arr.add(mThread3);
    }

    private void runMultiThread() {

        //run thread if the thread is not run
        if (!isRunning) {
            isRunning = true;
            btn.setText("Threads are running");
            //create Thread
            createThreads();
            //check Thread if it is not null and not start ==> start it
            for(MThread thread : arr){
                if(thread != null && !thread.isAlive()){
                    thread.start();
                }
            }
        } else {
            //check thread if it is start ==> stop it
            for(MThread thread : arr){
                if(thread != null && thread.isAlive()){
                    thread.stopThread();
                }
            }
            isRunning = false;
            btn.setText("Threads are not running");
        }
    }

    private void getUI() {
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        btn = findViewById(R.id.btn_run_multi_thread);
        btnClickMe = findViewById(R.id.btn_test);
    }

    @Override
    public void onAEvent(int number) {
        Log.d("bibi", "running on AEvent");
        //this Thread is used for test callback
        MThread mThread = new MThread(4, this, number);
        mThread.start();
    }

    private class MThread extends Thread {
        private int choice = -1;
        private boolean isRunning = false;
        private RegisterEventListener registerEventListener;
        private int count = 0;

        public MThread(int i, RegisterEventListener registerEventListener) {
            this.registerEventListener = registerEventListener;
            choice = i;
            this.count = 0;
        }

        public MThread(int i, RegisterEventListener registerEventListener, int count) {
            this.registerEventListener = registerEventListener;
            choice = i;
            this.count = count;
        }

        @Override
        public void run() {
            isRunning = true;
            int i=0;
            switch (choice) {
                case 1:
                    i = 0;
                    while (isRunning && i<10){
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        //call callback
                        if(i % 2 == 0){
                            registerEventListener.onAEvent(i);
                        }
                        tv1.setText(String.valueOf(i));
                        Log.d("bibi", "Thread 1: " + i);
                        i++;
                    }
                    break;
                case 2:
                    try {
                        Thread.sleep(2000);
                        tv2.setText("Hello");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    i = 0;
                    while (isRunning && i<5){
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        tv3.setText(String.valueOf(i));
                        Log.d("bibi", "Thread 3: " + i);
                        i++;
                    }
                    break;
                case 4:
                    tv2.setText(String.valueOf(count));
                    break;
                default:
                    break;
            }
        }

        public void stopThread(){
            this.isRunning = !this.isRunning;
            interrupt();
        }
    }
}