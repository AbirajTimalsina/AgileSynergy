package com.example.agilesynergy.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agilesynergy.R;
import com.example.agilesynergy.global.global;
import com.example.agilesynergy.models.regularfoodModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class regularfoodAdapter extends RecyclerView.Adapter<regularfoodAdapter.regularViewHolder> {

    Context mcontext;
    List<regularfoodModel> regularfoodModelList;

    public regularfoodAdapter(Context mcontext, List<regularfoodModel> regularfoodModelList) {
        this.mcontext = mcontext;
        this.regularfoodModelList = regularfoodModelList;
    }

    @NonNull
    @Override
    public regularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_regularfood, parent, false);
        return new regularViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull regularViewHolder holder, int position) {
        regularfoodModel regularfoodModel = regularfoodModelList.get(position);

        String imgpath = global.imagePath + regularfoodModel.getRegularfoodpicture();
        Log.e("Image path is :", "Image path is" + imgpath);

        Picasso.get().load(imgpath).into(holder.regularfoodimage);

        holder.regularfoodname.setText(regularfoodModel.getRegularfoodname());
        holder.regularfoodprice.setText(regularfoodModel.getRegularfoodprice());
    }

    @Override
    public int getItemCount() {
        return regularfoodModelList.size();
    }

    public class regularViewHolder extends RecyclerView.ViewHolder {

        ImageView regularfoodimage;
        TextView regularfoodname, regularfoodprice;

        public regularViewHolder(@NonNull View itemView) {
            super(itemView);
            regularfoodimage = itemView.findViewById(R.id.regularfoodimage);
            regularfoodname = itemView.findViewById(R.id.regularfoodname);
            regularfoodprice = itemView.findViewById(R.id.regularfoodprice);
        }
    }

}
