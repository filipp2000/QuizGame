package com.example.quizmegame;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class HomeFragment extends Fragment {

    View myView;
    Button singlePlayer_btn;
    Button multiplayer_btn;
    Button partyMode_btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_home, container, false);

        singlePlayer_btn = myView.findViewById(R.id.btn_login);
        multiplayer_btn = myView.findViewById(R.id.btn_multiplayer);
        partyMode_btn = myView.findViewById(R.id.btn_partyMode);


        singlePlayer_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), CategoriesActivity.class));
            }
        });

        multiplayer_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), MultiplayerQuizActivity.class));
            }
        });

        partyMode_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), PartyModeQuizActivity.class));
            }
        });

        return myView;
    }
}