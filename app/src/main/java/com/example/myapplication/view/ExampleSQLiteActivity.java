package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.manager.CustomerManager;
import com.example.myapplication.model.Customer;

public class ExampleSQLiteActivity extends AppCompatActivity {

    private EditText edt_name;
    private EditText edt_age;
    private TextView tv_name;
    private TextView tv_age;
    private Button btn_add_customer;
    private Button btn_get_customer;
    private Button btn_get_all_customer;
    private CustomerManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example_sqlite);
        getUI();

        manager = new CustomerManager(this);

        btn_add_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edt_name.getText().toString();
                int age = Integer.parseInt(edt_age.getText().toString());
                manager.addCustomer(name , age);
            }
        });

        btn_get_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getCustomerByName();
            }
        });

        btn_get_all_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAllCustomer();
            }
        });
    }

    private void getAllCustomer() {
        manager.getALLCustomer();
    }

    private void getCustomerByName() {
        String name = edt_name.getText().toString();
        Customer customer  = manager.getCustomer(name);
        if(customer != null){
            tv_name.setText(customer.getName());
            tv_age.setText(String.valueOf(customer.getAge()));
        }
    }

    private void getUI() {
        edt_name = findViewById(R.id.edt_name);
        edt_age = findViewById(R.id.edt_age);
        tv_name = findViewById(R.id.tv_name);
        tv_age = findViewById(R.id.tv_age);
        btn_add_customer = findViewById(R.id.btn_add_customer);
        btn_get_customer = findViewById(R.id.btn_get_customer_by_name);
        btn_get_all_customer = findViewById(R.id.btn_get_all_customer);
    }
}