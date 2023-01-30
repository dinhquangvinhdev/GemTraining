package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.adapter.TabCollectionAdapter;
import com.example.myapplication.view.fragment.DashboardFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TabLayoutActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private ArrayList<String> tabTitles = new ArrayList<>(Arrays.asList("dash board", "home" , "game", "note" , "something"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);

        setUI();
        setUpTabLayoutWithViewPager2();
    }

    private void setUpTabLayoutWithViewPager2() {
        TabCollectionAdapter adapter = new TabCollectionAdapter(this);
        viewPager.setAdapter(adapter);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.d("bibi", "running here");
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        new TabLayoutMediator(tabLayout , viewPager ,
                ((tab, position) -> tab.setText(tabTitles.get(position)))).attach();

        for(int i=0 ; i<tabTitles.size() ; i++){
            //get inflate view of TextView
            TextView textView =
                    (TextView) LayoutInflater.from(this).inflate(R.layout.tab_title, null);
            textView.setText(tabTitles.get(i));
            tabLayout.getTabAt(i).setCustomView(textView);
        }
    }

    private void setUI() {
        tabLayout = findViewById(R.id.tb_dashboard);
        viewPager = findViewById(R.id.vp2_tab);
    }
}