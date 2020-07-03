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
import com.example.agilesynergy.models.regularfoodModel;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class regularfoodAdapter extends RecyclerView.Adapter<regularfoodAdapter.regularViewHolder> {

    Context mcontext;
    List<regularfoodModel> regularfoodModelList;
    JSONObject regularfoodObject = new JSONObject();

    public regularfoodAdapter(Context mcontext, List<regularfoodModel> regularfoodModelList) {
        this.mcontext = mcontext;
        this.regularfoodModelList = regularfoodModelList;
    }

    @NonNull
    @Override
    public regularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_regularfood, parent, false);
        return new regularViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final regularViewHolder holder, int position) {
        final regularfoodModel regularfoodModel = regularfoodModelList.get(position);

        String imgpath = global.imagePath + regularfoodModel.getRegularfoodpicture();
        Log.e("Image path is :", "Image path is" + imgpath);

        Picasso.get().load(imgpath).into(holder.regularfoodimage);

        holder.regularfoodname.setText(regularfoodModel.getRegularfoodname());
        holder.regularfoodprice.setText(regularfoodModel.getRegularfoodprice());

        holder.btncart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    regularfoodObject.put("itemname", regularfoodModel.getRegularfoodname());
                    regularfoodObject.put("itemprice", regularfoodModel.getRegularfoodprice());
                    regularfoodObject.put("itemamount", holder.txtquanity.getText());
                    global.ItemLists.add(regularfoodObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return regularfoodModelList.size();
    }

    public class regularViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView regularfoodimage;
        TextView regularfoodname, regularfoodprice, txtquanity;
        private Button btnadd, btnminus, btncart;
        private Integer Amount;

        public regularViewHolder(@NonNull View itemView) {
            super(itemView);
            regularfoodimage = itemView.findViewById(R.id.regularfoodimage);
            regularfoodname = itemView.findViewById(R.id.regularfoodname);
            regularfoodprice = itemView.findViewById(R.id.regularfoodprice);
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
