package com.example.agilesynergy.adapter;


import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agilesynergy.R;
import com.example.agilesynergy.classes.StrictModeClass;
import com.example.agilesynergy.global.global;
import com.example.agilesynergy.models.fastfoodModel;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class fastfoodAdapter extends RecyclerView.Adapter<fastfoodAdapter.fastfoodViewHolder> {

    Context mcontext;
    List<fastfoodModel> fastfoodModelList;

    public fastfoodAdapter(Context mcontext, List<fastfoodModel> fastfoodModelList) {
        this.mcontext = mcontext;
        this.fastfoodModelList = fastfoodModelList;
    }


    @NonNull
    @Override
    public fastfoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_fastfood, parent, false);

        return new fastfoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull fastfoodViewHolder holder, int position) {

        fastfoodModel fastfoodModel = fastfoodModelList.get(position);

        String imgpath = global.imagePath + fastfoodModel.getFastfoodpicture();
        Log.e("Image path is :", "Image path is" + imgpath);

        Picasso.get().load(imgpath).into(holder.fastfoodpicture);
        holder.fastfoodname.setText(fastfoodModel.getFastfoodname());
        holder.fastfoodprice.setText(fastfoodModel.getFastfoodprice());


    }

    @Override
    public int getItemCount() {
        return fastfoodModelList.size();
    }

    public class fastfoodViewHolder extends RecyclerView.ViewHolder {

        ImageView fastfoodpicture;
        TextView fastfoodname, fastfoodprice;


        public fastfoodViewHolder(@NonNull View itemView) {
            super(itemView);

            fastfoodpicture = itemView.findViewById(R.id.fastfoodpicture);
            fastfoodname = itemView.findViewById(R.id.fastfoodname);
            fastfoodprice = itemView.findViewById(R.id.fastfoodprice);
        }
    }


}
