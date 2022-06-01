package com.example.quizmegame;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

    //Handler
    private Handler mHandler = new Handler();

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
                selectedAnswer = option1.getText().toString();
                questionList.get(currentQuestnum).setSelectedAnswer(selectedAnswer);

                boolean isCorrect = checkAnswer(option1);
                countDownTimer.cancel();

                if(isCorrect){
                    setNextQuestion();
                    //Toast.makeText(SinglePlayerQuizActivity.this,"Correct",Toast.LENGTH_SHORT).show();
                }
                else{
                    countDownTimer.cancel();
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            dislayResults();
                            //Toast.makeText(SinglePlayerQuizActivity.this, "Wrong Answer!",Toast.LENGTH_SHORT).show();
                            //finish();   //close the activity
                        }
                    },2000);  //wait 2sec
                }

            }
        });

        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedAnswer = option2.getText().toString();
                questionList.get(currentQuestnum).setSelectedAnswer(selectedAnswer);

                boolean isCorrect = checkAnswer(option2);
                countDownTimer.cancel();

                if(isCorrect){
                    setNextQuestion();
                }
                else{
                    countDownTimer.cancel();
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            dislayResults();
                        }
                    },2000);  //wait 2sec
                }
            }
        });

        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedAnswer = option3.getText().toString();
                questionList.get(currentQuestnum).setSelectedAnswer(selectedAnswer);

                boolean isCorrect = checkAnswer(option3);
                countDownTimer.cancel();

                if(isCorrect){
                    setNextQuestion();
                }
                else{
                    countDownTimer.cancel();
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            dislayResults();
                        }
                    },2000);  //wait 2sec
                }
            }
        });

        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedAnswer = option4.getText().toString();
                questionList.get(currentQuestnum).setSelectedAnswer(selectedAnswer);

                boolean isCorrect = checkAnswer(option4);
                countDownTimer.cancel();

                if(isCorrect){
                    setNextQuestion();
                }
                else{
                    countDownTimer.cancel();
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            dislayResults();
                        }
                    },2000);  //wait 2sec
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

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(currentQuestnum < questionList.size()){

                    setQuestionTimer(timer);
                    questsNum.setText((currentQuestnum +1) + "/" + questionList.size());

                    //set option background to unselected
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
                    dislayResults();
                }
            }
        },1000);  //wait 1sec



    }

    private void dislayResults(){
        Intent intent = new Intent(SinglePlayerQuizActivity.this,QuizResults.class);  //QuizResults
        intent.putExtra("correct",getCorrectAnswers());
        //intent.putExtra("incorrect",getIncorrectAnswers());

        startActivity(intent);
        finish();
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
                dislayResults();
            }
        });
    }

    public boolean checkAnswer(TextView textView){
        String selected = textView.getText().toString();
        final String getCorrectAnswer = questionList.get(currentQuestnum).getCorrectAnswer();

        boolean isCorrect = false;

        if (selected.equals(getCorrectAnswer)){   //correct answer
            textView.setBackgroundResource(R.drawable.correct_answer);
            isCorrect = true;

        }

        else {   //incorrect answer
            showAnswer();
            textView.setBackgroundResource(R.drawable.wrong_answer);
        }

        return isCorrect;
    }

    private void showAnswer(){
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