package com.example.agilesynergy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example.agilesynergy.classes.StrictModeClass;
import com.example.agilesynergy.classes.checkoutService;
import com.example.agilesynergy.fragments.FavouriteFragment;
import com.example.agilesynergy.fragments.HomeFragment;
import com.example.agilesynergy.fragments.MenuFragment;
import com.example.agilesynergy.fragments.ProfileFragment;
import com.example.agilesynergy.helper.BottomNavigationHider;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkoutService abc = new checkoutService(this, this.getSupportFragmentManager());
        abc.startService();
        StrictModeClass.StrictMode();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

//        // attaching bottom sheet behaviour - hide / show on scroll
//        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) navigation.getLayoutParams();
//        layoutParams.setBehavior(new BottomNavigationHider());

        // load the store fragment by default
        loadFragment(new HomeFragment(), 1);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = new HomeFragment();
                    loadFragment(fragment, 1);
                    return true;
                case R.id.navigation_menu:
                    fragment = new MenuFragment();
                    loadFragment(fragment, 2);
                    return true;
                case R.id.navigation_favourite:
                    fragment = new FavouriteFragment();
                    loadFragment(fragment, 3);
                    return true;
                case R.id.navigation_profile:
                    fragment = new ProfileFragment();
                    loadFragment(fragment, 4);
                    return true;
            }
            return false;
        }
    };

    private int position = 0;

    private void loadFragment(Fragment fragment, int position) {

        while (this.position != position) {
            // load fragment
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_container, fragment);
            if (this.position < position) {
                transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_from_right);
            } else {
                transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_from_left);
            }
            this.position = position;
            transaction.addToBackStack(null);
            transaction.detach(fragment);
            transaction.attach(fragment);
            transaction.commit();
        }

    }
}