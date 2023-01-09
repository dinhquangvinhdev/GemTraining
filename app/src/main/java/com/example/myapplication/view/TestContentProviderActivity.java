package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.UserDictionary;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.myapplication.R;

public class TestContentProviderActivity extends AppCompatActivity {

    private EditText edt;
    private ListView lvData;
    private Button btnAddData;
    private Button btnFindText;
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

        //init variable
        createSomeVariablesForProvider();
        usingCursorForGetData();

        //search data or get all
        btnFindText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchTextData();
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

    private void addNewContact() {
        Cursor simple = getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,
                null,
                null,
                null
        );
        Log.d("bibi" , simple.toString());
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

    private void searchTextData() {
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