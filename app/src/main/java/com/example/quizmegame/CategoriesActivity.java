package com.example.quizmegame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class CategoriesActivity extends AppCompatActivity {

    private String selectedCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        final CardView geo_categ = findViewById(R.id.geo_categ);
        final CardView entert_categ = findViewById(R.id.entert_categ);
        final CardView sports_categ = findViewById(R.id.sports_categ);
        final CardView history_categ = findViewById(R.id.history_categ);
        final CardView science_categ = findViewById(R.id.science_categ);
        final CardView art_categ = findViewById(R.id.art_categ);

        final ImageView back_btn = findViewById(R.id.back_arrow);

        geo_categ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectedCategory = "Geography";
                Intent intent = new Intent(CategoriesActivity.this, SinglePlayerQuizActivity.class);
                intent.putExtra("selectedCategory", selectedCategory);
                startActivity(intent);

            }
        });

        entert_categ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectedCategory = "Entertainment";
                Intent intent = new Intent(CategoriesActivity.this, SinglePlayerQuizActivity.class);
                intent.putExtra("selectedCategory", selectedCategory);
                startActivity(intent);
            }
        });

        sports_categ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectedCategory = "Sports";
                Intent intent = new Intent(CategoriesActivity.this, SinglePlayerQuizActivity.class);
                intent.putExtra("selectedCategory", selectedCategory);
                startActivity(intent);

            }
        });

        history_categ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectedCategory = "History";
                Intent intent = new Intent(CategoriesActivity.this, SinglePlayerQuizActivity.class);
                intent.putExtra("selectedCategory", selectedCategory);
                startActivity(intent);

            }
        });

        science_categ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectedCategory = "Science";
                Intent intent = new Intent(CategoriesActivity.this, SinglePlayerQuizActivity.class);
                intent.putExtra("selectedCategory", selectedCategory);
                startActivity(intent);

            }
        });

        art_categ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectedCategory = "Art";
                Intent intent = new Intent(CategoriesActivity.this, SinglePlayerQuizActivity.class);
                intent.putExtra("selectedCategory", selectedCategory);
                startActivity(intent);

            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CategoriesActivity.this,HomeActivity.class));
                finish();
            }
        });

    }
}