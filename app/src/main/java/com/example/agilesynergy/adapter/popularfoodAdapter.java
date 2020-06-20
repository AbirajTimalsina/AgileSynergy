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
import com.example.agilesynergy.models.popularfoodModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class popularfoodAdapter extends RecyclerView.Adapter<popularfoodAdapter.popularfoodviewHolder> {

    Context mcontext;
    List<popularfoodModel> popularfoodModelList;

    public popularfoodAdapter(Context mcontext,  List<popularfoodModel> popularfoodModelList)
    {
        this.mcontext = mcontext;
        this.popularfoodModelList = popularfoodModelList;
    }


    @NonNull
    @Override
    public popularfoodviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_popularfood,parent,false);
        return new popularfoodviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull popularfoodviewHolder holder, int position) {

        popularfoodModel popularfoodModel = popularfoodModelList.get(position);
        String imgpath = global.imagePath + popularfoodModel.getPopularfoodpicture();
        Log.e("Image path is :" ,"Image path is" + imgpath);
        Picasso.get().load(imgpath).into(holder.popularfoodimage);
        holder.popularfoodname.setText(popularfoodModel.getPopularfoodname());
        holder.popularfoodprice.setText(popularfoodModel.getPopularfoodprice());
    }

    @Override
    public int getItemCount() {
        return popularfoodModelList.size();
    }

    public class popularfoodviewHolder extends RecyclerView.ViewHolder{

        ImageView popularfoodimage;
        TextView popularfoodname,popularfoodprice;

        public popularfoodviewHolder(@NonNull View itemView) {
            super(itemView);

            popularfoodimage = itemView.findViewById(R.id.popularfoodimage);
            popularfoodname = itemView.findViewById(R.id.popufoodname);
            popularfoodprice = itemView.findViewById(R.id.popularfoodprice);

        }
    }
}
