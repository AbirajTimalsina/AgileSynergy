package com.example.agilesynergy.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.agilesynergy.R;
import com.example.agilesynergy.adapter.fastfoodAdapter;
import com.example.agilesynergy.adapter.newdishesAdapter;
import com.example.agilesynergy.adapter.popularfoodAdapter;
import com.example.agilesynergy.api.fastfoodApi;
import com.example.agilesynergy.api.homeApi;
import com.example.agilesynergy.global.global;
import com.example.agilesynergy.models.fastfoodModel;
import com.example.agilesynergy.models.newdishesModel;
import com.example.agilesynergy.models.popularfoodModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    private RecyclerView fastfoodrecycleview, popularfoodrecycleview, newdishesrecycleview;

    //    Models list
    List<fastfoodModel> fastfoodModelList;
    List<popularfoodModel> popularfoodModelList;
    List<newdishesModel> newdishesModelList;


    //    Adapter
    fastfoodAdapter fastfoodAdapter;
    popularfoodAdapter popularfoodAdapter;
    newdishesAdapter newdishesAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        fastfoodrecycleview = view.findViewById(R.id.fastfoodrecycleview);
        fastfood();

        popularfoodrecycleview = view.findViewById(R.id.popularfoodrecycleview);
        popularfood();

        newdishesrecycleview= view.findViewById(R.id.newdishesrecycleview);
        newdishes();

        return view;
    }

    private void newdishes() {

        newdishesModelList = new ArrayList<>();
        homeApi homeApi = global.getInstance().create(homeApi.class);
        Call<List<newdishesModel>> newdisheslistcall = homeApi.getnewdishesdetails();
        newdisheslistcall.enqueue(new Callback<List<newdishesModel>>() {
            @Override
            public void onResponse(Call<List<newdishesModel>> call, Response<List<newdishesModel>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "Error" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<newdishesModel> newdishesModelList1 = response.body();
                newdishesAdapter = new newdishesAdapter(getContext(), newdishesModelList1);
                newdishesrecycleview.setAdapter(newdishesAdapter);
                newdishesrecycleview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

            }

            @Override
            public void onFailure(Call<List<newdishesModel>> call, Throwable t) {
                Log.d("Error Message", "Error" + t.getLocalizedMessage());
                Toast.makeText(getActivity(), "Api error. please check logcat or run", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void popularfood() {
        popularfoodModelList  = new ArrayList<>();
        homeApi homeApi = global.getInstance().create(homeApi.class);
        Call<List<popularfoodModel>> popularfoodlistcall = homeApi.getpopularfooddetails();
        popularfoodlistcall.enqueue(new Callback<List<popularfoodModel>>() {
            @Override
            public void onResponse(Call<List<popularfoodModel>> call, Response<List<popularfoodModel>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "Error" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<popularfoodModel> popularfoodModelList1 = response.body();
                popularfoodAdapter = new popularfoodAdapter(getContext(), popularfoodModelList1);
                popularfoodrecycleview.setAdapter(popularfoodAdapter);
                popularfoodrecycleview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

            }

            @Override
            public void onFailure(Call<List<popularfoodModel>> call, Throwable t) {
                Log.d("Error Message", "Error" + t.getLocalizedMessage());
                Toast.makeText(getActivity(), "Api error. please check logcat or run", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void fastfood() {

        fastfoodModelList = new ArrayList<>();
        fastfoodApi fastfoodapi = global.getInstance().create(fastfoodApi.class);

        Call<List<fastfoodModel>> fastfoodlistCall = fastfoodapi.getfastfooddetails();

        fastfoodlistCall.enqueue(new Callback<List<fastfoodModel>>() {
            @Override
            public void onResponse(Call<List<fastfoodModel>> call, Response<List<fastfoodModel>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "Error" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<fastfoodModel> fastfoodModelList1 = response.body();
                fastfoodAdapter = new fastfoodAdapter(getContext(), fastfoodModelList1);
                fastfoodrecycleview.setAdapter(fastfoodAdapter);
                fastfoodrecycleview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

            }

            @Override
            public void onFailure(Call<List<fastfoodModel>> call, Throwable t) {
                Log.d("Error Message", "Error" + t.getLocalizedMessage());
                Toast.makeText(getActivity(), "Api error. please check logcat or run", Toast.LENGTH_SHORT).show();
            }
        });


    }
}