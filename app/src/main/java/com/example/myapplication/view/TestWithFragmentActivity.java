package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityTestWithFragmentBinding;
import com.example.myapplication.mInterface.OnClickFragmentListener;
import com.example.myapplication.view.fragment.Fragment1;
import com.example.myapplication.view.fragment.Fragment2;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TestWithFragmentActivity extends AppCompatActivity implements OnClickFragmentListener {

    private ActivityTestWithFragmentBinding binding;
    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private String stringStorage = null;
    private BottomNavigationView bottomView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTestWithFragmentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //init Fragment
        fragment1 = new Fragment1();
        fragment2 = new Fragment2();

        //create fragment into frameLayout with supportFragmentManager
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container_view, fragment1)
                .replace(R.id.fragment_container_view, fragment2)
                .commit();

        //create bottom navigation view
        bottomView = findViewById(R.id.bnv_main);
        bottomView.setOnItemSelectedListener(item -> {
            //do something in here
            switch (item.getItemId()){
                case R.id.navFrag1:{
                    replaceFrag(1);
                    break;
                }
                case R.id.navFrag2:{
                   replaceFrag(2);
                    break;
                }
                default: {
                    Log.d("bib_bnvMActivity", "get wrong id view");
                }
            }
            return true;
        });

        //Click btn 1
        binding.btnFrag1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFrag(1);
            }
        });

        //Click btn 2
        binding.btnFrag2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFrag(2);
            }
        });


    }

    /**
     * This method is used for replacing fragment in fragmentContainerView
     * if choice = 1 -> it is fragment1
     * else if choice = 2 -> it is fragment2
     * else notification error
     * @param choice
     */
    private void replaceFrag(int choice){
        switch (choice){
            case 1:
                getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(
                                R.anim.enter_right_to_left,  // enter
                                R.anim.exit_right_to_left,  // exit
                                R.anim.enter_left_to_right,   // popEnter
                                R.anim.exit_left_to_right  // popExit
                        )
                        .replace(R.id.fragment_container_view, fragment1)
                        .addToBackStack(null)
                        .commit();
                break;
            case 2:
                getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(
                                R.anim.enter_right_to_left,  // enter
                                R.anim.exit_right_to_left,  // exit
                                R.anim.enter_left_to_right,   // popEnter
                                R.anim.exit_left_to_right  // popExit
                        )
                        .replace(R.id.fragment_container_view, fragment2)
                        .addToBackStack(null)
                        .commit();
            default:
                Log.d("bib_MActivity", "get wrong choice fragment");
        }
    }

    @Override
    public String getData() {
        Log.d("bib_activity","call getData: " + stringStorage);
        return stringStorage;
    }

    @Override
    public void sendData(String result) {
        stringStorage = result;
        Log.d("bib_activity","call sendData: " + result + "|| stringStorage: " + stringStorage);
    }


}