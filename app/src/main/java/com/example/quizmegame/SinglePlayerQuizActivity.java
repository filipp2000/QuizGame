package com.example.quizmegame;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class SinglePlayerQuizActivity extends AppCompatActivity {

    private TextView question;
    private TextView option1, option2, option3, option4;

    private TextView questsNum;
    private int currentQuestnum = 0;

    private TextView selectedCategory;

    private TextView timer;
    CountDownTimer countDownTimer;

    private ArrayList<Question> questionList;

    private String selectedAnswer = "";

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



        final String getSelectedCateg = getIntent().getStringExtra("selectedCategory");

        selectedCategory.setText(getSelectedCateg);

        questionList = QuestionsDB.getQuestions(getSelectedCateg);

        timer = findViewById(R.id.timer);
        setQuestionTimer(timer);

        questsNum.setText((currentQuestnum +1) + "/" + questionList.size());

        question.setText(questionList.get(0).getQuestion());
        option1.setText(questionList.get(0).getOption1());
        option2.setText(questionList.get(0).getOption2());
        option3.setText(questionList.get(0).getOption3());
        option4.setText(questionList.get(0).getOption4());


        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedAnswer.isEmpty()){

                    selectedAnswer = option1.getText().toString();
                    option1.setBackgroundResource(R.drawable.wrong_answer);
                    option1.setTextColor(Color.WHITE);

                    checkAnswer();
                    questionList.get(currentQuestnum).setSelectedAnswer(selectedAnswer);

                    setNextQuestion();
                }
            }
        });

        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedAnswer.isEmpty()){

                    selectedAnswer = option2.getText().toString();
                    option2.setBackgroundResource(R.drawable.wrong_answer);
                    option2.setTextColor(Color.WHITE);

                    checkAnswer();
                    questionList.get(currentQuestnum).setSelectedAnswer(selectedAnswer);
                    //countDownTimer.cancel();
                    setNextQuestion();
                }
            }
        });

        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedAnswer.isEmpty()){

                    selectedAnswer = option3.getText().toString();
                    option3.setBackgroundResource(R.drawable.wrong_answer);
                    option3.setTextColor(Color.WHITE);

                    checkAnswer();
                    questionList.get(currentQuestnum).setSelectedAnswer(selectedAnswer);

                    setNextQuestion();
                }
            }
        });

        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedAnswer.isEmpty()){

                    selectedAnswer = option4.getText().toString();
                    option4.setBackgroundResource(R.drawable.wrong_answer);
                    option4.setTextColor(Color.WHITE);

                    checkAnswer();
                    questionList.get(currentQuestnum).setSelectedAnswer(selectedAnswer);

                    setNextQuestion();
                }
            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDownTimer.cancel();
                startActivity(new Intent(SinglePlayerQuizActivity.this,CategoriesActivity.class));
                finish();
            }
        });


    }


    private void setNextQuestion(){
        currentQuestnum++;
        countDownTimer.cancel();

        if(currentQuestnum < questionList.size()){

            setQuestionTimer(timer);
            questsNum.setText((currentQuestnum +1) + "/" + questionList.size());


            option1.setBackgroundColor(Color.WHITE);
            option1.setTextColor(Color.BLACK);

            option2.setBackgroundColor(Color.WHITE);
            option2.setTextColor(Color.BLACK);

            option3.setBackgroundColor(Color.WHITE);
            option3.setTextColor(Color.BLACK);

            option4.setBackgroundColor(Color.WHITE);
            option4.setTextColor(Color.BLACK);

            question.setText(questionList.get(currentQuestnum).getQuestion());
            option1.setText(questionList.get(currentQuestnum).getOption1());
            option2.setText(questionList.get(currentQuestnum).getOption2());
            option3.setText(questionList.get(currentQuestnum).getOption3());
            option4.setText(questionList.get(currentQuestnum).getOption4());
        }
        else{
            Intent intent = new Intent(SinglePlayerQuizActivity.this,HomeActivity.class);  //QuizResults
            intent.putExtra("correct",getCorrectAnswers());
            intent.putExtra("incorrect",getIncorrectAnswers());

            startActivity(intent);
            finish();
        }

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
                Intent intent = new Intent(SinglePlayerQuizActivity.this,HomeActivity.class);
                intent.putExtra("correct",getCorrectAnswers());
                intent.putExtra("incorrect",getIncorrectAnswers());

                startActivity(intent);
                finish();
            }
        });
    }

    private void checkAnswer(){
        final String getCorrectAnswer = questionList.get(currentQuestnum).getCorrectAnswer();

        if(option1.getText().toString().equals(getCorrectAnswer)){
            option1.setBackgroundResource(R.drawable.correct_answer);
            option1.setTextColor(Color.WHITE);
        }

        else if(option2.getText().toString().equals(getCorrectAnswer)){
            option2.setBackgroundResource(R.drawable.correct_answer);
            option2.setTextColor(Color.WHITE);
        }

        else if(option3.getText().toString().equals(getCorrectAnswer)){
            option3.setBackgroundResource(R.drawable.correct_answer);
            option3.setTextColor(Color.WHITE);
        }
        else if(option4.getText().toString().equals(getCorrectAnswer)){
            option4.setBackgroundResource(R.drawable.correct_answer);
            option4.setTextColor(Color.WHITE);
        }
    }

}