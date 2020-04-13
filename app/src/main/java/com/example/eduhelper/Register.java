package com.example.eduhelper;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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
        rYear = findViewById(R.id.year);
        rPassword = findViewById(R.id.password);
        rConPassword = findViewById(R.id.conPassword);
        rRegisterBtn = findViewById(R.id.registerBtn);
        rGoToLogin = findViewById(R.id.goToLogin);

        fAth = FirebaseAuth.getInstance();

    }
}
