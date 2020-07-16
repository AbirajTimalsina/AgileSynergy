package com.example.agilesynergy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agilesynergy.R;
import com.example.agilesynergy.models.purchasehistory;

import java.util.List;

public class purchasehistoryAdapter extends RecyclerView.Adapter<purchasehistoryAdapter.purchasehistoryViewHolder> {
    Context mcontext;
    List<purchasehistory> purchasehistoryList;

    public purchasehistoryAdapter(Context mcontext, List<purchasehistory> purchasehistoryList) {
        this.mcontext = mcontext;
        this.purchasehistoryList = purchasehistoryList;
    }


    @NonNull
    @Override
    public purchasehistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_purchasehsitroy, parent, false);
        return new purchasehistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull purchasehistoryViewHolder holder, int position) {
        purchasehistory purchasehistory = purchasehistoryList.get(position);
        holder.tvitemname.setText(purchasehistory.getItemname());
        holder.tvitemprice.setText(purchasehistory.getItemprice());

    }

    @Override
    public int getItemCount() {
        return purchasehistoryList.size();
    }


    public class purchasehistoryViewHolder extends RecyclerView.ViewHolder {

        TextView tvitemname, tvitemprice;

        public purchasehistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvitemname = itemView.findViewById(R.id.tvitemname);
            tvitemprice = itemView.findViewById(R.id.tvitemprice);
        }
    }

}
