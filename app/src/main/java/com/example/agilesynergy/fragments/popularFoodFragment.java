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
import com.example.agilesynergy.adapter.popularfoodAdapter;
import com.example.agilesynergy.api.homeApi;
import com.example.agilesynergy.global.global;
import com.example.agilesynergy.models.popularfoodModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class popularFoodFragment extends Fragment {
    private RecyclerView popularfoodrecycleview;

    List<popularfoodModel> popularfoodModelList;

    com.example.agilesynergy.adapter.popularfoodAdapter popularfoodAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View v = inflater.inflate(R.layout.fragment_popular_food, container, false);

        popularfoodrecycleview = v.findViewById(R.id.popularfoodrecycleview);
        popularfood();

        return v;
    }

    private void popularfood() {
        popularfoodModelList = new ArrayList<>();
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

}