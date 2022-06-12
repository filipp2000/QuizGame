package com.example.quizmegame;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class HomeFragment extends Fragment {

    View myView;
    Button singlePlayer_btn;
    Button multiplayer_btn;
    Button partyMode_btn;

    private String selectedMode;
    private String userName;

    FirebaseFirestore database;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_home, container, false);

        singlePlayer_btn = myView.findViewById(R.id.btn_login);
        multiplayer_btn = myView.findViewById(R.id.btn_multiplayer);
        partyMode_btn = myView.findViewById(R.id.btn_partyMode);

        final TextView currentUsername = myView.findViewById(R.id.currentUsername);
        final Button lives = myView.findViewById(R.id.lives);
        final Button coins = myView.findViewById(R.id.coins);
        final Button tickets = myView.findViewById(R.id.tickets);


        database = FirebaseFirestore.getInstance();
        database.collection("users").document(FirebaseAuth.getInstance().getUid()).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        User user = documentSnapshot.toObject(User.class);

                        lives.setText(String.valueOf(user.getLives()));
                        coins.setText(String.valueOf(user.getCoins()));
                        tickets.setText(String.valueOf(user.getTickets()));
                        userName = user.getUsername();
                        currentUsername.setText(userName);

                    }
                });

        singlePlayer_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), CategoriesActivity.class));
            }
        });

        multiplayer_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //selectedMode = "Multiplayer";

                Intent intent = new Intent(getActivity(), MultiplayerQuizActivity.class);
                //intent.putExtra("selectedMode", selectedMode);
                intent.putExtra("userName", userName);
                startActivity(intent);

            }
        });

        partyMode_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedMode = "Party Mode";
                Intent intent = new Intent(getActivity(), SinglePlayerQuizActivity.class);
                intent.putExtra("selectedMode", selectedMode);
                startActivity(intent);

                startActivity(new Intent(getActivity(), GameLobby.class));
            }
        });

        return myView;
    }
}