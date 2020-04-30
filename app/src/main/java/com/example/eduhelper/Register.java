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

public class Register extends AppCompatActivity {
    EditText rIdNo, rName, rEmail, rFaculty, rYear, rPassword, rConPassword;
    Button rRegisterBtn;
    TextView rGoToLogin;
    FirebaseAuth fAth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        rIdNo = findViewById(R.id.idNo);
        rName = findViewById(R.id.name);
        rEmail = findViewById(R.id.email);
        rFaculty = findViewById(R.id.faculty);
        rPassword = findViewById(R.id.password);
        rRegisterBtn = findViewById(R.id.registerBtn);
        rGoToLogin = findViewById(R.id.goToLogin);

        fAth = FirebaseAuth.getInstance();

        if (fAth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        rRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idNo = rIdNo.getText().toString().trim();
                String name = rName.getText().toString().trim();
                String email = rEmail.getText().toString().trim();
                String faculty = rFaculty.getText().toString().trim();
                String password = rPassword.getText().toString().trim();

                if(TextUtils.isEmpty(idNo)){
                    rIdNo.setError("ID Number is required.");
                    return;
                }
                if (TextUtils.isEmpty(name)){
                    rName.setError("Name is required.");
                    return;
                }
                if (TextUtils.isEmpty(email)){
                    rEmail.setError("Email is required.");
                    return;
                }
                if (TextUtils.isEmpty(faculty)){
                    rFaculty.setError("Faculty is required.");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    rPassword.setError("Password is required.");
                    return;
                }if (password.length() < 6){
                    rPassword.setError("Password must be greater than six characters.");
                    return;
                }


                //register the user in firebase

                fAth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(Register.this,"User Created.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }else{
                            Toast.makeText(Register.this, "Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });

        rGoToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });

    }
}
