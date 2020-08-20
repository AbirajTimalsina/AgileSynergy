package com.example.agilesynergy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.agilesynergy.classes.StrictModeClass;
import com.example.agilesynergy.classes.forgotPasswordUpdate;

public class ForgotPassword extends AppCompatActivity implements View.OnClickListener {


    private Spinner spinnerForgotQuestion;
    private EditText EditTextForgotEmail, EditTextForgotAnswer, EditTextForgotPassword, EditTextForgotConfirmPassword;
    private Button ForgotButton;

    private String questions[] = {"Please Select a Question", "What is yours first pets name?", "What is your spouse name?",
            "Who is your favorite artist?", "What is your favorite holiday location?"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        spinnerForgotQuestion = findViewById(R.id.SpinnerForgotQuestion);
        EditTextForgotAnswer = findViewById(R.id.editTextForgotAnswer);

        EditTextForgotEmail = findViewById(R.id.EditTextForgotEmail);
        EditTextForgotPassword = findViewById(R.id.EditTextForgotPassword);
        EditTextForgotConfirmPassword = findViewById(R.id.EditTextForgotConfirmPassword);
        ForgotButton=findViewById(R.id.ButtonForgotButton);


        ArrayAdapter<String> arrayAdapterQuestions = new ArrayAdapter<>(this, android.R.layout.select_dialog_item, questions);
        spinnerForgotQuestion.setAdapter(arrayAdapterQuestions);

        ForgotButton.setOnClickListener(this);

    }

    public void PasswordChange() {
        StrictModeClass.StrictMode();
        if (EditTextForgotPassword.getText().toString().equals(EditTextForgotConfirmPassword.
                getText().toString())) {

            forgotPasswordUpdate forgotPasswordUpdate = new forgotPasswordUpdate(EditTextForgotEmail.getText().toString(),
                    EditTextForgotAnswer.getText().toString(), spinnerForgotQuestion.getSelectedItem().toString(),
                    EditTextForgotPassword.getText().toString());
            if (forgotPasswordUpdate.UpdateForgottenPassword()) {
                Toast.makeText(this, "Passwaord updated", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "Password doesn't match with each other", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ButtonForgotButton:
                PasswordChange();
                return;
        }
    }
}