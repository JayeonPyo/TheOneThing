package com.example.mochon;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class EditActivity extends AppCompatActivity implements RatingBar.OnRatingBarChangeListener{
    TextView title_edit, des_edit;
    RatingBar import_edit, time_edit, success_edit;
    Button edit_btn;
    String titleStr, desStr;
    int pos;
    float import_float, time_float, success_float;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        title_edit = findViewById(R.id.edit_title);
        des_edit = findViewById(R.id.edit_des);
        import_edit = findViewById(R.id.import_edit);
        import_edit.setOnRatingBarChangeListener(this);
        time_edit = findViewById(R.id.time_edit);
        time_edit.setOnRatingBarChangeListener(this);
        success_edit =findViewById(R.id.success_edit);
        success_edit.setOnRatingBarChangeListener(this);
        edit_btn = findViewById(R.id.edit_btn);
        pos = getIntent().getIntExtra("pos", 0);

        titleStr = getIntent().getStringExtra("title_edit");
        desStr = getIntent().getStringExtra("des_edit");
        import_float = getIntent().getFloatExtra("import_edit", 0);
        time_float= getIntent().getFloatExtra("time_edit", 0);
        success_float = getIntent().getFloatExtra("success_edit", 0);

        edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(title_edit.getText().toString().length() == 0 ||
                        des_edit.getText().toString().length() == 0){
                    Toast.makeText(EditActivity.this, "빈칸을 작성해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent();
                    intent.putExtra("title_edit", title_edit.getText().toString());
                    intent.putExtra("des_edit", des_edit.getText().toString());
                    intent.putExtra("import_edit", import_edit.getRating());
                    intent.putExtra("time_edit", time_edit.getRating());
                    intent.putExtra("success_edit", success_edit.getRating());
                    intent.putExtra("position", pos);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

        if(titleStr != null){
            title_edit.setText(titleStr);
            des_edit.setText(desStr);
            import_edit.setRating(import_float);
            time_edit.setRating(time_float);
            success_edit.setRating(success_float);
        }


    }


    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        switch (ratingBar.getId()) {
            case R.id.import_edit:
                import_float = rating;
                import_edit.setRating(import_float);
                Log.d("Edit_PRINT", String.valueOf(import_float));
                break;
            case R.id.time_edit:
                time_float = rating;
                time_edit.setRating(time_float);
                break;
            case R.id.success_edit:
                success_float = rating;
                success_edit.setRating(success_float);
                break;
        }
    }
}
