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
import com.google.firebase.firestore.FirebaseFirestore;

public class RegisterActivity extends AppCompatActivity {

    // create an object of firebase's realtime database
    //private final DatabaseReference dbreference  = FirebaseDatabase.getInstance("https://quizmegame-df195-default-rtdb.europe-west1.firebasedatabase.app/").getReference();
    FirebaseAuth auth;
    FirebaseFirestore database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        final EditText regUserame = findViewById(R.id.InputUsername);
        final EditText regPassword = findViewById(R.id.InputPassword);
        final EditText regEmail = findViewById(R.id.InputEmail);
        final Button register_btn = findViewById(R.id.btn_register);
        final TextView haveAccount = findViewById(R.id.alreadyHaveAnAccount);

        auth = FirebaseAuth.getInstance();
        database = FirebaseFirestore.getInstance();

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
                    auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                User user = new User(username,password,email);

                                String uid = task.getResult().getUser().getUid();

                                database.collection("users").document(uid).set(user)
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()){
                                                            Toast.makeText(RegisterActivity.this, "Registered successfully",Toast.LENGTH_SHORT).show();
                                                            startActivity(new Intent(RegisterActivity.this, HomeActivity.class));
                                                            finish();
                                                        }
                                                        else{
                                                            Toast.makeText(RegisterActivity.this, task.getException().getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                });

                            }else {
                                Toast.makeText(RegisterActivity.this, task.getException().getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }

            }
        });
    }
}