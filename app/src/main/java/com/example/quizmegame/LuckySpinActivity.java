package com.example.quizmegame;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LuckySpinActivity extends AppCompatActivity {

    private ImageView spin;
    private LuckySpin lw;
    private static final Random random = new Random();
    List<SpinItem> spinItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luckyspin);

        generateSpinItems();

        lw = findViewById(R.id.lwv);
        lw = addSpinItems(spinItems);
        lw.setTarget(1);

        lw.setLuckySpinReachTheTarget(new OnLuckySpinReachTheTarget());
        @Override
        public voidnonReachTarget(){
            Toast.makeText(LuckySpinActivity.this,"Your prize is ",Toast.LENGTH_SHORT).show();
        }

        }

    private LuckySpin addSpinItems(List<SpinItem> spinItems) {
    });

    Button start = findViewById(R.id.start);
    start.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view){
            lw.rotateSpinTo(1);
        }
    });

    private void generateSpinItems(){
        spinItems = new ArrayList<>();
        spinItems.add(new spinItem(Color.parseColor("#ffffff"),BitmapFactory.decodeResource(getResources(),R.drawable.coin),"X2"));
        spinItems.add(new spinItem(Color.parseColor("#f44336"),BitmapFactory.decodeResource(getResources(),R.drawable.twemoji_ticket),"X2"));
        spinItems.add(new spinItem(Color.parseColor("#ffffff"),BitmapFactory.decodeResource(getResources(),R.drawable.coin),"100"));
        spinItems.add(new spinItem(Color.parseColor("#f44336"),BitmapFactory.decodeResource(getResources(),R.drawable.heart),"X1"));
        spinItems.add(new spinItem(Color.parseColor("#ffffff"),BitmapFactory.decodeResource(getResources(),R.drawable.twemoji_ticket),"X2"));
        spinItems.add(new spinItem(Color.parseColor("#f44336"),BitmapFactory.decodeResource(getResources(),R.drawable.coin),"300"));
        spinItems.add(new spinItem(Color.parseColor("#ffffff"),BitmapFactory.decodeResource(getResources(),R.drawable.twemoji_ticket),"X2"));
        spinItems.add(new spinItem(Color.parseColor("#f44336"),BitmapFactory.decodeResource(getResources(),R.drawable.correct),"X1"));
        spinItems.add(new spinItem(Color.parseColor("#ffffff"),BitmapFactory.decodeResource(getResources(),R.drawable.heart),"X2"));
        spinItems.add(new spinItem(Color.parseColor("#f44336"),BitmapFactory.decodeResource(getResources(),R.drawable.coin),"X2"));
        spinItems.add(new spinItem(Color.parseColor("#ffffff"),BitmapFactory.decodeResource(getResources(),R.drawable.twemoji_ticket),"X1"));
        spinItems.add(new spinItem(Color.parseColor("#f44336"),BitmapFactory.decodeResource(getResources(),R.drawable.correct),"X2"));
    }

}

    private class SpinItem {
    }
}
