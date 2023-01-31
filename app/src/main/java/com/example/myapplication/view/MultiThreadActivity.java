package com.example.myapplication.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.ArrayList;

public class MultiThreadActivity extends AppCompatActivity {

    private ArrayList<MThread> arr;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private Button btn;
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

    }

    private void createThreads() {
        arr = new ArrayList<>(3);
        MThread mThread1 = new MThread(1);
        MThread mThread2 = new MThread(2);
        MThread mThread3 = new MThread(3);
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
    }

    private void testThread() {
        int count = 1;
        MThread mThread = new MThread(1);
        mThread.start();
        MThread mThread2 = new MThread(2);
        mThread2.start();
        MThread mThread3 = new MThread(3);
        mThread3.start();
    }

    private class MThread extends Thread {
        private int choice = -1;
        private boolean isRunning = false;

        public MThread(int i) {
            choice = i;
        }

        @Override
        public void run() {
            isRunning = true;
            int i=0;
            switch (choice) {
                case 1:
                    i = 0;
                    while (isRunning && i<5){
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
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
                            Thread.sleep(4000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        tv3.setText(String.valueOf(i));
                        Log.d("bibi", "Thread 3: " + i);
                        i++;
                    }
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