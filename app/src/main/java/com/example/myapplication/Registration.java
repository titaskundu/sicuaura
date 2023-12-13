package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Registration extends AppCompatActivity {

    EditText editemail, editnumber, editpassword, confirm;
    Button butreg;
    FirebaseAuth mAuth;
    TextView textView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        TextView textView = findViewById(R.id.textView13);
        mAuth=FirebaseAuth.getInstance();
        editemail=findViewById(R.id.email);
        editnumber=findViewById(R.id.phonenumber);
        editpassword=findViewById(R.id.password);
        confirm=findViewById(R.id.Reenter);
        textView=findViewById(R.id.textView13);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();

            }
        });
        butreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, password, number, confp;
                email=String.valueOf(editemail.getText());
                password=String.valueOf(editpassword.getText());
                number=String.valueOf(editnumber.getText());
                confp=String.valueOf(confirm.getText());

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confp)) {
                    Toast.makeText(Registration.this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (!password.equals(confp)) {
                    Toast.makeText(Registration.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    return;
                }
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information

                                    Toast.makeText(Registration.this, "Account created", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                    startActivity(intent);
                                    finish();

                                } else {
                                    // If sign in fails, display a message to the user.

                                    Toast.makeText(Registration.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

            }
        });




    }
}