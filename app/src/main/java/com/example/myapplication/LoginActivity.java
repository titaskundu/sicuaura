package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    EditText editemail, editnumber, editpassword;
    Button butlog;
    FirebaseAuth mAuth;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth= FirebaseAuth.getInstance();
        editemail=findViewById(R.id.email);
        editpassword=findViewById(R.id.password);
        butlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, password, number, confp;
                email=String.valueOf(editemail.getText());
                password=String.valueOf(editpassword.getText());
                number=String.valueOf(editnumber.getText());


                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password) ) {
                    Toast.makeText(LoginActivity.this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
                    return;
                }



                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(getApplicationContext(), allow.class);
                                    startActivity(intent);
                                    finish();


                                } else {
                                    // If sign in fails, display a message to the user.

                                    Toast.makeText(LoginActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });


            }
        });


    }
}