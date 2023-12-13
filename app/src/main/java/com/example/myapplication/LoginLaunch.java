package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginLaunch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_launch);


        Button loginButton = findViewById(R.id.button);
        TextView newUserButton = findViewById(R.id.textView10);

            loginButton.setOnClickListener(v -> {
                // Switch to SignInActivity
                Intent signInIntent = new Intent(LoginLaunch.this, LoginActivity.class);
                startActivity(signInIntent);
            });

            newUserButton.setOnClickListener(v -> {
                // Switch to SignUpActivity
                Intent signUpIntent = new Intent(LoginLaunch.this, Registration.class);
                startActivity(signUpIntent);
            });
    }
}