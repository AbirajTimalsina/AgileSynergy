package com.example.agilesynergy.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.agilesynergy.ProfileActivity;
import com.example.agilesynergy.R;

public class fragmentProfile extends Fragment {

private Button btnProfile;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view= inflater. inflate (R.layout.fragment_profile, container, false);
        btnProfile=view.findViewById(R.id.btnProfile);
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getContext(), ProfileActivity.class);
                startActivity(intent);
            }
        });
return view;
    }
}