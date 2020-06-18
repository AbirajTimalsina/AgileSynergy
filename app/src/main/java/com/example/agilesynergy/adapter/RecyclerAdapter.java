package com.example.agilesynergy.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agilesynergy.R;
import com.example.agilesynergy.fragments.MenuFragment;
import com.example.agilesynergy.fragments.innerFragments.ItemFragment;
import com.example.agilesynergy.global.global;
import com.example.agilesynergy.models.item;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {

    Context mcontext;
    private List<item> listItems;
    FragmentManager fm;

    public RecyclerAdapter(Context mcontext, List<item> listItems, FragmentManager fm) {
        this.mcontext = mcontext;
        this.listItems = listItems;
        this.fm = fm;
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

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ItemFragment itemFragment = new ItemFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("itemObject", item);
                itemFragment.setArguments(bundle);
                global.item=item;
                fm.beginTransaction().replace(R.id.frame_container, new ItemFragment()).addToBackStack(null).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        ImageView imageitempicture;
        TextView itemname, itemprice, itemingredient;
        LinearLayout linearLayout;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            imageitempicture = itemView.findViewById(R.id.imageviewmenupicture);
            itemname = itemView.findViewById(R.id.itemmenuname);
            itemprice = itemView.findViewById(R.id.itemmenuprice);
            itemingredient = itemView.findViewById(R.id.itemmenuingredient);
            linearLayout = itemView.findViewById(R.id.linearmenu);

        }
    }
}
