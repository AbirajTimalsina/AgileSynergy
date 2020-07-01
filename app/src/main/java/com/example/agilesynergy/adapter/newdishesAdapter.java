package com.example.agilesynergy.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agilesynergy.R;
import com.example.agilesynergy.global.global;
import com.example.agilesynergy.models.newdishesModel;
import com.example.agilesynergy.models.popularfoodModel;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class newdishesAdapter extends RecyclerView.Adapter<newdishesAdapter.newdishesviewHolder> {

    Context mcontext;
    List<newdishesModel> newdishesModelList;
    JSONObject newdishesObject= new JSONObject();

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
        final newdishesModel newdishesModel = newdishesModelList.get(position);
        String imgpath = global.imagePath + newdishesModel.getNewdishespicture();
        Log.e("Image path is :" ,"Image path is" + imgpath);
        Picasso.get().load(imgpath).into(holder.newdishesimage);
        holder.newdishesname.setText(newdishesModel.getNewdishesname());
        holder.newdishesprice.setText(newdishesModel.getNewdishesprice());

        holder.newdisheslayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    newdishesObject.put("itemname",newdishesModel.getNewdishesname());
                    newdishesObject.put("itemprice",newdishesModel.getNewdishesprice());
                    newdishesObject.put("itemamount","2");
                    global.ItemLists.add(newdishesObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return newdishesModelList.size();
    }

    public class newdishesviewHolder extends RecyclerView.ViewHolder{

        ImageView newdishesimage;
        TextView newdishesname,newdishesprice;
        ConstraintLayout newdisheslayout;
        public newdishesviewHolder(@NonNull View itemView) {
            super(itemView);


            newdishesimage = itemView.findViewById(R.id.newdishesimage);
            newdishesname = itemView.findViewById(R.id.newdishesname);
            newdishesprice = itemView.findViewById(R.id.newdishesprice);
            newdisheslayout=itemView.findViewById(R.id.newdisheslayout);
        }
    }
}
