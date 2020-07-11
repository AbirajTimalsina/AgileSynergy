package com.example.agilesynergy.fragments.innerFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agilesynergy.classes.userPurchase;
import com.example.agilesynergy.global.global;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agilesynergy.R;
import com.example.agilesynergy.adapter.RecyclerAdapter;

public class checkoutFragment extends Fragment {

    private RecyclerView recyclerView;
    private TextView tvPurchase, tvCounter;
    private LinearLayout linearLayout;
    private CountDownTimer countDownTimer = new CountDownTimer(10000, 1000) {

        public void onTick(long millisUntilFinished) {
            tvCounter.setText("Remaining Time to Cancel : " + millisUntilFinished / 1000);
        }

        public void onFinish() {
            boolean isPurchased = new userPurchase().userPurchaseFood();
            if (isPurchased) {
                Toast.makeText(getContext(), "Purchased Successfully", Toast.LENGTH_SHORT).show();
                getActivity().getSupportFragmentManager().popBackStackImmediate();
                global.ItemLists.clear();
                tvCounter.setText("Order in Progress!");
            } else {
                Toast.makeText(getContext(), "There was a problem making a purchase", Toast.LENGTH_LONG).show();
                tvCounter.setVisibility(View.GONE);
                tvPurchase.setText("Purchase");
                linearLayout.startAnimation(fadeAnimation());
            }

        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_checkout, container, false);
        tvPurchase = view.findViewById(R.id.tvpurchase);
        tvCounter = view.findViewById(R.id.tvcounter);
        linearLayout = view.findViewById(R.id.linearlayoutpurchase);
        tvCounter.setVisibility(View.GONE); //Making the timer textview invisible at initiation


//        final userPurchase userPurchase = new userPurchase();
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (global.ItemLists.size() > 0) {

                    if (tvPurchase.getText().toString().toLowerCase().equals("purchase")) {

                        tvPurchase.setText("Cancel");
                        tvCounter.setVisibility(View.VISIBLE);
                        countDownTimer.start();
                        linearLayout.startAnimation(fadeAnimation());
                    } else {
                        countDownTimer.cancel();
                        tvCounter.setVisibility(View.GONE);
                        tvPurchase.setText("Purchase");
                        linearLayout.startAnimation(fadeAnimation());
                    }
                }
            }
        });
        FragmentManager fm = getActivity().getSupportFragmentManager();

        recyclerView = view.findViewById(R.id.recyclerviewcheckout);
        try {
            RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getActivity(), null,
                    global.ItemLists, fm, "checkout");
            recyclerView.setAdapter(recyclerAdapter);
            LinearLayoutManager layoutManager
                    = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(layoutManager);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }

    public Animation fadeAnimation() {
        return AnimationUtils.loadAnimation(getContext(), R.anim.fadein);
    }

}