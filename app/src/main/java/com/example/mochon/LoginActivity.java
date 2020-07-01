package com.example.mochon;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mochon.Retrofit.NetworkHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText login_id,login_pw;

    Button btn_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_id = findViewById(R.id.login_id);
        login_pw = findViewById(R.id.login_pw);
        btn_register = findViewById(R.id.btn_register);

        Button btn_login = (Button) findViewById(R.id.btn_login);
       // EditTextz btn_register = (Button)findViewById(R.id.btn_register);
       // Button btn_googlelogin = (Button) findViewById(R.id.btn_googlelogin);



//        //구글 로그인 버튼 > 구글 로그인 액티비티
//        btn_googlelogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(LoginActivity.this,GoogleLoginActivity.class);
//                startActivity(intent);
//
//            }
//        });


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String email = login_id.getText().toString();
                String password = login_pw.getText().toString();

            //    System.out.println("이ㅏ아ㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏ웨ㄹ시이ㅣㅣㅣㅣㅣㅣㅣ");

                if (email != null || password != null) {
                    if (login_pw.length() > 6) {
                        login();
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "비밀번호는 8자 이상...", Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                   // System.out.println("이ㅏ아ㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏ웨ㄹ시이ㅣㅣㅣㅣㅣㅣㅣ");
                }

            }
        });
    }

     void login() {


        NetworkHelper.getInstance().login(login_id.getText().toString(), login_pw.getText().toString()).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                System.out.println(
                        ""+response.body() + response.code());
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                           Toast.makeText(LoginActivity.this, "성공", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);


                    } else if (response.code() == 401) {
                        Toast.makeText(LoginActivity.this, "실패", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        System.out.println(response.code());
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "서버오류.", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("error", t.toString());

            }
        });
    }


}
