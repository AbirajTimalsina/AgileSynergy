package com.example.agilesynergy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agilesynergy.R;
import com.example.agilesynergy.global.global;
import com.example.agilesynergy.models.item;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {

    Context mcontext;
    private List<item> listItems;

    public RecyclerAdapter(Context mcontext, List<item> listItems) {
        this.mcontext = mcontext;
        this.listItems = listItems;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_items, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {


        final item item = listItems.get(position);

        holder.itemname.setText(item.getItemname());
        holder.itemprice.setText(item.getItemprice());
        holder.itemingredient.setText(item.getItemingredient());
        String imagePath = global.imagePath + item.getItempicture();
        Picasso.get().load(imagePath).into(holder.imageitempicture);


    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        ImageView imageitempicture;
        TextView itemname, itemprice, itemingredient;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            imageitempicture = itemView.findViewById(R.id.imageviewmenupicture);
            itemname = itemView.findViewById(R.id.itemmenuname);
            itemprice = itemView.findViewById(R.id.itemmenuprice);
            itemingredient = itemView.findViewById(R.id.itemmenuingredient);

        }
    }
}
