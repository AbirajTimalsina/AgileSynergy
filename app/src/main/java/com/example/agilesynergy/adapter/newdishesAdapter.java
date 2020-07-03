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
import com.example.agilesynergy.models.newdishesModel;
import com.example.agilesynergy.models.popularfoodModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class newdishesAdapter extends RecyclerView.Adapter<newdishesAdapter.newdishesviewHolder> {

    Context mcontext;
    List<newdishesModel> newdishesModelList;

    public newdishesAdapter(Context mcontext,   List<newdishesModel> newdishesModelList)
    {
        this.mcontext = mcontext;
        this.newdishesModelList = newdishesModelList;
    }

    @NonNull
    @Override
    public newdishesviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_newdishes,parent,false);
        return new newdishesviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull newdishesviewHolder holder, int position) {
        newdishesModel newdishesModel = newdishesModelList.get(position);
        String imgpath = global.imagePath + newdishesModel.getNewdishespicture();
        Log.e("Image path is :" ,"Image path is" + imgpath);
        Picasso.get().load(imgpath).into(holder.newdishesimage);
        holder.newdishesname.setText(newdishesModel.getNewdishesname());
        holder.newdishesprice.setText(newdishesModel.getNewdishesprice());


    }

    @Override
    public int getItemCount() {
        return newdishesModelList.size();
    }

    public class newdishesviewHolder extends RecyclerView.ViewHolder{

        ImageView newdishesimage;
        TextView newdishesname,newdishesprice;
        public newdishesviewHolder(@NonNull View itemView) {
            super(itemView);
            newdishesimage = itemView.findViewById(R.id.newdishesimage);
            newdishesname = itemView.findViewById(R.id.newdishesname);
            newdishesprice = itemView.findViewById(R.id.newdishesprice);
        }
    }
}
