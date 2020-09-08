package com.example.controlealimentos.app.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.controlealimentos.R;
import com.example.controlealimentos.app.controller.Config;

public class Splash_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_);

        iniciarAPP();
    }

    public void  iniciarAPP(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash_Activity.this, LoginActivity.class);
                startActivity(intent);
            }
        }, Config.TIME_SPLASH);
    }
}