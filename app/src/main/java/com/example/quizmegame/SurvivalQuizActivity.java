package com.example.quizmegame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class SurvivalQuizActivity extends AppCompatActivity {

    private TextView timer;
    CountDownTimer countDownTimer;

    private TextView roundNum;
    private TextView question;
    private TextView option1, option2, option3, option4;

    private int currentRoundNum = 0;

    private ArrayList<Question> questionList = new ArrayList<>();
    private String selectedAnswer = "";

    // players unique id
    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
    private final String userId = currentUser.getUid();
    //private String userName;

    private int lives;
    FirebaseFirestore database;


    //opponent unique id
    private String opponentId = "";

    // true when opponents found
    private boolean opponentsFound = false;

    // values are matching: finds a room to join OR waiting: when a user creates a room and he is waiting for others to join
    private String status = "matching";

    // room id in which player has joined to play
    private String roomId = "";

    ValueEventListener roundsEventListener, wonEventListener;

    DatabaseReference dbreference  = FirebaseDatabase.getInstance("https://quizmegame-df195-default-rtdb.europe-west1.firebasedatabase.app/").getReference();


    //Dialog Variables
    AlertDialog.Builder builderDialog;
    AlertDialog alertDialog;

    //Handler
    private Handler mHandler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survival_quiz);

        //get player name
        final String userName = getIntent().getStringExtra("userName");

        TextView currentUserName = findViewById(R.id.currentUsername);
        TextView opponentName = findViewById(R.id.opponentName);

        final ImageView back_btn = findViewById(R.id.back_arrow);
        timer = findViewById(R.id.timer);


        roundNum = findViewById(R.id.roundNum);
        question = findViewById(R.id.question);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);

        //show dialog while waiting for opponents
        ProgressDialog progressDialog = new ProgressDialog(SurvivalQuizActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Waiting for opponents...");
        progressDialog.show();


        dbreference.child("survival_rooms").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                // check if opponent found
                if(!opponentsFound){

                    // checking if there are others in realtime db
                    if (snapshot.hasChildren()){

                        // checking room if users are waiting for opponents
                        for (DataSnapshot rooms : snapshot.getChildren()){

                            // getting room unique id
                            String getRoomId = rooms.getKey();

                            // 2 players are required to play
                            // if players < 2 it means player is waiting for opponent
                            // else if players =2 means the room is completed
                            int getPlayersCount = (int) rooms.getChildrenCount();

                            // waiting in room for others to join
                            if(status.equals("waiting")){

                                // if players=2 means other player joined the match
                                if (getPlayersCount == 2){

                                    // true when player found in rooms
                                    boolean playerFound = false;

                                    // getting players in room
                                    for (DataSnapshot players : rooms.getChildren()){

                                        String getPlayerId = players.getKey();

                                        // check if player id match with user who created room(current user). if match get opponents details
                                        if(getPlayerId.equals(userId)){
                                            playerFound = true;

                                        }
                                        else if (playerFound){

                                            String getOpponentName = players.child("player_name").getValue(String.class);
                                            opponentId = players.getKey();

                                            // set players name to TextView
                                            currentUserName.setText(userName);
                                            opponentName.setText(getOpponentName);

                                            // set room id
                                            roomId = getRoomId;
                                            opponentsFound = true;

                                            // add round and won listener to db
                                            //dbreference.child("rounds").child(roomId).addValueEventListener(roundsEventListener);
                                            //dbreference.child("won").child(roomId).addValueEventListener(wonEventListener);

                                            // dismiss progress dialog
                                            if (progressDialog.isShowing()){
                                                progressDialog.dismiss();
                                            }

                                            fetchQuestionsFromDB();

                                            // once the room is completed remove from db reference
                                            dbreference.child("survival_rooms").removeEventListener(this);

                                        }

                                    }
                                }
                            }
                            // user has not created a room because there are available rooms to join
                            else {

                                // checking if room has 1 player (<10) then join this room to play
                                if (getPlayersCount == 1){

                                    // add player to the room
                                    rooms.child(userId).child("player_name").getRef().setValue(userName);

                                    //getting players in room
                                    for(DataSnapshot players : rooms.getChildren()){

                                        String getOpponentName = players.child("player_name").getValue(String.class);
                                        opponentId = players.getKey();

                                        // set players name to TextView
                                        currentUserName.setText(userName);
                                        opponentName.setText(getOpponentName);

                                        // set room id
                                        roomId = getRoomId;
                                        opponentsFound = true;

                                        // add round and won listener to db
                                        //dbreference.child("rounds").child(roomId).addValueEventListener(roundsEventListener);
                                        //dbreference.child("won").child(roomId).addValueEventListener(wonEventListener);

                                        // dismiss progress dialog
                                        if (progressDialog.isShowing()){
                                            progressDialog.dismiss();
                                        }

                                        //start quiz
                                        fetchQuestionsFromDB();

                                        // once the room is completed remove from db reference
                                        dbreference.child("survival_rooms").removeEventListener(this);

                                        break;
                                    }

                                }

                            }

                        }
                        // check if opponent is not found and user is not waiting anymore then create a new room
                        if (!opponentsFound && !status.equals("waiting")){

                            // generate unique room id
                            String roomUniqueId = String.valueOf(System.currentTimeMillis());
                            snapshot.child(roomUniqueId).child(userId).child("player_name").getRef().setValue(userName);
                            status = "waiting";
                        }

                    }
                    // if there is no room available in realtime db create a new room and wait for opponents to join
                    else{
                        // generate unique room id
                        String roomUniqueId = String.valueOf(System.currentTimeMillis());
                        snapshot.child(roomUniqueId).child(userId).child("player_name").getRef().setValue(userName);
                        status = "waiting";
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        roundsEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                // getting all rounds of the room
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        wonEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //at 30
                // check if a player won the match
                if (snapshot.hasChild("player_id")){
                    String getWinnerId = snapshot.child("player_id").getValue(String.class);

                    if (getWinnerId.equals(userId)){
                        //show win dialog
                    }
                    else {
                        //lost -> show claim rewards dialog
                    }

                    // remove listeners from db
                    dbreference.child("rounds").child(roomId).removeEventListener(roundsEventListener);
                    dbreference.child("won").child(roomId).removeEventListener(wonEventListener);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        // quiz options
        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedAnswer = option1.getText().toString();
                questionList.get(currentRoundNum).setSelectedAnswer(selectedAnswer);

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
                questionList.get(currentRoundNum).setSelectedAnswer(selectedAnswer);

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
                questionList.get(currentRoundNum).setSelectedAnswer(selectedAnswer);

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
                questionList.get(currentRoundNum).setSelectedAnswer(selectedAnswer);

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
                // once the room is completed remove from db reference
                dbreference.child("survival_rooms").child(roomId).removeValue();
                finish();
            }
        });


    }


    private void setNextQuestion(){
        currentRoundNum++;
        countDownTimer.cancel();

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(currentRoundNum < questionList.size()){

                    setQuestionTimer(timer);
                    roundNum.setText("Round " + (currentRoundNum +1));

                    //set option background to unselected
                    option1.setBackgroundColor(Color.WHITE);
                    option1.setTextColor(Color.BLACK);

                    option2.setBackgroundColor(Color.WHITE);
                    option2.setTextColor(Color.BLACK);

                    option3.setBackgroundColor(Color.WHITE);
                    option3.setTextColor(Color.BLACK);

                    option4.setBackgroundColor(Color.WHITE);
                    option4.setTextColor(Color.BLACK);

                    question.setText(questionList.get(currentRoundNum).getQuestion());
                    option1.setText(questionList.get(currentRoundNum).getOption1());
                    option2.setText(questionList.get(currentRoundNum).getOption2());
                    option3.setText(questionList.get(currentRoundNum).getOption3());
                    option4.setText(questionList.get(currentRoundNum).getOption4());
                }
                else{
                    dislayResults();
                }
            }
        },1000);  //wait 1sec

    }

    public boolean checkAnswer(TextView textView){
        String selected = textView.getText().toString();
        final String getCorrectAnswer = questionList.get(currentRoundNum).getCorrectAnswer();

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
        final String getCorrectAnswer = questionList.get(currentRoundNum).getCorrectAnswer();

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

    public void fetchQuestionsFromDB(){

        Query query = FirebaseDatabase.getInstance("https://quizmegame-df195-default-rtdb.europe-west1.firebasedatabase.app/").getReference()
                .child("Questions").child("Any Category")
                .limitToLast(10);

        //show dialog while questions are being fetched
        ProgressDialog progressDialog = new ProgressDialog(SurvivalQuizActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading");
        progressDialog.show();


        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //int count = (int) snapshot.getChildrenCount();
                //int rand = new Random().nextInt(count);

                // getting all questions for a specific category
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){


                    // getting data from firebase db
                    final String getQuestion = dataSnapshot.child("question").getValue(String.class);
                    final String getOption1 = dataSnapshot.child("option1").getValue(String.class);
                    final String getOption2 = dataSnapshot.child("option2").getValue(String.class);
                    final String getOption3 = dataSnapshot.child("option3").getValue(String.class);
                    final String getOption4 = dataSnapshot.child("option4").getValue(String.class);
                    final String getCorrectAnswer = dataSnapshot.child("correctAnswer").getValue(String.class);

                    // adding data to the questionList
                    Question question = new Question(getQuestion,getOption1,getOption2,getOption3,getOption4,getCorrectAnswer,selectedAnswer);
                    questionList.add(question);
                }


                // hide dialog
                progressDialog.dismiss();

                // set questions to TextView
                roundNum.setText("Round " + (currentRoundNum +1));

                question.setText(questionList.get(currentRoundNum).getQuestion());
                option1.setText(questionList.get(currentRoundNum).getOption1());
                option2.setText(questionList.get(currentRoundNum).getOption2());
                option3.setText(questionList.get(currentRoundNum).getOption3());
                option4.setText(questionList.get(currentRoundNum).getOption4());

                //set question countdown timer
                setQuestionTimer(timer);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void dislayResults(){

        dbreference.child("survival_rooms").child(roomId).removeValue();
        Intent intent = new Intent(SurvivalQuizActivity.this,SurvivalStartingActivity.class);  //QuizResults
        //intent.putExtra("correct",getCorrectAnswers());


        startActivity(intent);
        finish();
    }

}