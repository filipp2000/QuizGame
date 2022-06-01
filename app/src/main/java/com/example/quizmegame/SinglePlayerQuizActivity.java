package com.example.quizmegame;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class SinglePlayerQuizActivity extends AppCompatActivity {

    private TextView questsNum;
    private TextView question;
    private TextView option1, option2, option3, option4;

    private TextView selectedCategory;

    CountDownTimer countDownTimer;

    private final ArrayList<Question> questionList = new ArrayList<>();

    //Dialog Variables
    AlertDialog.Builder builderDialog;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player_quiz);

        final ImageView back_btn = findViewById(R.id.back_arrow);
        selectedCategory = findViewById(R.id.selectedCategory);

        questsNum = findViewById(R.id.questsNum);
        question = findViewById(R.id.question);

        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);


        final TextView timer = findViewById(R.id.timer);

        final String getSelectedCateg = getIntent().getStringExtra("selectedCategory");
        selectedCategory.setText(getSelectedCateg);

        setQuestionTimer(timer);
    }


    private int getCorrectAnswers(){    //number of correct answers from the quiz
        int correctAnswers = 0;

        for (int i=0;i<questionList.size();i++){

            final String getUserSelectedAnswer = questionList.get(i).getSelectedAnswer();
            final String getCorrectAnswer = questionList.get(i).getCorrectAnswer();

            if (getUserSelectedAnswer.equals(getCorrectAnswer)){
                correctAnswers++;
            }
        }

        return correctAnswers;
    }

    private int getIncorrectAnswers(){    //number of incorrect answers from the quiz
        int inCorrectAnswers = 0;

        for (int i=0;i<questionList.size();i++){

            final String getUserSelectedAnswer = questionList.get(i).getSelectedAnswer();
            final String getCorrectAnswer = questionList.get(i).getCorrectAnswer();

            if (!getUserSelectedAnswer.equals(getCorrectAnswer)){
                inCorrectAnswers++;
            }
        }

        return inCorrectAnswers;
    }


    private void setQuestionTimer(TextView timer) {
        long timer_value = TimeUnit.SECONDS.toMillis(20);
        countDownTimer = new CountDownTimer(timer_value,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String duration = String.format(Locale.ENGLISH, "%02d : %02d"
                        ,TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)
                        ,TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)-
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));

                timer.setText(duration);
            }

            @Override
            public void onFinish() {
                showAlertDialog(R.layout.time_out_dialog);
            }
        }.start();
    }

    private void showAlertDialog(int myLayout) {
        builderDialog = new AlertDialog.Builder(this);
        View layoutView = getLayoutInflater().inflate(myLayout,null);
        builderDialog.setView(layoutView);
        alertDialog = builderDialog.create();
        alertDialog.show();

        Button tryAgain = layoutView.findViewById(R.id.tryAgain);
        tryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SinglePlayerQuizActivity.this,QuizResults.class);
                intent.putExtra("correct",getCorrectAnswers());
                intent.putExtra("incorrect",getIncorrectAnswers());

                startActivity(intent);
                finish();
            }
        });
    }


}