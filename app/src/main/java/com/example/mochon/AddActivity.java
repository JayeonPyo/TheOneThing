package com.example.mochon;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.mochon.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.DecimalFormat;

public class AddActivity extends AppCompatActivity implements RatingBar.OnRatingBarChangeListener{
    EditText add_title, add_des;
    RatingBar rating_import, rating_time, rating_success;
    Button add_btn;
    int pos;
    float currentImport, currentTime, currentSuccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        add_title = findViewById(R.id.add_title);
        add_des = findViewById(R.id.add_des);

        pos = getIntent().getIntExtra("pos", 0);

        add_btn = findViewById(R.id.add_btn);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(add_title.getText().toString().length() == 0 ||
                add_des.getText().toString().length() == 0){
                    Toast.makeText(AddActivity.this, "빈칸을 작성해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent();
                    intent.putExtra("title", add_title.getText().toString());
                    intent.putExtra("des", add_des.getText().toString());
                    intent.putExtra("import_rate", rating_import.getRating());
                    intent.putExtra("time_rate", rating_time.getRating());
                    intent.putExtra("success_rate", rating_success.getRating());

                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

        rating_import = findViewById(R.id.rating_import);
        rating_import.setOnRatingBarChangeListener(this);
        rating_time = findViewById(R.id.rating_time);
        rating_time.setOnRatingBarChangeListener(this);
        rating_success = findViewById(R.id.rating_success);
        rating_success.setOnRatingBarChangeListener(this);

    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        switch (ratingBar.getId()) {
            case R.id.rating_import:
                currentImport = rating;
                ratingBar.setRating(currentImport);
                Log.d("Add_PRINT", String.valueOf(currentImport));
                break;
            case R.id.rating_time:
                currentTime = rating;
                ratingBar.setRating(currentTime);
                break;
            case R.id.rating_success:
                currentSuccess = rating;
                ratingBar.setRating(currentSuccess);
                break;
        }

    }
}
