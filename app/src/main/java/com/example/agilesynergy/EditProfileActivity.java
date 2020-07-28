package com.example.agilesynergy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agilesynergy.api.userapi;
import com.example.agilesynergy.fragments.ProfileFragment;
import com.example.agilesynergy.global.global;
import com.example.agilesynergy.models.user;
import com.example.agilesynergy.models.userCRUD;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.test.platform.app.InstrumentationRegistry.getArguments;

public class EditProfileActivity extends AppCompatActivity {


    private EditText etfname,etaddress,etphone,etemail;
    private Spinner spgender;
    private TextView btnupdate;
    private ImageView imguser;
    String spin_val;
    String gender[] = { "Male", "Female", "Other"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        etfname =findViewById(R.id.etfname);
        etaddress =findViewById(R.id.etaddress);
        etphone =findViewById(R.id.etphone);
        etemail =findViewById(R.id.etemail);
        spgender =findViewById(R.id.spgender);
        imguser =findViewById(R.id.imguser);
        btnupdate =findViewById(R.id.btnupdate);


        etfname.setText(global.user.getFullname());
        etaddress.setText(global.user.getAddress());
        etphone.setText(global.user.getPhonenumber());
        etemail.setText(global.user.getEmail());

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profileupdate();
            }
        });



        ArrayAdapter<String> spin_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, gender);

        // setting adapteers to spinners
        spgender.setAdapter(spin_adapter);
    }

    private void profileupdate() {

        String fullname = etfname.getText().toString();
        String address = etaddress.getText().toString();
        String phonenumber = etphone.getText().toString();
        String email = etemail.getText().toString();
        String gender = spgender.toString();

        userCRUD UserCRUD = new userCRUD();
        UserCRUD.setFullname(fullname);
        UserCRUD.setAddress(address);
        UserCRUD.setPhonenumber(phonenumber);
        UserCRUD.setEmail(email);
        UserCRUD.setGender(spgender.getSelectedItem().toString());

        userapi Userapi = global.getInstance().create(userapi.class);
        Call<userCRUD> userCRUDCall = Userapi.updateuser(global.token,UserCRUD);



        userCRUDCall.enqueue(new Callback<userCRUD>() {
            @Override
            public void onResponse(Call<userCRUD> call, Response<userCRUD> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(EditProfileActivity.this, "Code" + response.code(), Toast.LENGTH_LONG).show();
                    Log.d("EditProfileActivity", "response other than 200" + response.code());
                    return;
                }
                Toast.makeText(EditProfileActivity.this, "Updated", Toast.LENGTH_LONG).show();
                Log.d("EditProfileActivity", "response of 200" + response.body().getAddress());
                Intent intent = new Intent(EditProfileActivity.this, MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<userCRUD> call, Throwable t) {
                Toast.makeText(EditProfileActivity.this, "Error"+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }
}