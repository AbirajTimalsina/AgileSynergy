package com.example.agilesynergy.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.example.agilesynergy.R;
import com.example.agilesynergy.adapter.fastfoodAdapter;
import com.example.agilesynergy.adapter.newdishesAdapter;
import com.example.agilesynergy.adapter.popularfoodAdapter;
import com.example.agilesynergy.adapter.regularfoodAdapter;
import com.example.agilesynergy.adapter.upcomingfoodAdapter;
import com.example.agilesynergy.api.homeApi;
import com.example.agilesynergy.global.global;
import com.example.agilesynergy.models.fastfoodModel;
import com.example.agilesynergy.models.newdishesModel;
import com.example.agilesynergy.models.popularfoodModel;
import com.example.agilesynergy.models.regularfoodModel;
import com.example.agilesynergy.models.upcomingfoodModel;
import com.google.android.material.tabs.TabLayout;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private RecyclerView newdishesrecycleview, upcomingfoodrecycleview ;


    private SwipeRefreshLayout swipeLayout;
    //    Models list


    List<newdishesModel> newdishesModelList;
    List<upcomingfoodModel> upcomingfoodModelList;

    //    Adapter

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



        carouselView = view.findViewById(R.id.carouselView);
        imageSlider();



        newdishesrecycleview = view.findViewById(R.id.newdishesrecycleview);
        newdishes();

        upcomingfoodrecycleview = view.findViewById(R.id.upcomingrecycleview);
        upcomingfood();


        // Setting ViewPager for each Tabs
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        // Set Tabs inside Toolbar
        TabLayout tabs = (TabLayout) view.findViewById(R.id.result_tabs);
        tabs.setupWithViewPager(viewPager);


//        //Refreshing
//        swipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
//        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                swipeLayout.setColorScheme(android.R.color.holo_blue_bright,
//                        android.R.color.holo_green_light,
//                        android.R.color.holo_orange_light,
//                        android.R.color.holo_red_light);
//
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        swipeLayout.setRefreshing(false);
//                    }
//                }, 2000);
//
//
//            }
//        });


        return view;
    }

    // Add Fragments to Tabs
    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getChildFragmentManager());
        adapter.addFragment(new FastFoodFragment(), "Fast Food");
        adapter.addFragment(new popularFoodFragment(), "Popular Food");
        adapter.addFragment(new RegularFoodFragment(), "Regular Food");
        viewPager.setAdapter(adapter);

    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public Adapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
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



}
