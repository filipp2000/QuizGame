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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword;

    FirebaseAuth auth;

    // create an object of firebase's realtime database
    private final DatabaseReference dbreference  = FirebaseDatabase.getInstance("https://quizmegame-df195-default-rtdb.europe-west1.firebasedatabase.app/").getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputEmail = findViewById(R.id.InputEmail);
        inputPassword = findViewById(R.id.InputPassword);
        Button login_btn = findViewById(R.id.btn_login);
        TextView forgotPass = findViewById(R.id.textViewForgotPassword);
        TextView signUp = findViewById(R.id.textViewSignUp);

        auth = FirebaseAuth.getInstance();


        if(auth.getCurrentUser() != null) {
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            finish();
        }

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //login();  //remove comments to test
                if(checkInputFields()) {

                    //Get the values from the text fields
                    String email = inputEmail.getText().toString().trim();
                    String password = inputPassword.getText().toString().trim();

                    auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(LoginActivity.this, "Logged in successfully",Toast.LENGTH_SHORT).show();
                                login();
                            } else
                                Toast.makeText(LoginActivity.this, task.getException().getLocalizedMessage(),Toast.LENGTH_SHORT).show();

                        }
                    });
                }

            }
        });
    }

    private boolean checkInputFields(){

        if(inputEmail.getText().toString().isEmpty()){
            inputEmail.setError("Enter Email Address");
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