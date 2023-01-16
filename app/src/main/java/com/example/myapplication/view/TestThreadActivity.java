package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.CharacterPickerDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestThreadActivity extends AppCompatActivity {

    public class MThread extends Thread{

        private List<String> mList = new ArrayList<String>(Arrays.asList("t1","t2","t3","t4","t5"));

        public MThread(String name){
            this.setName(name);
        }
        @Override
        public void run() {
            try{
                for(int i=0 ; i<mList.size() ; i++){
                    Log.d("bibi",this.getName() + " : " + mList.get(i));
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public class MThread2 extends Thread{

        private List<String> mList = new ArrayList<String>(Arrays.asList("t1","t2","t3","t4","t5"));

        public MThread2(String name){
            this.setName(name);
        }
        @Override
        public void run() {
            try{
                synchronized (this){
                    for(int i=0 ; i<mList.size() ; i++){
                        if(i == 2){
                            Log.d("bibi", this.getName() + ": " + "wait thread");
                            this.wait();
                        }
                        Log.d("bibi",this.getName() + " : " + mList.get(i));
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        public synchronized void notifyThread(){
            notify();
            Log.d("bibi", this.getName() + ": " + "notify thread");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_thread);

        Button btn = findViewById(R.id.btn_run);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                runMThread();
            }
        });

    }

    private class RThread implements Runnable{

        private List<String> mList = new ArrayList<String>(Arrays.asList("t1","t2","t3","t4","t5"));
        private String name;

        public RThread(String name){
            this.name = name;
        }

        @Override
        public void run() {
            try{
                for(int i=0 ; i<mList.size() ; i++){
                    Log.d("bibi", name + " : " + mList.get(i));
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void runMThread() {
        MThread thread1 = new MThread("thread1");
        MThread2 thread3 = new MThread2("thread3");
        MThread2 thread4 = new MThread2("thread4");
        Thread thread2 = new Thread(new RThread("thread2"));

        //run all thread
        thread1.start();
        thread4.start();
        thread3.start();
        try {
            //stop run other threads after it finish <all thread run before this thread continue run>
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.start();

        if(!thread1.isAlive())
            thread4.notifyThread();
    }


}