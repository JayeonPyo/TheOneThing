package com.example.mochon;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        Handler hd = new Handler();
        hd.postDelayed(new splashHandler(), 3000);
    }

    private class splashHandler implements Runnable{
        public void run(){
            startActivity(new Intent(getApplication(), LoginActivity.class));
            finish();
        }
    }

    //뒤로가기 못누르게 하기
    @Override
    public void onBackPressed() {
    }
}
