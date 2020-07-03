package com.example.agilesynergy.adapter;

import android.content.Context;
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
import com.example.agilesynergy.global.global;
import com.example.agilesynergy.models.popularfoodModel;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class popularfoodAdapter extends RecyclerView.Adapter<popularfoodAdapter.popularfoodviewHolder> {

    Context mcontext;
    List<popularfoodModel> popularfoodModelList;
    private Integer Amount;
    JSONObject popularfoodObject = new JSONObject();

    public popularfoodAdapter(Context mcontext, List<popularfoodModel> popularfoodModelList) {
        this.mcontext = mcontext;
        this.popularfoodModelList = popularfoodModelList;
    }


    @NonNull
    @Override
    public popularfoodviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_popularfood, parent, false);
        return new popularfoodviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final popularfoodviewHolder holder, final int position) {

        final popularfoodModel popularfoodModel = popularfoodModelList.get(position);
        String imgpath = global.imagePath + popularfoodModel.getPopularfoodpicture();
        Log.e("Image path is :", "Image path is" + imgpath);
        Picasso.get().load(imgpath).into(holder.popularfoodimage);
        holder.popularfoodname.setText(popularfoodModel.getPopularfoodname());
        holder.popularfoodprice.setText(popularfoodModel.getPopularfoodprice());
        holder.btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Amount = Integer.parseInt(holder.txtquanity.getText().toString());
                Amount += 1;
                holder.txtquanity.setText(Amount.toString());
            }
        });

        holder.btnminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Amount = Integer.parseInt(holder.txtquanity.getText().toString());
                Amount -= 1;
                holder.txtquanity.setText(Amount.toString());
            }
        });

        holder.btncart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.txtquanity.getText().toString().equals("0")) {
                    Toast.makeText(mcontext, "Please Select Amount.", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    popularfoodObject.put("itemname", popularfoodModel.getPopularfoodname());
                    popularfoodObject.put("itemprice", popularfoodModel.getPopularfoodprice());
                    popularfoodObject.put("itemamount", holder.txtquanity.getText());
                    global.ItemLists.add(popularfoodObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return popularfoodModelList.size();
    }

    public class popularfoodviewHolder extends RecyclerView.ViewHolder {

        ImageView popularfoodimage;
        TextView popularfoodname, popularfoodprice, txtquanity;
        private Button btnadd, btnminus, btncart;

        public popularfoodviewHolder(@NonNull View itemView) {
            super(itemView);
            popularfoodimage = itemView.findViewById(R.id.popularfoodimage);
            popularfoodname = itemView.findViewById(R.id.popufoodname);
            popularfoodprice = itemView.findViewById(R.id.popularfoodprice);
            btnadd = itemView.findViewById(R.id.btnadd);
            btnminus = itemView.findViewById(R.id.btndes);
            txtquanity = itemView.findViewById(R.id.txtquanity);
            btncart = itemView.findViewById(R.id.btncart);
        }
    }
}
