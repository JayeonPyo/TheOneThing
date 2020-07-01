package com.example.mochon;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mochon.Retrofit.NetworkHelper;
import com.example.mochon.Retrofit.RegisterUserModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private EditText user_name,user_id,user_pw, user_repw;
    private Spinner spinner;
    public String user_mbti;
    //private Button btn_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        user_name = findViewById(R.id.user_name);
        user_id = findViewById(R.id.user_id);
        user_pw= findViewById(R.id.user_pw);
        user_repw = findViewById(R.id.user_repw);

       // btn_signup = findViewById(R.id.btn_signup);




        Button btn_signup = (Button) findViewById(R.id.btn_signup);
        //final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner = findViewById(R.id.spinner);

        String[] items = getResources().getStringArray(R.array.mbti_array);


        //DataSource(ArrayList) < Adapter > Adapter View(List View, spinner)
        ArrayAdapter myAdapter =
                new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinner.setAdapter(myAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //  Toast.makeText(RegisterActivity.this, "MBTI : " + spinner.getItemAtPosition(position), Toast.LENGTH_SHORT).show();


                String spinner_item = spinner.getSelectedItem().toString();
                System.out.println(spinner_item);
                user_mbti = spinner_item;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = user_name.getText().toString();
                String email = user_id.getText().toString();
                String password = user_pw.getText().toString();
                String passwordConfirm = user_repw.getText().toString();
                String mbti = user_mbti;


                if (name != null || email != null || password != null || passwordConfirm != null || mbti != null) {
                    if (user_pw.length() > 6) {
                        register();
                    }
                    else {
                        Toast.makeText(RegisterActivity.this, "비밀번호는 8자 이상...", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
    }

    public void register() {


        NetworkHelper.getInstance().register(user_name.getText().toString(), user_id.getText().toString(), user_pw.getText().toString()
                , user_repw.getText().toString(),user_mbti).enqueue(new Callback<RegisterUserModel>() {
            @Override
            public void onResponse(Call<RegisterUserModel> call, Response<RegisterUserModel>    response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        Toast.makeText(RegisterActivity.this, "성공", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                    } else if (response.code() == 401) {
                        Toast.makeText(RegisterActivity.this, "비밀번호이 다르거나 이미 회원 가입한 회원입니다.", Toast.LENGTH_LONG).show();

                    }
                } else {
                    System.out.println(""+response.code());
                    Toast.makeText(RegisterActivity.this, "서버 오류.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterUserModel> call, Throwable t) {
                Log.e("error", t.toString());

            }
        });
    }
}












