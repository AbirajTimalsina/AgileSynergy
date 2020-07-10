package com.example.agilesynergy.adapter;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agilesynergy.R;
import com.example.agilesynergy.fragments.HomeFragment;
import com.example.agilesynergy.fragments.innerFragments.ItemFragment;
import com.example.agilesynergy.global.global;
import com.example.agilesynergy.models.item;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {


    private Context mcontext;
    private List<item> listItems;
    private ArrayList<JSONObject> listObjects = new ArrayList<>();
    private FragmentManager fm;
    private String location_Fragment;

    public RecyclerAdapter(Context mcontext, List<item> listItems, ArrayList<JSONObject> listObjects,
                           FragmentManager fm, String location_Fragment) {
        this.mcontext = mcontext;
        this.listItems = listItems;
        this.listObjects = listObjects;
        this.fm = fm;
        this.location_Fragment = location_Fragment;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        switch (location_Fragment) {
            case "menu":
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_items, parent, false);
                break;
            case "checkout":
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.checkout_item, parent, false);
                break;
        }
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, final int position) {
        switch (location_Fragment) {
            case "menu":
                final item item = listItems.get(position);
                holder.itemname.setText(item.getItemname());
                holder.itemprice.setText(item.getItemprice());
                holder.itemingredient.setText(item.getItemingredient());
                String imagePath = global.imagePath + item.getItempicture();
                Picasso.get().load(imagePath).into(holder.imageitempicture);
                holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //bundle Arguments is not working.
                        global.item = item;
                        fm.beginTransaction().replace(R.id.frame_container, new ItemFragment()).
                                setCustomAnimations(R.anim.enter_from_right, R.anim.exit_from_right).
                                addToBackStack(null).commit();
                    }
                });
                break;
            case "checkout":
                JSONObject items = listObjects.get(position);
                holder.checkoutItemName.setText(items.optString("itemname"));
                holder.checkoutItemPrice.setText(items.optString("itemprice"));
                holder.checkoutItemAmount.setText(items.optString("itemamount"));
                Integer Price, Amount, afterAmount;
                Price = Integer.parseInt(items.optString("itemprice"));
                Amount = Integer.parseInt(items.optString("itemamount"));
                afterAmount = Price * Amount;
                holder.checkoutItemAfterAmount.setText(Integer.toString(afterAmount));

                holder.btnCheckoutDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listObjects.remove(position);
                        notifyDataSetChanged();
                        if (listObjects.size() == 0) {
                            fm.popBackStackImmediate();  //returns to previous fragment, granted it was added to stack.
                        }
                    }
                });
                break;
        }
    }

    private int listCount = 0;

    @Override
    public int getItemCount() {
        switch (location_Fragment) {
            case "menu":
                listCount = listItems.size();
                break;
            case "checkout":
                listCount = listObjects.size();
                break;
        }
        return listCount;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        //Menu Elements
        ImageView imageitempicture;
        TextView itemname, itemprice, itemingredient;
        ImageButton btnCheckoutDelete;
        LinearLayout linearLayout;
        //Checkout Elements
        TextView checkoutItemName, checkoutItemPrice, checkoutItemAmount, checkoutItemAfterAmount;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            switch (location_Fragment) {
                case "menu":
                    imageitempicture = itemView.findViewById(R.id.imageviewmenupicture);
                    itemname = itemView.findViewById(R.id.itemmenuname);
                    itemprice = itemView.findViewById(R.id.itemmenuprice);
                    itemingredient = itemView.findViewById(R.id.itemmenuingredient);
                    linearLayout = itemView.findViewById(R.id.linearmenu);
                    break;
                case "checkout":
                    checkoutItemName = itemView.findViewById(R.id.txtcheckoutitemname);
                    checkoutItemPrice = itemView.findViewById(R.id.txtcheckoutitemprice);
                    checkoutItemAmount = itemView.findViewById(R.id.txtcheckoutitemamount);
                    checkoutItemAfterAmount = itemView.findViewById(R.id.txtcheckoutitemafteramount);
                    btnCheckoutDelete = itemView.findViewById(R.id.btncheckoutitemdelete);
                    break;
            }

        }

    }
}


