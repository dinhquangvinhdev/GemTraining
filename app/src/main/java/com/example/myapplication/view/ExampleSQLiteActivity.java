package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.manager.CustomerManager;
import com.example.myapplication.model.Customer;

import java.util.List;

public class ExampleSQLiteActivity extends AppCompatActivity {

    private EditText edt_name;
    private EditText edt_age;
    private TextView tv_name;
    private TextView tv_age;
    private Button btn_add_customer;
    private Button btn_get_customer;
    private Button btn_get_all_customer;
    private Button btn_update_customer;
    private Button btn_delete_customer;
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
                addCustomer();
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

        btn_update_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateCustomer();
            }
        });

        btn_delete_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete_customer();
            }
        });
    }

    private void addCustomer() {
        String age = edt_age.getText().toString();
        String name = edt_name.getText().toString();
        if(!age.isEmpty()) {
            long id_row = manager.addCustomer(name, Integer.parseInt(age));
            if(id_row > -1){
                Toast.makeText(this, "add new customer success", Toast.LENGTH_SHORT).show();
            } else{
                Toast.makeText(this , "add failed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void delete_customer() {
        String age = edt_age.getText().toString();
        if(!age.isEmpty()){
            int result = manager.deleteCustomer(new Customer(Integer.parseInt(age)));
            if(result == 0){
                Toast.makeText(this , "delete customer failed", Toast.LENGTH_SHORT).show();
            } else if(result == 1){
                Toast.makeText(this ,"delete success", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void updateCustomer() {
        // return 0 if update success
        // return 1 if not found object
        if(!edt_age.getText().toString().isEmpty() && !edt_name.getText().toString().isEmpty()) {
            Customer customer = new Customer(Integer.parseInt(edt_age.getText().toString()), edt_name.getText().toString());
            int temp = manager.updateCustomer(customer);
            if (temp == 0){
                Toast.makeText(this, "Not found customer", Toast.LENGTH_SHORT).show();
            } else if(temp == 1){
                Toast.makeText(this , "Update success", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getAllCustomer() {
        List<Customer> mList = manager.getALLCustomer();
        String names = "";
        String ages = "";
        for(Customer customer : mList){
            names += customer.getName() + "\n";
            ages += customer.getAge() + "\n";
        }
        tv_name.setText("Name" + "\n" + names);
        tv_age.setText("Age" + "\n" + ages);
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
        btn_update_customer = findViewById(R.id.btn_update_customer);
        btn_delete_customer = findViewById(R.id.btn_delete_customer);
    }
}