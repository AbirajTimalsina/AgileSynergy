package com.example.agilesynergy.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agilesynergy.LoginActivity;
import com.example.agilesynergy.R;
import com.example.agilesynergy.adapter.purchasehistoryAdapter;
import com.example.agilesynergy.api.userapi;
import com.example.agilesynergy.global.global;
import com.example.agilesynergy.models.purchasehistory;
import com.example.agilesynergy.models.user;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class ProfileFragment extends Fragment {

    ImageView imguser;
    private TextView tvfullname, tvemail, tvphoneno, tvaddress;
    Button btnlogout;
    ImageButton changepp;
    RecyclerView phrecyclehsitory;
    List<purchasehistory> purchasehistoryList;
    purchasehistoryAdapter purchasehistoryAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_profile, container, false);
        imguser = view.findViewById(R.id.imguser);
        tvfullname = view.findViewById(R.id.tvfullname);
        tvemail = view.findViewById(R.id.tvemail);
        tvphoneno = view.findViewById(R.id.tvphoneno);
        tvaddress = view.findViewById(R.id.tvaddress);
        phrecyclehsitory = view.findViewById(R.id.phrecyclehsitory);
        btnlogout = view.findViewById(R.id.btnlogout);
        changepp = view.findViewById(R.id.changepp);
        loadcurrentuser();
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Log Out!").
                        setMessage("You sure, that you want to logout?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        global.token = "Bearer ";
                        Intent logout = new Intent(getContext(), LoginActivity.class)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(logout);
                        getActivity().finish();
                    }
                });
                builder.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert11 = builder.create();
                alert11.show();
            }
        });
        return view;
    }

    private void loadcurrentuser() {
        userapi Userapi = global.getInstance().create(userapi.class);
        Call<user> userCall = Userapi.getUserDetails(global.token);
        try {
            Response<user> profileresponse = userCall.execute();

            if (profileresponse.isSuccessful()) {
                String imagepath = null;
                imagepath = global.imagePath + profileresponse.body().getProfile_image();
                Picasso.get().load(imagepath).into(imguser);
            }
            tvfullname.setText(profileresponse.body().getFullname());
            tvemail.setText(profileresponse.body().getEmail());
            tvphoneno.setText(profileresponse.body().getPhonenumber());

            // Purchase History
            purchasehistoryList = profileresponse.body().getPurchase();
            purchasehistoryAdapter = new purchasehistoryAdapter(getContext(), purchasehistoryList);
            phrecyclehsitory.setAdapter(purchasehistoryAdapter);
            phrecyclehsitory.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}