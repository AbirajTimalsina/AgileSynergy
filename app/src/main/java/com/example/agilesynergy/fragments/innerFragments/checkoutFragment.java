package com.example.agilesynergy.fragments.innerFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agilesynergy.classes.userPurchase;
import com.example.agilesynergy.global.global;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.agilesynergy.R;
import com.example.agilesynergy.adapter.RecyclerAdapter;

public class checkoutFragment extends Fragment {

    private RecyclerView recyclerView;
    private Button btnPurchase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_checkout, container, false);

        btnPurchase = view.findViewById(R.id.purchase);
        final userPurchase userPurchase = new userPurchase();
        btnPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (global.ItemLists.size() > 0) {
                    if (userPurchase.userPurchaseFood()) {
                        Toast.makeText(getContext(), "Purchased Successfully", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        recyclerView = view.findViewById(R.id.recyclerviewcheckout);
        try {
            RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getActivity(), null,
                    global.ItemLists, null, "checkout");
            recyclerView.setAdapter(recyclerAdapter);
            LinearLayoutManager layoutManager
                    = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(layoutManager);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }
}