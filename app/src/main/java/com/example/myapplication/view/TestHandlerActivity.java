package com.example.myapplication.view;

import static com.example.myapplication.view.TestHandlerActivity.MHandler.MESS1;
import static com.example.myapplication.view.TestHandlerActivity.MHandler.MESS2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;

public class TestHandlerActivity extends AppCompatActivity {

    private static final String TAG_LOG = "bibi";
    private Button btnStart, btnStop, btnTask1, btnTask2;
    private MThread mThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_handler);

        btnStart = findViewById(R.id.btn_start);
        btnStop = findViewById(R.id.btn_stop);
        btnTask1 = findViewById(R.id.btn_task1);
        btnTask2 = findViewById(R.id.btn_task2);

        mThread = new MThread();

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //start thread and the loop for Handler
                mThread.start();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //quit the loop
                mThread.looper.quit();
            }
        });

        btnTask1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //create a message to send for Handler
                Message message = new Message();
                message.what = MESS1;
                mThread.handler.handleMessage(message);
            }
        });

        btnTask2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //create a message to send for Handler
                Message message = new Message();
                message.what = MESS2;
                mThread.handler.handleMessage(message);
            }
        });
    }

    /**
     * This method just use for someOne understand how a Handler work
     * This method will throw exception if not start the loop first
     */
    private void postMessByHandlerError() {
        postMessByHandlerError();
        //create a Handler have the looper of Thread run
        Handler handler = new Handler(mThread.looper);
        //send a message into queue
        handler.post(new Runnable() {
            @Override
            public void run() {
                for(int i=0 ; i<5 ; i++){
                    Log.d(TAG_LOG, "run: " + i);
                    SystemClock.sleep(1000);
                }
                Log.d(TAG_LOG,"End counting");
            }
        });
    }

    /**
     * This class uses to create Handler for calling again after the thread was die by system
     */
    public class MThread extends Thread{
        public Handler handler;
        public Looper looper;

        @Override
        public void run() {
            //need to create a Looper.prepare before create a new Handler in a Thread
            Looper.prepare();
            //Get Looper is running this Thread
            looper = Looper.myLooper();
            //craete a handler
            handler = new MHandler();
            //loop the looper
            Looper.loop();
        }

        public Handler getmHandler() {
            return handler;
        }
    }

    public class MHandler extends Handler{
        public static final int MESS1 = 0;
        public static final int MESS2 = 1;

        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case MESS1:
                    for(int i=0 ;i<5 ; i++){
                        Log.d("bibi", "Mess1: " + i);
                        SystemClock.sleep(1000);
                    }
                    break;
                case MESS2:
                    for(int i=0 ;i<5 ; i++){
                        Log.d("bibi", "Mess2: " + i);
                        SystemClock.sleep(1000);
                    }
                    break;
                default:
                    Log.e("bibi", "wrong message what");
                    break;
            }
        }
    }

}