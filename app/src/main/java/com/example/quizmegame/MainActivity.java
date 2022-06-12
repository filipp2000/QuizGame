package com.example.quizmegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                //uploadQuestions();  // remove comments to update questions in db
                finish(); //closes the splash screen
            }
        },3000);  //3sec
    }

    public void uploadQuestions() {
        QuestionsDB qstdb = new QuestionsDB();
        qstdb.uploadQuestionsToDB();
        Toast.makeText(MainActivity.this, "Question list uploaded successfully",Toast.LENGTH_LONG).show();
    }
}