package com.example.quiz_me;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class BonusGamesFragment extends Fragment {

    View myView;

    Button survival_btn;
    Button trueFalse_btn;
    Button missions_btn;
    Button pickAprize_btn;
    Button luckySpin_btn;
    Button dailyQstn_btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        myView = inflater.inflate(R.layout.fragment_bonus_games, container, false);

        survival_btn = myView.findViewById(R.id.btn_survival);
        survival_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SurvivalQuizActivity.class));
            }
        });
        return myView;
    }


}