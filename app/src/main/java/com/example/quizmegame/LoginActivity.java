package com.example.quizmegame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private EditText inputUsername, inputPassword;
    private Button login_btn;
    private TextView forgotPass, signUp;

    // create an object of firebase's realtime database
    private final DatabaseReference dbreference  = FirebaseDatabase.getInstance("https://quizmegame-df195-default-rtdb.europe-west1.firebasedatabase.app/").getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputUsername = findViewById(R.id.InputUsername);
        inputPassword = findViewById(R.id.InputPassword);
        Button login_btn = findViewById(R.id.btn_login);
        TextView forgotPass = findViewById(R.id.textViewForgotPassword);
        TextView signUp = findViewById(R.id.textViewSignUp);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkInputFields()){

                    dbreference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            //Get the values from the text fields
                            String username = inputUsername.getText().toString().trim();
                            String password = inputPassword.getText().toString().trim();

                            // check if username exists in firebase
                            if(snapshot.hasChild(username)){
                                //get user password from database
                                String getPassword = snapshot.child(username).child("password").getValue(String.class);

                                if (password.equals(getPassword)){
                                    Toast.makeText(LoginActivity.this, "Logged in successfully",Toast.LENGTH_SHORT).show();

                                    //open home activity
                                    login();
                                }
                                else
                                    Toast.makeText(LoginActivity.this, "Wrong Password",Toast.LENGTH_SHORT).show();
                            }
                            else
                                Toast.makeText(LoginActivity.this, "User not registered",Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(LoginActivity.this, "Failed to read value",Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });
    }

    private boolean checkInputFields(){

        if(inputUsername.getText().toString().isEmpty()){
            inputUsername.setError("Enter Email Address");
            return false;
        }

        if(inputPassword.getText().toString().isEmpty()){
            inputPassword.setError("Enter Password");
            return false;
        }

        return true;
    }

    private void login(){
        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
        finish();
    }
}