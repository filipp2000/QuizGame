package com.example.quizmegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class GameLobby extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_join_screen);

        TextView selectedMode = findViewById(R.id.selectedMode);
        final ImageView back_btn = findViewById(R.id.back_arrow);

        // get selected mode from HomeFragment
        final String getSelectedMode = getIntent().getStringExtra("selectedMode");
        selectedMode.setText(getSelectedMode);


        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GameLobby.this,HomeActivity.class));
                finish();
            }
        });
    }
}