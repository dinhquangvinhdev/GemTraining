package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.Person;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.provider.UserDictionary;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestContentProviderActivity extends AppCompatActivity {

    private EditText edt;
    private ListView lvData;
    private Button btnAddData;
    private Button btnFindText;
    private ArrayAdapter<String> arrayAdapter;
    private List<String> lString;
    // variable for content provider
    private String[] mProjection;
    private String selectionClause;
    private String[] selectionArgs;
    private Uri mUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_content_provider);

        //get id
        edt = findViewById(R.id.edt_inputProvider);
        lvData = findViewById(R.id.lv_data);
        btnAddData = findViewById(R.id.btn_addData);
        btnFindText = findViewById(R.id.btn_findTextData);
        lString = new ArrayList<>();

        //checkPermission
        checkPermission();



        //test for dictionary
//        //init variable
//        createSomeVariablesForProvider();
//        usingCursorForGetData();


        //test for contact
        createVarContactProvider();
        //search data or get all
        btnFindText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchTextDataContact();
            }
        });

        //add new raw data into database
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewContact();
            }
        });
    }

    private void createVarContactProvider() {
        //defines the column for that wil be return for each row
        this.mProjection = new String[]{
                Contacts.People.NAME,
                Contacts.People.NUMBER
        };

        //defines string for selection clause
        this.selectionClause = null;

        //Initialized an array to contain selection arguments
        this.selectionArgs = new String[]{""};

        //define uri
        mUri = Contacts.People.CONTENT_URI;
    }

    private void searchTextDataContact() {
        //get searchString
        String searchString = edt.getText().toString();

        if (TextUtils.isEmpty(searchString)){
            Toast.makeText(this , "Please text something",Toast.LENGTH_SHORT).show();
            selectionClause = null;
            selectionArgs[0] = "";
        } else{
            selectionClause = Contacts.People.NAME + " = ?";
            selectionArgs[0] = searchString;
        }
        usingCursorForGetDataContact();
    }

    private void usingCursorForGetDataContact() {
        /*Create contentResolve to query*/
        ContentResolver contentResolver = getContentResolver();
        /*Uri for contentProvider*/
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        /*Create cursor for get Contact list through query by contentResolver*/
        Cursor cursor = contentResolver.query(uri , null, null , null , null);
        lString.clear();

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
                    lString.add(nameContact + " : " + numberPhone);
                    Log.d("bibi" ,"Contact ## Name : " + nameContact + " || Phone : " + numberPhone);
                }
            }
        }else{
            Log.d("bibi","not receive any contact from your phone");
        }

        //add to ListView
        arrayAdapter = new ArrayAdapter<String>(this ,R.layout.item_phone,lString);
        lvData.setAdapter(arrayAdapter);
    }

    private void checkPermission() {
            //check permission
            if(ContextCompat.checkSelfPermission(this , Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(
                        this,
                        new String[] {Manifest.permission.READ_CONTACTS},
                        0
                );
            }else{
                Log.d("bibi","granted read_contact permission");
            }
            if(ContextCompat.checkSelfPermission(this , Manifest.permission.WRITE_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                        this,
                        new String[]{Manifest.permission.WRITE_CONTACTS},
                        0
                );
            }else{
                Log.d("bibi","granted write_contact permission");
            }
    }

    private void addNewContact() {
        //init new data
        ContentValues contentValues = new ContentValues();
        contentValues.put(Contacts.People.NAME, "Test name");
//        contentValues.put(ContactsContract.Contacts.Data.DATA1, "0904875633");
        try {
            Uri insertUri = getContentResolver().insert(
                    Contacts.People.CONTENT_URI,
                    contentValues
            );
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void addNewContact2() {
        //using implicit intent
        Intent contactIntent = new Intent(ContactsContract.Intents.Insert.ACTION);
        contactIntent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
        contactIntent
                .putExtra(ContactsContract.Intents.Insert.NAME , "New name")
                .putExtra(ContactsContract.Intents.Insert.PHONE, "0904875633");
        startActivity(contactIntent);

    }

    private void addNewData() {
        //Define an object to contain the new values to insert
        ContentValues contentValues = new ContentValues();

        //set arguments into object
        contentValues.put(UserDictionary.Words.APP_ID, "example.user");
        contentValues.put(UserDictionary.Words.LOCALE, "en_US");
        contentValues.put(UserDictionary.Words.WORD, "insert");
        contentValues.put(UserDictionary.Words.FREQUENCY, "100");

        Log.d("bibi","Old uri: " + mUri.toString());

        //insert object into database
        //remember update uri after inserting
        mUri = Uri.parse("content://user_dictionary/words");
        mUri = getContentResolver().insert(
                mUri,
                contentValues
        );
        Log.d("bibi","After insert new data: " + mUri.toString());
    }

    private void searchTextDataDictionary() {
        //get searchString
        String searchString = edt.getText().toString();

        //check for invalid or malicious input
        if (TextUtils.isEmpty(searchString)){
            Toast.makeText(this , "Please text something",Toast.LENGTH_SHORT).show();
            // setting selectionClause to null to return all words
            selectionClause = null;
            selectionArgs[0] = "";
        } else{
            //Constructs a selection clause that matches the word that the user enter
            //remember space with char =  and ?
            selectionClause = UserDictionary.Words.WORD + " = ?";
            // add the input String into argument for filter
            selectionArgs[0] = searchString;
        }
        usingCursorForGetData();
    }

    private void usingCursorForGetData() {
        //query to return a Cursor object with table
        Cursor mCursor = getContentResolver().query(
                mUri,
                mProjection,
                selectionClause,
                selectionArgs,
                null
        );

        //check exception if cursor return null by error
        if(mCursor == null){
            /**
             *  Log.e("bibi", "The cursor not found");
             */
            onCursorIsNull();
            //if the cursor is empty , the provider found no matches
        } else if(mCursor.getCount() < 1) {
            /**
             *  process this case to do something
             */
            onCursorNotGetAnyData();
            //else
        }else{
            /**
             * YEEEE you get the data --> do somthing with it
             */
            onCursorGetData(mCursor);
        }
    }

    private void onCursorGetData(Cursor mCursor) {
        Log.d("bibi","mCursor get something in data");

        //defines a list of column to receive data by cursor
        String[] worldListColumns = {
                UserDictionary.Words.WORD,
                UserDictionary.Words.LOCALE
        };

        //Defines a list of View IDs that will receive the Cursor columns for each row
        int[] worldListItems = { R.id.tv_dictWord , R.id.tv_locale};

        //get ListView through cursor by SimpleCursorAdapter
        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(
                getApplicationContext(),
                R.layout.item_world,
                mCursor,
                worldListColumns,
                worldListItems,
                0
        );

        lvData.setAdapter(cursorAdapter);
    }

    private void onCursorNotGetAnyData() {
        Log.d("bibi","mCursor not get anything in the data");
    }

    private void onCursorIsNull() {
        Log.d("bibi","mCursor is null");
    }

    private void createSomeVariablesForProvider() {
        //defines the column for that wil be return for each row
        this.mProjection = new String[]{
                UserDictionary.Words._ID,    // id column
                UserDictionary.Words.WORD,   //word column
                UserDictionary.Words.LOCALE //locale column
        };

        //defines string for selection clause
        this.selectionClause = null;

        //Initialized an array to contain selection arguments
        this.selectionArgs = new String[]{""};

        //define uri
        mUri = UserDictionary.Words.CONTENT_URI;
    }
}