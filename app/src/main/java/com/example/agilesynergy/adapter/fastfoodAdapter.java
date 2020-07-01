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
    JSONObject fastfoodObject = new JSONObject();


    public fastfoodAdapter(Context mcontext, List<fastfoodModel> fastfoodModelList) {
        this.mcontext = mcontext;
        this.fastfoodModelList = fastfoodModelList;
    }


    @NonNull
    @Override
    public fastfoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_fastfood, parent, false);


        return new fastfoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull fastfoodViewHolder holder, int position) {

        final fastfoodModel fastfoodModel123 = fastfoodModelList.get(position);

        Random rnd = new Random();
        int currentColor = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        holder.imageView.setColorFilter(currentColor);
        String imgpath = global.imagePath + fastfoodModel123.getFastfoodpicture();
        Log.e("Image path is :", "Image path is" + imgpath);
        Picasso.get().load(imgpath).into(holder.fastfoodpicture);
        holder.fastfoodname.setText(fastfoodModel123.getFastfoodname());
        holder.fastfoodprice.setText(fastfoodModel123.getFastfoodprice());

        holder.btncart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    fastfoodObject.put("itemname",fastfoodModel123.getFastfoodname());
                    fastfoodObject.put("itemprice",fastfoodModel123.getFastfoodprice());
                    fastfoodObject.put("itemamount","2");
                    global.ItemLists.add(fastfoodObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return fastfoodModelList.size();
    }

    public class fastfoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView fastfoodpicture, imageView;
        TextView fastfoodname, fastfoodprice, txtquanity;
        private Button btnadd, btnminus, btncart;
        private Integer Amount;

        public fastfoodViewHolder(@NonNull final View itemView) {
            super(itemView);
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

            }
        }
    }
}
