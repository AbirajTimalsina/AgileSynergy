package com.example.agilesynergy.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.facebook.login.widget.ProfilePictureView.TAG;

public class ProfileFragment extends Fragment {

    ImageView imguser;
    private TextView tvfullname, tvemail, tvphoneno, tvaddress;
    Button btnlogout;

    RecyclerView phrecyclehsitory;
    user User;

    List<purchasehistory> purchasehistoryList;
    purchasehistoryAdapter purchasehistoryAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        imguser = view.findViewById(R.id.imguser);
        tvfullname = view.findViewById(R.id.tvfullname);
        tvemail = view.findViewById(R.id.tvemail);
        tvphoneno = view.findViewById(R.id.tvphoneno);
        tvaddress = view.findViewById(R.id.tvaddress);
        phrecyclehsitory = view.findViewById(R.id.phrecyclehsitory);
        btnlogout = view.findViewById(R.id.btnlogout);
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
        loadcurrentuser();
        getActivity();
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

                // Purchase History
//                purchasehistoryList = response.body().getPurchase();
//                purchasehistoryAdapter = new purchasehistoryAdapter(getContext(), purchasehistoryList);
//                phrecyclehsitory.setAdapter(purchasehistoryAdapter);
//                phrecyclehsitory.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

            }

            @Override
            public void onFailure(Call<user> call, Throwable t) {
                Toast.makeText(getContext(), "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}