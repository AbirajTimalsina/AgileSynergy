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
import com.example.agilesynergy.models.upcomingfoodModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class upcomingfoodAdapter  extends RecyclerView.Adapter<upcomingfoodAdapter.upcomingfoodViewholder> {

    Context mcontext;
    List<upcomingfoodModel> upcomingfoodModelList;

    public upcomingfoodAdapter(Context mcontext, List<upcomingfoodModel> upcomingfoodModelList)
    {
        this.mcontext = mcontext;
        this.upcomingfoodModelList= upcomingfoodModelList;
    }




    @NonNull
    @Override
    public upcomingfoodViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_upcoming,parent,false);
        return new upcomingfoodViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull upcomingfoodViewholder holder, int position) {

        upcomingfoodModel upcomingfoodModel = upcomingfoodModelList.get(position);

        String imgpath = global.imagePath  + upcomingfoodModel.getUpcomingfoodpicture();
        Log.e("Image path is :" ,"Image path is" + imgpath);

        Picasso.get().load(imgpath).into(holder.upcomingfoodimage);

        holder.upcomingfoodname.setText(upcomingfoodModel.getUpcomingfoodname());
        holder.upcomingfoodprice.setText(upcomingfoodModel.getUpcomingfoodprice());

    }

    @Override
    public int getItemCount() {
        return upcomingfoodModelList.size();
    }

    public class upcomingfoodViewholder extends RecyclerView.ViewHolder{

        ImageView upcomingfoodimage;
        TextView upcomingfoodname, upcomingfoodprice;


        public upcomingfoodViewholder(@NonNull View itemView) {
            super(itemView);
            upcomingfoodimage = itemView.findViewById(R.id.upcomingimage);
            upcomingfoodname = itemView.findViewById(R.id.upcomingname);
            upcomingfoodprice = itemView.findViewById(R.id.upcomingprice);



        }
    }



}
