package com.example.agilesynergy.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.content.CursorLoader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agilesynergy.EditProfileActivity;
import com.example.agilesynergy.LoginActivity;
import com.example.agilesynergy.R;
import com.example.agilesynergy.adapter.purchasehistoryAdapter;
import com.example.agilesynergy.api.userapi;
import com.example.agilesynergy.classes.StrictModeClass;

import com.example.agilesynergy.global.global;
import com.example.agilesynergy.models.purchasehistory;
import com.example.agilesynergy.models.user;
import com.example.agilesynergy.response.ResponseImage;
import com.facebook.internal.ImageResponse;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;
import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static com.example.agilesynergy.global.global.imagePath;

public class ProfileFragment extends Fragment {

    ImageView imguser;
    ImageButton editprofile,editphoto;
    private TextView tvfullname, tvemail, tvphoneno, tvaddress,tvgender;
    Button btnlogout;

    RecyclerView phrecyclehsitory;


    List<purchasehistory> purchasehistoryList;
    purchasehistoryAdapter purchasehistoryAdapter;
    private String imageName="";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        imguser = view.findViewById(R.id.imguser);
        tvfullname = view.findViewById(R.id.tvfullname);
        tvemail = view.findViewById(R.id.tvemail);
        tvphoneno = view.findViewById(R.id.tvphoneno);
        tvaddress = view.findViewById(R.id.tvaddress);
        tvgender = view.findViewById(R.id.tvgender);
        phrecyclehsitory = view.findViewById(R.id.phrecyclehsitory);
        btnlogout = view.findViewById(R.id.btnlogout);
        editphoto = view.findViewById(R.id.editphoto);
        editprofile = view.findViewById(R.id.editprofile);


                editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), EditProfileActivity.class);
                startActivity(intent);
            }
        });

        editphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                browseimage();
                saveImageOnly();
            }
        });
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Log Out!").
                        setMessage("You sure, that you want to logout?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        global.token = "Bearer ";
                        Intent logout = new Intent(getContext(), LoginActivity.class)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(logout);
                        getActivity().finish();
                    }
                });
                builder.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert11 = builder.create();
                alert11.show();
            }
        });
        loadcurrentuser();

        return view;
    }


    private void loadcurrentuser() {
        userapi Userapi = global.getInstance().create(userapi.class);
        Call<user> userCall = Userapi.getUserDetails(global.token);
        try {

            Response<user> profileresponse = userCall.execute();


            if (profileresponse.isSuccessful()) {
                String imagepath = null;
                imagepath = imagePath + profileresponse.body().getProfile_image();
                Picasso.get().load(imagepath).into(imguser);
                global.user = profileresponse.body();
            }
            tvfullname.setText(profileresponse.body().getFullname());
            tvemail.setText(profileresponse.body().getEmail());
            tvphoneno.setText(profileresponse.body().getPhonenumber());
            tvaddress.setText(profileresponse.body().getAddress());
            tvgender.setText(profileresponse.body().getGender());

            // Purchase History
            purchasehistoryList = profileresponse.body().getPurchase();
            purchasehistoryAdapter = new purchasehistoryAdapter(getContext(), purchasehistoryList);
            phrecyclehsitory.setAdapter(purchasehistoryAdapter);
            phrecyclehsitory.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void browseimage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,0);
    }

    private void saveImageOnly() {
        File file = new File(imagePath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"),file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("image",
                file.getName(),requestBody);

        userapi Userapi = global.getInstance().create(userapi.class);
        Call<ResponseImage> responseBodyCall = Userapi.uploadpic(body);

        StrictModeClass.StrictMode();
        //Synchronomus method

        try{
            Response<ResponseImage> imageResponseResponse = responseBodyCall.execute();
            imageName = imageResponseResponse.body().getFilename();
            Toast.makeText(getContext(), "Image Inserted", Toast.LENGTH_LONG).show();
        }catch (IOException e)
        {
            Toast.makeText(getContext(), "Error"+e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(data == null)
            {
                Toast.makeText(getContext(), "Please select an image", Toast.LENGTH_LONG).show();
            }
        }

        Uri uri = data.getData();
        imguser.setImageURI(uri);
        imagePath = getRealPathFromUri(uri);
    }

    private String getRealPathFromUri(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(getApplicationContext(),uri,projection, null
                ,null,null);
        Cursor cursor = loader.loadInBackground();
        int colIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(colIndex);
        cursor.close();
        return result;
    }
}