package com.example.agilesynergy.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agilesynergy.R;
import com.example.agilesynergy.adapter.regularfoodAdapter;
import com.example.agilesynergy.api.homeApi;
import com.example.agilesynergy.global.global;
import com.example.agilesynergy.models.regularfoodModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegularFoodFragment extends Fragment {
    private RecyclerView regularrecycleview;
    List<regularfoodModel> regularfoodModelList;
    com.example.agilesynergy.adapter.regularfoodAdapter regularfoodAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_regular_food, container, false);
        regularrecycleview = v.findViewById(R.id.regularrecycleview);
        regularfood();
        return v;
    }

    private void regularfood() {

        regularfoodModelList = new ArrayList<>();
        homeApi homeApi = global.getInstance().create(com.example.agilesynergy.api.homeApi.class);
        Call<List<regularfoodModel>> regularfoodlistcall = homeApi.getregularfooddetails();
        regularfoodlistcall.enqueue(new Callback<List<regularfoodModel>>() {
            @Override
            public void onResponse(Call<List<regularfoodModel>> call, Response<List<regularfoodModel>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "Error" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<regularfoodModel> regularfoodModelList1 = response.body();
                regularfoodAdapter = new regularfoodAdapter(getContext(), regularfoodModelList1);
                regularrecycleview.setAdapter(regularfoodAdapter);
                regularrecycleview.setLayoutManager(new GridLayoutManager(getContext(), 3, LinearLayoutManager.VERTICAL, false));
            }

            @Override
            public void onFailure(Call<List<regularfoodModel>> call, Throwable t) {
                Log.d("Error Message", "Error" + t.getLocalizedMessage());
                Toast.makeText(getActivity(), "Api error. please check logcat or run", Toast.LENGTH_SHORT).show();
            }
        });
    }

}