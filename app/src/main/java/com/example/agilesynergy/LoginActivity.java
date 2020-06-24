package com.example.agilesynergy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agilesynergy.classes.LoginBLL;
import com.example.agilesynergy.classes.StrictModeClass;

import kotlin.jvm.internal.MagicApiIntrinsics;

public class LoginActivity extends AppCompatActivity {

    EditText etnumber, etpass;
    TextView etForget;
    Button loginbutton;
    TextView txtForget;

    private NotificationManagerCompat notificationManagerCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //for changing status bar icon colors
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setContentView(R.layout.activity_login);
        etnumber = findViewById(R.id.etnumber);
        etpass = findViewById(R.id.etPassword);
        etForget=findViewById(R.id.etforget);
        etForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, ForgotPassword.class));
            }
        });
        loginbutton = findViewById(R.id.btnuserlogin);

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(etnumber.getText())) {
                    etnumber.setError("Please Enter Your Number");
                    etnumber.requestFocus();
                    return;
                }
                else if (TextUtils.isEmpty(etpass.getText())) {
                    etpass.setError("Please Enter Your Password");
                    etpass.requestFocus();
                    return;
                }
                Userlogin();
            }
        });
    }

    private void Userlogin() {
        StrictModeClass.StrictMode();

        String usrnumber = etnumber.getText().toString();
        String usrpassword = etpass.getText().toString();
        LoginBLL loginBLL = new LoginBLL(usrnumber, usrpassword);


        if (loginBLL.checkUser(usrnumber, usrpassword)) {


            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            Toast.makeText(LoginActivity.this, "Redirecting... ", Toast.LENGTH_SHORT).show();
            startActivity(intent);
            Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Either Phone Number or password is incorrect", Toast.LENGTH_SHORT).show();
            etnumber.requestFocus();
            etnumber.setText("");
            etpass.setText("");
            etnumber.setVisibility(View.VISIBLE);
            etnumber.setBackgroundColor(Color.BLUE);
            etpass.setBackgroundColor(Color.BLUE);

        }

    }

    public void onLoginClick(View View) {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.stay);

    }
}
