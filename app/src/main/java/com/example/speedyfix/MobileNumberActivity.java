package com.example.speedyfix;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MobileNumberActivity extends AppCompatActivity {
    private Button phoneNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            // Load the welcome fragment as the initial screen
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, new WelcomeFragment())
                    .commit();
        }

    }
}