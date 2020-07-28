package com.example.agilesynergy.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agilesynergy.R;
import com.example.agilesynergy.adapter.RecyclerAdapter;
import com.example.agilesynergy.api.itemapi;
import com.example.agilesynergy.global.global;
import com.example.agilesynergy.models.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;


public class MenuFragment extends Fragment {


    AutoCompleteTextView autocompletetextmenusearch;
    RecyclerView recyclerView, searchrecycle;

    TextView etName;
    private Map<String, String> MenuItems;

    public MenuFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        autocompletetextmenusearch = view.findViewById(R.id.autocompletetextmenusearch);
        etName = view.findViewById(R.id.etName);
        searchrecycle = view.findViewById(R.id.searchrecycle);
        MenuItems = new HashMap<>();
        recyclerView = view.findViewById(R.id.recyclerviewmenu);
        itemapi itemapi = global.getInstance().create(itemapi.class);
        Call<List<item>> itemList = itemapi.getAllItem();
        try {
            Response<List<item>> itemResponse = itemList.execute();
            if (itemResponse.isSuccessful()) {
                global.itemList = itemResponse.body();

                FragmentManager fm = getActivity().getSupportFragmentManager();

                RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getActivity(), itemResponse.body(), null, fm, "menu", null);
                recyclerView.setAdapter(recyclerAdapter);
                LinearLayoutManager layoutManager
                        = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(layoutManager);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Selecting one game then comparing it to all games till it matches. Data is retrieved from matched data
        for (item Item : global.itemList) {
            MenuItems.put(Item.getItemname(), Item.get_id()); //(key, value)
        }

        ArrayAdapter arrayAdapterCountry = new ArrayAdapter<>(getActivity(),
                android.R.layout.select_dialog_item, new ArrayList(MenuItems.keySet()));
        autocompletetextmenusearch.setAdapter(arrayAdapterCountry);
        autocompletetextmenusearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String key = adapterView.getItemAtPosition(i).toString(); //extracting _id with respect with selected game
                String ItemID = MenuItems.get(key);  //getting value through the key
                List<item> SearchItemlist = new ArrayList<>();
                for (item item : global.itemList) {
                    if (ItemID.equals(item.get_id())) {
                        SearchItemlist.add(item);
                    }
                }
                FragmentManager fm = getActivity().getSupportFragmentManager();
                RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getActivity(), SearchItemlist,
                        null, fm, "menu", null);
                searchrecycle.setAdapter(recyclerAdapter);
                LinearLayoutManager layoutManager
                        = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                searchrecycle.setLayoutManager(layoutManager);
            }
        });
        getActivity().closeContextMenu();
        return view;

    }
}
