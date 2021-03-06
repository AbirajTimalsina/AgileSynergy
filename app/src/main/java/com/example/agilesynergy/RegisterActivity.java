package com.example.agilesynergy;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.agilesynergy.api.userapi;
import com.example.agilesynergy.classes.StrictModeClass;
import com.example.agilesynergy.classes.userRegister;
import com.example.agilesynergy.global.global;
import com.example.agilesynergy.models.qa;
import com.example.agilesynergy.models.user;




import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    CircularProgressButton cirRegisterButton;
    private EditText editTextName, editTextEmail, editTextPhonenumber, editTextPassword, editTextAnswer;
    private Spinner spinnerQuestions;

    //Secret Questions
    private String questions[] = {"Please Select a Question","What is yours first pets name?", "What is your spouse name?",
            "Who is your favorite artist?", "What is your favorite holiday location?"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        changeStatusBarColor();

        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPhonenumber = findViewById(R.id.editTextphonenumber);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextAnswer = findViewById(R.id.editTextAnswer);

        spinnerQuestions = findViewById(R.id.SpinnerQuestion);

        ArrayAdapter<String> arrayAdapterQuestions = new ArrayAdapter<>(this, android.R.layout.select_dialog_item, questions);
        spinnerQuestions.setAdapter(arrayAdapterQuestions);

        cirRegisterButton = findViewById(R.id.cirRegisterButton);
        cirRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();

            }
        });
    }

    private boolean isValidEmailId(String email) {

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }




    private void register() {
        validation();
        qa qa= new qa(spinnerQuestions.getSelectedItem().toString(),editTextAnswer.getText().toString());
        user user= new user(null, editTextName.getText().toString(),editTextPhonenumber.getText().toString(),
                editTextEmail.getText().toString(),editTextPassword.getText().toString(),null,null,null,qa,null, null);

        userRegister userRegister= new userRegister(user);
        StrictModeClass.StrictMode();
        if(userRegister.userRegistration()){
            Intent intent= new Intent(this, MainActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Successfully Singed UP", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Something went wrong.", Toast.LENGTH_SHORT).show();
        }
    }

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
            window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
        }
    }

    public void onLoginClick(View view) {
        startActivity(new Intent(this, LoginActivity.class));
        overridePendingTransition(R.anim.slide_in_left, android.R.anim.slide_out_right);

    }

    public void validation(){
        if (TextUtils.isEmpty(editTextName.getText())) {
            editTextName.setError("Please enter Full name");
            editTextName.requestFocus();
            return;
        } else if (TextUtils.isEmpty(editTextPhonenumber.getText())) {
            editTextPhonenumber.setError("Please enter Contact Number");
            editTextPhonenumber.requestFocus();
            return;
        } else if (TextUtils.isEmpty(editTextPassword.getText())) {
            editTextPassword.setError("Please enter valid Password");
            editTextPassword.requestFocus();

            return;
        } else if (isValidEmailId(editTextEmail.getText().toString().trim())) {
            //doing nothing
        }else if(spinnerQuestions.getSelectedItem().toString().equals("Please Select a Question")){
            spinnerQuestions.requestFocus();
            return;
        }else {
            editTextEmail.setError("Enter Valid Address ");
            editTextEmail.requestFocus();
            return;
        }
    }
}
