package com.example.quizmegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

public class QuizResults extends AppCompatActivity {

    private TextView correctAnswers;
    private TextView coins_earned;
    private TextView exp_earned;
    FirebaseFirestore database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_results);

        Button playAgain_btn = findViewById(R.id.playAgain_btn);
        Button exit_btn = findViewById(R.id.exit_btn);
        correctAnswers = findViewById(R.id.correct_answ_num);
        coins_earned = findViewById(R.id.coins_gained);
        exp_earned = findViewById(R.id.exp_gained);


        final int getcorrectAnswersNum = getIntent().getIntExtra("correct",0);

        calcResults(getcorrectAnswersNum);

        playAgain_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QuizResults.this, CategoriesActivity.class));
                finish();
            }
        });

        exit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QuizResults.this, HomeActivity.class));
                finish();
            }
        });
    }

    private void calcResults(int correctAnswersNum){
        int coins = correctAnswersNum * 50;
        int tickets = correctAnswersNum * 30;
        int points = correctAnswersNum * 80;
        int lives = -1;

        database = FirebaseFirestore.getInstance();

        database.collection("users")
                .document(FirebaseAuth.getInstance().getUid())
                .update("coins", FieldValue.increment(coins));

        database.collection("users")
                .document(FirebaseAuth.getInstance().getUid())
                .update("tickets", FieldValue.increment(tickets));

        database.collection("users")
                .document(FirebaseAuth.getInstance().getUid())
                .update("experience", FieldValue.increment(points));

        database.collection("users")
                .document(FirebaseAuth.getInstance().getUid())
                .update("lives", FieldValue.increment(lives));



        correctAnswers.setText((correctAnswersNum + "/" + "10"));
        coins_earned.setText(String.valueOf(coins));
        exp_earned.setText(String.valueOf(points));
    }
}
