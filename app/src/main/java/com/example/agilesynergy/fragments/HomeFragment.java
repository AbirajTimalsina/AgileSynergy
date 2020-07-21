package com.example.agilesynergy.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.agilesynergy.R;
import com.example.agilesynergy.adapter.fastfoodAdapter;
import com.example.agilesynergy.adapter.newdishesAdapter;
import com.example.agilesynergy.adapter.popularfoodAdapter;
import com.example.agilesynergy.adapter.regularfoodAdapter;
import com.example.agilesynergy.adapter.upcomingfoodAdapter;
import com.example.agilesynergy.api.fastfoodApi;
import com.example.agilesynergy.api.homeApi;
import com.example.agilesynergy.global.global;
import com.example.agilesynergy.models.fastfoodModel;
import com.example.agilesynergy.models.newdishesModel;
import com.example.agilesynergy.models.popularfoodModel;
import com.example.agilesynergy.models.regularfoodModel;
import com.example.agilesynergy.models.upcomingfoodModel;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private RecyclerView fastfoodrecycleview, popularfoodrecycleview, newdishesrecycleview, upcomingfoodrecycleview, regularrecycleview;


    private SwipeRefreshLayout swipeLayout;
    //    Models list
    List<fastfoodModel> fastfoodModelList;
    List<popularfoodModel> popularfoodModelList;
    List<newdishesModel> newdishesModelList;
    List<upcomingfoodModel> upcomingfoodModelList;
    List<regularfoodModel> regularfoodModelList;
    //    Adapter
    fastfoodAdapter fastfoodAdapter;
    popularfoodAdapter popularfoodAdapter;
    newdishesAdapter newdishesAdapter;
    upcomingfoodAdapter upcomingfoodAdapter;
    regularfoodAdapter regularfoodAdapter;

    CarouselView carouselView;
    int[] sampleImages = {R.drawable.ad1, R.drawable.ad2, R.drawable.ad3, R.drawable.ad4};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        fastfoodrecycleview = view.findViewById(R.id.fastfoodrecycleview);
        fastfood();

        carouselView = view.findViewById(R.id.carouselView);
        imageSlider();

        popularfoodrecycleview = view.findViewById(R.id.popularfoodrecycleview);
        popularfood();

        newdishesrecycleview = view.findViewById(R.id.newdishesrecycleview);
        newdishes();

        upcomingfoodrecycleview = view.findViewById(R.id.upcomingrecycleview);
        upcomingfood();


        regularrecycleview = view.findViewById(R.id.regularrecycleview);
        regularfood();


        //Refreshing
        swipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeLayout.setColorScheme(android.R.color.holo_blue_bright,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeLayout.setRefreshing(false);
                    }
                }, 2000);


            }
        });


        return view;
    }

    private void imageSlider() {

        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(sampleImages[position]);
            }
        });


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

    private void upcomingfood() {

        upcomingfoodModelList = new ArrayList<>();
        homeApi homeApi = global.getInstance().create(homeApi.class);
        Call<List<upcomingfoodModel>> upcomingfoodlistcall = homeApi.getupcomingfooddetails();
        upcomingfoodlistcall.enqueue(new Callback<List<upcomingfoodModel>>() {
            @Override
            public void onResponse(Call<List<upcomingfoodModel>> call, Response<List<upcomingfoodModel>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "Error" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<upcomingfoodModel> upcomingfoodModelList1 = response.body();
                upcomingfoodAdapter = new upcomingfoodAdapter(getContext(), upcomingfoodModelList1);
                upcomingfoodrecycleview.setAdapter(upcomingfoodAdapter);
                upcomingfoodrecycleview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            }

            @Override
            public void onFailure(Call<List<upcomingfoodModel>> call, Throwable t) {
                Log.d("Error Message", "Error" + t.getLocalizedMessage());
                Toast.makeText(getActivity(), "Api error. please check logcat or run", Toast.LENGTH_SHORT).show();
            }
        });


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

    private void fastfood() {
        fastfoodModelList = new ArrayList<>();
        fastfoodApi fastfoodapi = global.getInstance().create(fastfoodApi.class);
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
