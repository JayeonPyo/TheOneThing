package com.example.mochon;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mochon.Retrofit.LoginUserModel;
import com.example.mochon.Retrofit.NetworkHelper;
import com.example.mochon.Retrofit.RegisterUserModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private EditText login_id,login_pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login_id = findViewById(R.id.login_id);
        login_pw = findViewById(R.id.login_pw);

        Button btn_login = (Button) findViewById(R.id.btn_login);
        Button btn_googlelogin = (Button) findViewById(R.id.btn_googlelogin);


//        //구글 로그인 버튼 > 구글 로그인 액티비티
//        btn_googlelogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this,GoogleLoginActivity.class);
//                startActivity(intent);
//
//            }
//        });



        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String email = login_id.getText().toString();
                String password = login_pw.getText().toString();


                if (email != null || password != null) {
                    if (login_pw.length() > 6) {
                        login();
                    }
                    else {
                        Toast.makeText(MainActivity.this, "비밀번호는 8자 이상...", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
    }

     void login() {


        NetworkHelper.getInstance().login(login_id.getText().toString(), login_pw.getText().toString()).enqueue(new Callback<LoginUserModel>() {
            @Override
            public void onResponse(Call<LoginUserModel> call, Response<LoginUserModel> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                           Toast.makeText(MainActivity.this, "성공", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(MainActivity.this, MainActivity.class);
//                        startActivity(intent);
                    } else if (response.code() == 401) {
                        Toast.makeText(MainActivity.this, "실패", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "서버오류.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginUserModel> call, Throwable t) {
                Log.e("error", t.toString());

            }
        });
    }


}
