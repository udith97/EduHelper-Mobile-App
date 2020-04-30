package com.example.eduhelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Button LecturerListBtn, TimetableBtn, AnnouncementListBtn, CalculaterBtn, PastPaperBtn, semfeecal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LecturerListBtn = findViewById(R.id.lecturerBtn);
        TimetableBtn = findViewById(R.id.timeTableBtn);
        AnnouncementListBtn = findViewById(R.id.announcementBtn);
        CalculaterBtn = findViewById(R.id.calBtn);
        PastPaperBtn = findViewById(R.id.pastPaperBtn);
        semfeecal = findViewById(R.id.button16);


        LecturerListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),List_Lecturers.class));
            }
        });

        TimetableBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ListTimetables.class));
            }
        });

        AnnouncementListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),announcement_list.class));
            }
        });

        CalculaterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Marks_Calculation.class));
            }
        });

        PastPaperBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),RetrivePastpaperData.class));
            }
        });

        semfeecal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),PaymentDetails.class));
            }
        });

    }

    public void logout (View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();
    }
}
