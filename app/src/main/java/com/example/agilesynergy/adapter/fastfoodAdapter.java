package com.example.agilesynergy.adapter;


import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agilesynergy.R;
import com.example.agilesynergy.classes.StrictModeClass;
import com.example.agilesynergy.global.global;
import com.example.agilesynergy.models.fastfoodModel;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class fastfoodAdapter extends RecyclerView.Adapter<fastfoodAdapter.fastfoodViewHolder> {

    Context mcontext;
    List<fastfoodModel> fastfoodModelList;
    private String location_Fragment;
    private ArrayList<JSONObject> listObjects = new ArrayList<>();
    private JSONObject itemList = new JSONObject();

    public fastfoodAdapter(Context mcontext, List<fastfoodModel> fastfoodModelList, ArrayList<JSONObject> listObjects, String location_Fragment) {
        this.mcontext = mcontext;
        this.fastfoodModelList = fastfoodModelList;
        this.listObjects = listObjects;
        this.location_Fragment = location_Fragment;
    }


    @NonNull
    @Override
    public fastfoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = null;
        switch (location_Fragment) {
            case "home":
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_fastfood, parent, false);
                break;
            case "checkout":
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.checkout_item, parent, false);
                break;
        }

        return new fastfoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull fastfoodViewHolder holder, int position) {

        fastfoodModel fastfoodModel123 = fastfoodModelList.get(position);
        switch (location_Fragment) {
            case "home":
                Random rnd = new Random();
                int currentColor = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                holder.imageView.setColorFilter(currentColor);
                String imgpath = global.imagePath + fastfoodModel123.getFastfoodpicture();
                Log.e("Image path is :", "Image path is" + imgpath);
                Picasso.get().load(imgpath).into(holder.fastfoodpicture);
                holder.fastfoodname.setText(fastfoodModel123.getFastfoodname());


                break;
            case "checkout":

                JSONObject fastfood = listObjects.get(position);
                holder.checkoutItemName.setText(fastfood.optString("fastfoodname").toString());
                holder.checkoutItemPrice.setText(fastfood.optString("fastfoodprice").toString());
                holder.checkoutItemAmount.setText(fastfood.optString("fastfoodamount").toString());
                Integer Price, Amount, afterAmount;
                Price = Integer.parseInt(fastfood.optString("fastfoodprice").toString());
                Amount = Integer.parseInt(fastfood.optString("fastfoodamount").toString());
                afterAmount = Price * Amount;
                holder.checkoutItemAfterAmount.setText(Integer.toString(afterAmount));
                break;

        }


    }

    int listCount = 0;

    @Override
    public int getItemCount() {

        switch (location_Fragment) {
            case "home":
                listCount = fastfoodModelList.size();
                break;
            case "checkout":
                listCount = listObjects.size();
                break;
        }
        return listCount;
    }

    public class fastfoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView fastfoodpicture, imageView;
        TextView fastfoodname, fastfoodprice, txtquanity;
        private Button btnadd, btnminus, btncart;
        private Integer Amount;

        //Checkout Elements
        TextView checkoutItemName, checkoutItemPrice, checkoutItemAmount, checkoutItemAfterAmount;

        public fastfoodViewHolder(@NonNull final View itemView) {
            super(itemView);

            switch (location_Fragment) {
                case "home":
                    imageView = itemView.findViewById(R.id.imageView);
                    fastfoodpicture = itemView.findViewById(R.id.fastfoodpicture);
                    fastfoodname = itemView.findViewById(R.id.fastfoodname);
                    fastfoodprice = itemView.findViewById(R.id.fastfoodprice);
                    btnadd = itemView.findViewById(R.id.btnadd);
                    btnminus = itemView.findViewById(R.id.btndes);
                    txtquanity = itemView.findViewById(R.id.txtquanity);
                    btncart = itemView.findViewById(R.id.btncart);
                    btnadd.setOnClickListener(this);
                    btnminus.setOnClickListener(this);
                    btncart.setOnClickListener(this);
                    break;
                case "checkout":
                    checkoutItemName = itemView.findViewById(R.id.txtcheckoutitemname);
                    checkoutItemPrice = itemView.findViewById(R.id.txtcheckoutitemprice);
                    checkoutItemAmount = itemView.findViewById(R.id.txtcheckoutitemamount);
                    checkoutItemAfterAmount = itemView.findViewById(R.id.txtcheckoutitemafteramount);
                    break;
            }


        }


        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btnadd:
                    Amount = Integer.parseInt(txtquanity.getText().toString());
                    Amount += 1;
                    txtquanity.setText(Amount.toString());
                    return;
                case R.id.btndes:
                    Amount = Integer.parseInt(txtquanity.getText().toString());
                    Amount -= 1;
                    txtquanity.setText(Amount.toString());
                    return;
                case R.id.btncart:
                    if (txtquanity.getText().toString().equals("0")) {
                        Toast.makeText(view.getContext(), "Please Select Amount.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    try {
                        itemList.put("fastfoodid", global.fastfoodModel.get_id());
                        itemList.put("fastfoodname", global.fastfoodModel.getFastfoodname());
                        itemList.put("fastfoodprice", global.fastfoodModel.getFastfoodprice());
                        itemList.put("fastfoodamount", txtquanity.getText().toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    global.ItemLists.add(itemList);
                    break;
            }
        }
    }
}
