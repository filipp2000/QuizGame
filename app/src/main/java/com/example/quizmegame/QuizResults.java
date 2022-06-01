package com.example.quizmegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class QuizResults extends AppCompatActivity {

    private Button playAgain_btn;
    private Button exit_btn;
    private TextView correctAnswers;
    private TextView coins_earned;
    private TextView exp_earned;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_results);

        playAgain_btn = findViewById(R.id.playAgain_btn);
        exit_btn = findViewById(R.id.exit_btn);
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

        if(correctAnswersNum == 0){
            correctAnswers.setText((0 + "/" + "10"));
        }
        else{
            correctAnswers.setText((correctAnswersNum + "/" + "10"));
            //coins_earned.setText(correctAnswersNum);
            //coins_earned.setText(correctAnswersNum);
        }

    }
}