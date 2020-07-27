package com.example.agilesynergy.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agilesynergy.R;
import com.example.agilesynergy.adapter.favouriteAdapter;
import com.example.agilesynergy.api.userapi;
import com.example.agilesynergy.global.global;
import com.example.agilesynergy.models.feedbackModel;
import com.example.agilesynergy.models.user;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class FavouriteFragment extends Fragment {

    RecyclerView fav_recycleview;
    List<feedbackModel> feedbackModels;
    favouriteAdapter favouriteAdapter;
    user user;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_favourite, container, false);

        fav_recycleview = view.findViewById(R.id.fav_recycleview);
        userapi Userapi = global.getInstance().create(userapi.class);
        Call<user> userCall = Userapi.getUserDetails(global.token);
        try {
           Response<user> favouriteresponse = userCall.execute();
            feedbackModels = favouriteresponse.body().getFeedback();
            favouriteAdapter = new favouriteAdapter(getContext(), feedbackModels);
            fav_recycleview.setAdapter(favouriteAdapter);
            fav_recycleview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }
}