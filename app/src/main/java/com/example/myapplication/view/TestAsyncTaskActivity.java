package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestAsyncTaskActivity extends AppCompatActivity {

    private String uri = "https://pbs.twimg.com/media/FZyplLdXEAQrbFP.jpg";
    Button btn;
    ImageView img;
    GridLayout gl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_async_task);

        btn = findViewById(R.id.btn_run);
        img = findViewById(R.id.img_screen);
        gl = findViewById(R.id.gl_img);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                runAsyncTask();
//                loadImageWithGlide();
            }
        });

    }

    private void loadImageWithGlide() {
        Glide.with(this)
                .load(uri)
                .into(img);
    }

    private void runAsyncTask() {
        AsyncTask mAsyncTask = new mTask(TestAsyncTaskActivity.this).execute(uri);
    }

    /**
     * AsynTask chỉ dùng cho các hoạt động ngắn hạn <chưa rõ>
     * có 3 đối số của AsyncTask là: <Lưu ý có thể custom theo ý mình thích nhưng thứ tự vẫn vậy>
     * String : Params --> input của doInBackground
     * Integer: Progress --> input của onProgressUpdate
     * String: Result  --> là ouput của doInBackground và input của onPostExecute
     */
    public class mTask extends AsyncTask<String, Integer , Bitmap>{

        private Activity activity;
        private ProgressBar pb;

        /**
         * Điểm đặc biệt
         * khi dùng AsyncTask hơn Thread là nó có thể lấy được context của Activity
         * Xử lý được dữ liệu lớn để updateUI
         * @param activity
         */
        public mTask(Activity activity){
            this.activity = activity;
            pb = activity.findViewById(R.id.pb_status);
        }

        /**
         * phương thức này chạy trên UI Thread
         * Chạy trước khi chạy background
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Thông báo bắt đầu tải
            Toast.makeText(activity , "Bắt đầu tải", Toast.LENGTH_SHORT).show();
            pb.setProgress(0);
        }

        /**
         * Xử lý background ở đây
         * Tuyệt đối không được vẽ giao diện trong phương thức này
         * @param strings
         * @return
         */
        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                //Get data
                URL url = new URL(strings[0]);
                //sử dụng http connection để kết nối
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                //get response code
                int responseCode = conn.getResponseCode();
                //check response code
                if(responseCode == HttpURLConnection.HTTP_OK){
                    InputStream inputStream = conn.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                    //update UI to show the process here
                    int step = 100;
                    publishProgress(step);
                    return bitmap;
                }else{
                    //ngắt kết nối
                    conn.disconnect();
                    throw new RuntimeException("Error code: " + responseCode);
                }
            }catch (IOException e){
                e.printStackTrace();
            }

            return null;
        }

        /**
         * Chạy trên UI Thread được dùng để cập nhật UI Thread ?? chưa bt xài kiểu gì
         * @param values
         */
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            //update giao diện thanh progressBar
            int number = values[0];
            pb.setProgress(number);
        }

        /**
         * Chạy trên UI Thread
         * được gọi khi xong task trong background
         * thường được dùng để lấy dữ liệu sau khi xử lý xong trong background
         * @param bitmap
         */
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
//            //add new imageView into GridLayout
//            ImageView imageView = new ImageView(activity);
//            imageView.setLayoutParams(gl.getLayoutParams());
//            imageView.setImageBitmap(bitmap);
//            gl.addView(imageView);
            img.setImageBitmap(bitmap);
            Log.d("bibi", "AsyncTask onPostExecute: " + bitmap);
            Toast.makeText(activity, "Get Image Done", Toast.LENGTH_SHORT).show();
        }
    }
}