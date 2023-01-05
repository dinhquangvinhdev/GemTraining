package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
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

                /*Create contentResolve to query*/
                ContentResolver contentResolver = getContentResolver();
                /*Uri for contentProvider*/
                Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
                /*Create cursor for get Contact list through query by contentResolver*/
                Cursor cursor = contentResolver.query(uri , null, null , null , null);

                //check if cursor not receive any column -> this is end of your day
                if(cursor.getCount() > 0){
                    String nameContact, numberPhone;
                    int key_name, key_phone;
                    //traver the contact list to end
                    while (cursor.moveToNext()){
                        //get id column
                        key_name = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                        key_phone = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                        //check id column > 0
                        if(key_name >= 0 && key_phone > 0){
                            nameContact = cursor.getString(key_name);
                            numberPhone = cursor.getString(key_phone);
                            Log.d("bibi" ,"Contact ## Name : " + nameContact + " || Phone : " + numberPhone);
                        }
                    }
                }else{
                    Log.d("bibi","not receive any contact from your phone");
                }
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