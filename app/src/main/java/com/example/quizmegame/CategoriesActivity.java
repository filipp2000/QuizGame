package com.example.quizmegame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;

public class CategoriesActivity extends AppCompatActivity {

    private String selectedCategoryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        final CardView geography = findViewById(R.id.geo_categ);
        final CardView entert_categ = findViewById(R.id.entert_categ);
        final CardView sports_categ = findViewById(R.id.sports_categ);
        final CardView history_categ = findViewById(R.id.history_categ);
        final CardView science_categ = findViewById(R.id.science_categ);
        final CardView art_categ = findViewById(R.id.art_categ);

        geography.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedCategoryName = "geography";
            }
        });

        entert_categ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectedCategoryName = "entertainment";


            }
        });

        sports_categ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectedCategoryName = "sports";


            }
        });

        history_categ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectedCategoryName = "history";


            }
        });

        science_categ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectedCategoryName = "science";


            }
        });

        art_categ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectedCategoryName = "art";


            }
        });


    }
}