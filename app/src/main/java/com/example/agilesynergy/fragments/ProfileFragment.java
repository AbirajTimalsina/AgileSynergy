package com.example.agilesynergy.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agilesynergy.LoginActivity;
import com.example.agilesynergy.R;
import com.example.agilesynergy.api.userapi;
import com.example.agilesynergy.global.global;
import com.example.agilesynergy.models.user;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {

    ImageView imguser;
    private TextView tvfullname, tvemail, tvphoneno;
    Button btnedit;
    user User;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        imguser = view.findViewById(R.id.imguser);
        tvfullname = view.findViewById(R.id.tvfullname);
        tvemail = view.findViewById(R.id.tvemail);
        tvphoneno = view.findViewById(R.id.tvphoneno);
        btnedit = view.findViewById(R.id.btnlogout);
        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Log Out!").
                        setMessage("You sure, that you want to logout?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent logout = new Intent(getContext(), LoginActivity.class);
                        startActivity(logout);
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
        loadcurrentuser();
        return view;
    }

    private void loadcurrentuser() {
        userapi Userapi = global.getInstance().create(userapi.class);
        Call<user> userCall = Userapi.getUserDetails(global.token);
        userCall.enqueue(new Callback<user>() {
            @Override
            public void onResponse(Call<user> call, Response<user> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                User = response.body();
                if (response.body() != null) {
                    String imagepath = null;
                    imagepath = global.imagePath + response.body().getProfile_image();
                    Picasso.get().load(imagepath).into(imguser);
                }
                tvfullname.setText(response.body().getFullname());
                tvemail.setText(response.body().getEmail());
                tvphoneno.setText(response.body().getPhonenumber());
            }

            @Override
            public void onFailure(Call<user> call, Throwable t) {
                Toast.makeText(getContext(), "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}