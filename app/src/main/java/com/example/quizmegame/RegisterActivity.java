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

public class RegisterActivity extends AppCompatActivity {

    // create an object of firebase's realtime database
    private final DatabaseReference dbreference  = FirebaseDatabase.getInstance("https://quizmegame-df195-default-rtdb.europe-west1.firebasedatabase.app/").getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        final EditText regUserame = findViewById(R.id.InputUsername);
        final EditText regPassword = findViewById(R.id.InputPassword);
        final EditText regEmail = findViewById(R.id.InputEmail);
        final Button register_btn = findViewById(R.id.btn_register);
        final TextView haveAccount = findViewById(R.id.alreadyHaveAnAccount);


        haveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }
        });

        // Save data in firebase on register button click
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Get the values from the text fields
                String username = regUserame.getText().toString();
                String password = regPassword.getText().toString().trim();
                String email = regEmail.getText().toString();

                //check if user fill all the fields
                if(username.isEmpty() || password.isEmpty() || email.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Fill all fields",Toast.LENGTH_SHORT).show();
                }
                else{

                    dbreference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            //check if username is not registered before
                            if(snapshot.hasChild(username)){
                                Toast.makeText(RegisterActivity.this, username + " is already registered",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                User user = new User(username, password, email);

                                // sending data to database using username as unique identity of every user
                                dbreference.child("users").child(username).setValue(user);
                                Toast.makeText(RegisterActivity.this, username + " registered successfully",Toast.LENGTH_SHORT).show();
                                finish();   //close the activity
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }

            }
        });
    }
}