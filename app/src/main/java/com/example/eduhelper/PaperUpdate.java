package com.example.eduhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PaperUpdate extends AppCompatActivity {

    EditText up_year, up_moduleCode, up_semester, up_faculty, up_exam;
    Button updatebtn;
    String year, moduleCode, semester, faculty, exam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paper_update);

        up_year = findViewById(R.id.upyearpp);
        up_semester = findViewById(R.id.upsempp);
        up_moduleCode = findViewById(R.id.upmodulecpp);
        up_faculty = findViewById(R.id.upfacpp);
        up_exam = findViewById(R.id.upexampp);

        updatebtn = findViewById(R.id.upbtnpp);

        Intent intent = getIntent();
        year = intent.getStringExtra("iyear");
        semester = intent.getStringExtra("sem");
        moduleCode = intent.getStringExtra("mcode");
        faculty = intent.getStringExtra("ifac");
        exam = intent.getStringExtra("iexam");




        up_year.setText(year);
        up_semester.setText(semester);
        up_moduleCode.setText(moduleCode);
        up_faculty.setText(faculty);
        up_exam.setText(exam);



        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("pastPapers").child(year);
                String  umoduleCodep, usemesterp, ufacultyp, uexamp;



                umoduleCodep = up_moduleCode.getText().toString();
                usemesterp = up_semester.getText().toString();
                ufacultyp = up_faculty.getText().toString();
                uexamp = up_exam.getText().toString();

                PastpaperHelper pastpaperHelper = new PastpaperHelper(year,umoduleCodep,usemesterp,ufacultyp,uexamp);
                databaseReference.setValue(pastpaperHelper);
                Toast.makeText(PaperUpdate.this,"Data Updated" ,Toast.LENGTH_LONG).show();
            }
        });

    }
}
