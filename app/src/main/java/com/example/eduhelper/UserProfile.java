package com.example.eduhelper;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class UserProfile extends AppCompatActivity {
    TextView pIdNo, pName, pEmail, pFaculty;
    Button pGoToHome;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        pIdNo = findViewById(R.id.profileId);
        pName = findViewById(R.id.profileName);
        pEmail = findViewById(R.id.profileEmail);
        pFaculty = findViewById(R.id.profileFaculty);
        pGoToHome = findViewById(R.id.goToHome);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userId = fAuth.getCurrentUser().getUid();

        DocumentReference documentReference = fStore.collection("users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                pIdNo.setText(documentSnapshot.getString("id"));
                pName.setText(documentSnapshot.getString("name"));
                pEmail.setText(documentSnapshot.getString("email"));
                pFaculty.setText(documentSnapshot.getString("faculty"));
            }
        });


    }
}