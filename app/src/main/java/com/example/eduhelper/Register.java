package com.example.eduhelper;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    EditText rIdNo, rName, rEmail, rFaculty, rPassword;
    Button rRegisterBtn;
    TextView rGoToLogin;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;

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

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        if (fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        rRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String idNo = rIdNo.getText().toString();
                final String name = rName.getText().toString();
                final String email = rEmail.getText().toString().trim();
                final String faculty = rFaculty.getText().toString();
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
                    rPassword.setError("Password mus be greater than six characters.");
                    return;
                }


                //register the user in firebase

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(Register.this,"User Created.", Toast.LENGTH_SHORT).show();
                            userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("users").document(userID);
                            Map<String,Object> user = new HashMap<>();
                            user.put("id",idNo);
                            user.put("name",name);
                            user.put("email",email);
                            user.put("faculty",faculty);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d("TAG", "onSuccess: User Profile is created for"+userID);
                                }
                            });

                        }else{
                            Toast.makeText(Register.this, "Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });startActivity(new Intent(getApplicationContext(), MainActivity.class));

        rGoToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });

    }
}
