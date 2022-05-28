package com.example.quiz_me;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private EditText email, password;
    private Button login_btn;
    private TextView forgotPass, signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText email = findViewById(R.id.InputEmail);
        final EditText password = findViewById(R.id.InputPassword);
        final Button login_btn = findViewById(R.id.btn_login);
        final TextView forgotPass = findViewById(R.id.textViewForgotPassword);
        final TextView signUp = findViewById(R.id.textViewSignUp);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(verifyUser()){
                    login();
                }
            }
        });
    }

    private boolean verifyUser(){

        if(email.getText().toString().isEmpty()){
            email.setError("Enter Email Address");
            return false;
        }

        if(password.getText().toString().isEmpty()){
            password.setError("Enter Password");
            return false;
        }

        return true;
    }

    private void login(){
        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
    }
}