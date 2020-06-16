package com.example.agilesynergy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;


import com.example.agilesynergy.fragments.fragmentProfile;
import com.google.android.material.tabs.TabLayout;

import com.example.agilesynergy.adapter.ViewPagerAdapter;
import com.example.agilesynergy.fragments.fragmentDashboard;
import com.example.agilesynergy.fragments.fragmentMenu;

public class MainActivity extends AppCompatActivity {

    public static ViewPager viewPager;

    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Fragment Handling

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabID);

        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FFFFFF"));

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addFragment(new fragmentDashboard(), "Dashboard");
        viewPagerAdapter.addFragment(new fragmentMenu(), "Menu");
        viewPagerAdapter.addFragment(new fragmentProfile(), "Profile");

        viewPagerAdapter.notifyDataSetChanged();
        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);

    }
}