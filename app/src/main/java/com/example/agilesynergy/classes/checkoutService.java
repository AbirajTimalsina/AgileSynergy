package com.example.agilesynergy.classes;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import com.example.agilesynergy.R;
import com.example.agilesynergy.fragments.innerFragments.checkoutFragment;
import com.example.agilesynergy.global.global;


public class checkoutService extends Service {
    Handler handler;
    Runnable runnable;
    Button btnCheckout;

    Activity mActivity;
    FragmentManager fm;
   //context and activity

    //getcontext   getactivity.
    public checkoutService(Activity mActivity, FragmentManager fm) {
        this.mActivity = mActivity;
        this.fm=fm;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void startService() {
        btnCheckout = mActivity.findViewById(R.id.checkoutButton);
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                if (global.ItemLists.size() == 0) {
                    btnCheckout.setVisibility(View.GONE); //if list 0 then don't show me
                }
                else if (global.ItemLists.size() > 0) {
                    btnCheckout.setVisibility(View.VISIBLE); //if list is more than zero then show me.
                    btnCheckout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            fm.beginTransaction().replace(R.id.frame_container, new checkoutFragment()).addToBackStack(null).commit();
                        }
                    });
                }
                handler.postDelayed(runnable, 1000);
            }
        };
        handler.postDelayed(runnable, 500);
    }
}
