package com.example.agilesynergy.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.agilesynergy.R;
import com.example.agilesynergy.adapter.fastfoodAdapter;
import com.example.agilesynergy.api.homeApi;
import com.example.agilesynergy.global.global;
import com.example.agilesynergy.models.fastfoodModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class FastFoodFragment extends Fragment {

    private RecyclerView fastfoodrecycleview;

    List<fastfoodModel> fastfoodModelList;
    com.example.agilesynergy.adapter.fastfoodAdapter fastfoodAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  v = inflater.inflate(R.layout.fragment_fast_food, container, false);

        fastfoodrecycleview = v.findViewById(R.id.fastfoodrecycleview);
        fastfood();

        return v;
    }


    private void fastfood() {
        fastfoodModelList = new ArrayList<>();
        homeApi fastfoodapi = global.getInstance().create(homeApi.class);
        Call<List<fastfoodModel>> fastfoodlistCall = fastfoodapi.getfastfooddetails();
        try {
            Response<List<fastfoodModel>> fastfoodresponse = fastfoodlistCall.execute();
            fastfoodModelList = fastfoodresponse.body();
            fastfoodAdapter = new fastfoodAdapter(getContext(), fastfoodModelList);
            fastfoodrecycleview.setAdapter(fastfoodAdapter);
            fastfoodrecycleview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}