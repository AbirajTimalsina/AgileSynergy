package com.example.agilesynergy.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agilesynergy.R;
import com.example.agilesynergy.api.userapi;
import com.example.agilesynergy.global.global;
import com.example.agilesynergy.models.feedbackModel;
import com.example.agilesynergy.models.user;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class favouriteAdapter extends RecyclerView.Adapter<favouriteAdapter.favouriteViewHolder> {


    Context mcontext;
    List<feedbackModel> feedbackModelList;



    public favouriteAdapter(Context mcontext, List<feedbackModel> feedbackModelList) {
        this.mcontext = mcontext;
        this.feedbackModelList = feedbackModelList;

    }

    @NonNull
    @Override
    public favouriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_favourite, parent, false);
        return new favouriteAdapter.favouriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final favouriteViewHolder holder, int position) {
        // final user user = new user("5ee3b65d3cbdee3dcc402c8b",null,null,null,null,null,null,null,null);

        final feedbackModel feedbackModel = feedbackModelList.get(position);
        holder.tvitemname.setText(feedbackModel.getItemname());
        holder.btnfav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Favourite Item!").
                        setMessage("Are you sure you want to delete this item?");
                builder.setPositiveButton("REMOVE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        userapi userapi = global.getInstance().create(userapi.class);
                        Call<user> dltusrfav = userapi.getUserDetails(global.token);
                        try{
                            Response<user> deletefavouriteresponse = dltusrfav.execute();
                            Call<Void> dltfav = userapi.deletefavouirtelist(global.token, deletefavouriteresponse.body().get_id(), feedbackModel.get_id());
                             dltfav.execute();
                            Toast.makeText(view.getContext(), "Successfully Removed ", Toast.LENGTH_SHORT).show();

                        }catch (Exception e){
                            e.printStackTrace();
                        }

//                        if (new feedbackClass(new feedbackModel(null,holder.tvitemname.getText().toString(), "no", null)).
//                                postFeedback()) {
//                            Toast.makeText(view.getContext(), "Sucessfully Removed ", Toast.LENGTH_SHORT).show();
//                        }

                    }
                });
                builder.setNegativeButton("CANCEL",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert11 = builder.create();
                alert11.show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return feedbackModelList.size();
    }

    public class favouriteViewHolder extends RecyclerView.ViewHolder {

        TextView tvitemname;
        ImageButton btnfav;

        public favouriteViewHolder(@NonNull View itemView) {
            super(itemView);
            tvitemname = itemView.findViewById(R.id.tvfavitemname);
            btnfav = itemView.findViewById(R.id.btnremovefav);

        }
    }

}
