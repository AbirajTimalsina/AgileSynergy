package com.example.agilesynergy.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;

import com.example.agilesynergy.R;
import com.example.agilesynergy.adapter.RecyclerAdapter;
import com.example.agilesynergy.api.itemapi;
import com.example.agilesynergy.global.global;
import com.example.agilesynergy.models.item;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;


public class MenuFragment extends Fragment {

AutoCompleteTextView autocompletetextmenusearch;
    RecyclerView recyclerView;
RecyclerAdapter recyclerAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
  autocompletetextmenusearch=view.findViewById(R.id.autocompletetextmenusearch);
        recyclerView = view.findViewById(R.id.recyclerviewmenu);
        itemapi itemapi = global.getInstance().create(itemapi.class);
        Call<List<item>> itemList = itemapi.getAllItem();

  autocompletetextmenusearch.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
          recyclerAdapter.getFilter().filter(s);
      }

      @Override
      public void afterTextChanged(Editable s) {

      }
  });


        try {
            Response<List<item>> itemResponse = itemList.execute();
            if (itemResponse.isSuccessful()) {

                FragmentManager fm = getActivity().getSupportFragmentManager();

                RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getContext(), itemResponse.body(),null, fm, "menu");
                recyclerView.setAdapter(recyclerAdapter);
                LinearLayoutManager layoutManager
                        = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(layoutManager);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }
}