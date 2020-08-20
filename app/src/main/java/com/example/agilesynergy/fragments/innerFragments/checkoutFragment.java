package com.example.agilesynergy.fragments.innerFragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agilesynergy.CreateChannel.CreateChannel;
import com.example.agilesynergy.MainActivity;
import com.example.agilesynergy.api.itemapi;
import com.example.agilesynergy.classes.userPurchase;
import com.example.agilesynergy.global.global;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agilesynergy.R;
import com.example.agilesynergy.adapter.RecyclerAdapter;

public class checkoutFragment extends DialogFragment {

    private RecyclerView recyclerView;
    private LinearLayout linearLayoutPurchase, linearLayoutCancel;
    private NotificationManagerCompat notificationManagerCompat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_checkout, container, false);
        linearLayoutPurchase = view.findViewById(R.id.linearlayoutpurchase);
        linearLayoutCancel = view.findViewById(R.id.linearlayoutcancel);
        int width = getResources().getDimensionPixelSize(R.dimen.dialogFragmentWidth);
        int height = getResources().getDimensionPixelSize(R.dimen.dialogFragmentHeight);
        getDialog().getWindow().setLayout(width, height);
        linearLayoutCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
                global.ItemLists.clear();
            }
        });

        linearLayoutPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (global.ItemLists.size() > 0) {

                    //Function Containing Dialog
                    yesOrNo();
                }
            }
        });
        FragmentManager fm = getActivity().getSupportFragmentManager();

        recyclerView = view.findViewById(R.id.recyclerviewcheckout);
        try {
            RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getActivity(), null,
                    global.ItemLists, fm, "checkout", getDialog());
            recyclerView.setAdapter(recyclerAdapter);
            LinearLayoutManager layoutManager
                    = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(layoutManager);

        } catch (Exception e) {
            e.printStackTrace();
        }


        //notification manager
        notificationManagerCompat = NotificationManagerCompat.from(getContext());
        CreateChannel channel = new CreateChannel(getContext());
        channel.createChannel();
        return view;
    }

    public void yesOrNo() {
        new AlertDialog.Builder(getActivity())
                .setTitle("Are you sure?")
                .setMessage("The current order list will be sent out.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        boolean isPurchased = new userPurchase().userPurchaseFood();
                        if (isPurchased) {
                            Notification notification = new NotificationCompat.Builder(getContext(), CreateChannel.CHANNEL_1).
                                    setSmallIcon(R.drawable.user)
                                    .setContentTitle("Happy Belly")
                                    .setContentText( "Successfully Purchase ")
                                    .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                                    .build();
                            notificationManagerCompat.notify(1, notification);

                            getDialog().dismiss();
                            global.ItemLists.clear();
                        } else {
                            Toast.makeText(MainActivity.contextMainActivity, "There was a problem making a purchase", Toast.LENGTH_LONG).show();
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        getDialog().dismiss();
                    }
                })
                .create().show();
    }

}