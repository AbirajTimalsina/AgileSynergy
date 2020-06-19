package com.example.agilesynergy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agilesynergy.api.userapi;
import com.example.agilesynergy.global.global;
import com.example.agilesynergy.models.user;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Url;

public class ProfileActivity extends AppCompatActivity {
    private ImageView imguser;
    private TextView fullname, email, phoneno;
    user User;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        fullname = findViewById(R.id.fullname);
        email = findViewById(R.id.email);
        phoneno = findViewById(R.id.phoneno);
        User = new user();
        loaduser();

    }

    private void loaduser() {
        userapi userAPI = global.getInstance().create(userapi.class);
//
        Call<user> ListCall1 = userAPI.getUserDetails(global.token);
        ListCall1.enqueue(new Callback<user>() {
            @Override
            public void onResponse(Call<user> call, Response<user> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(ProfileActivity.this, "Error" + response.code(), Toast.LENGTH_SHORT).show();

                }
                User = response.body();
                if (response.body() != null) {

                    String imgPath = null;
                    imgPath =global.imagePath + response.body().getProfile_image();
                    Picasso.get().load(imgPath).into(imguser);


                }

                fullname.setText(response.body().getFullname());

                phoneno.setText(response.body().getPhonenumber());
                email.setText(response.body().getEmail());


            }

            @Override
            public void onFailure(Call<user> call, Throwable t) {
                Log.d("Error Message", "Error " + t.getLocalizedMessage());
                Toast.makeText(ProfileActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });


    }
}