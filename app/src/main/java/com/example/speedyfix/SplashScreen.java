package com.example.speedyfix;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {
    String loginValue = "";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        if(loginValue.isEmpty()){
            Intent intent = new Intent(this, MobileNumberActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
