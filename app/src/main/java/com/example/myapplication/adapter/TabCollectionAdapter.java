package com.example.myapplication.adapter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapplication.view.fragment.DashboardFragment;
import com.example.myapplication.view.fragment.GameFragment;
import com.example.myapplication.view.fragment.HomeFragment;
import com.example.myapplication.view.fragment.NoteFragment;
import com.example.myapplication.view.fragment.SomethingFragment;

public class TabCollectionAdapter  extends FragmentStateAdapter {
    public TabCollectionAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        //return a new fragment instance in createFragment(int)
        //you can also send a bundle in here for each frag
        switch (position){
            case 0: return new DashboardFragment();
            case 1: return new HomeFragment();
            case 2: return new GameFragment();
            case 3: return new NoteFragment();
            case 4: return new SomethingFragment();
            default:
                Log.d("bibi", "the TabLayout was call out of ranger");
                return new DashboardFragment();
        }
    }

    @Override
    public int getItemCount() {
        //limit the tab just for 10
        return 5;
    }
}
