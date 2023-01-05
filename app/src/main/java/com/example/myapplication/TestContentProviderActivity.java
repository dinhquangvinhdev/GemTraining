package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PackageManagerCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class TestContentProviderActivity extends AppCompatActivity {

    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_content_provider);

        btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //request permission
                checkPermission();
            }
        });
    }

    /**
     * This method need to call before using permission Read_Contacts
     * to have check app has been authorized or not.
     * If not show dialog to get permission
     */
    private void checkPermission(){
        //check if app not have permission show a dialog to user confirm this app has permission
        if(ContextCompat.checkSelfPermission(this , Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{
                            Manifest.permission.READ_CONTACTS
                    },
                    0
            );
            Log.d("bibi","request permissions");
        }
        else{
            Log.d("bibi", "Already had permissions");
        }
    }
}