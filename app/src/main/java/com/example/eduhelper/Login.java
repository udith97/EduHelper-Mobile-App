package com.example.eduhelper;

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

public class Login extends AppCompatActivity {
    EditText rEmail, rPassword;
    Button rSignInBtn;
    TextView rGoToRegister;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        rEmail = findViewById(R.id.email);
        rPassword = findViewById(R.id.password);
        rSignInBtn = findViewById(R.id.signInBtn);
        rGoToRegister = findViewById(R.id.goToRegister);
        fAuth = FirebaseAuth.getInstance();

        rSignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = rEmail.getText().toString().trim();
                String password = rPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    rEmail.setError("Email is required.");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    rPassword.setError("Password is required.");
                    return;
                }if (password.length() < 6){
                    rPassword.setError("Password mus be greater than six characters.");
                    return;
                }

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(Login.this,"Logged in successfully.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }else{
                            Toast.makeText(Login.this, "Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });

        rGoToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Register.class));
            }
        });

    }
}
