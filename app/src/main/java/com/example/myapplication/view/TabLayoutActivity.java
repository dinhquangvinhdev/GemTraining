package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

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

        viewPager.setAdapter(new TabCollectionAdapter(this));
        new TabLayoutMediator(tabLayout , viewPager ,
                ((tab, position) -> tab.setText(tabTitles.get(position)))).attach();
    }

    private void setUI() {
        tabLayout = findViewById(R.id.tb_dashboard);
        viewPager = findViewById(R.id.vp2_tab);
    }
}