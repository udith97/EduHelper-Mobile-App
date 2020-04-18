package com.example.eduhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Insert_P_Paper extends AppCompatActivity {

    private EditText year,faculty, moduleCode, exam, semester;
    private Button submit;

    private FirebaseDatabase rootNode;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert__p__paper);

        year = findViewById(R.id.ip_year);
        semester = findViewById(R.id.ip_semester);
        moduleCode = findViewById(R.id.ip_modulecode);
        exam = findViewById(R.id.ip_exam);
        faculty = findViewById(R.id.ip_faculty);
        submit = findViewById(R.id.ipSubmit);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                mDatabase = rootNode.getReference("pastPapers");

//                int iyear = Integer.parseInt(year.getEditableText().toString());
//                int isem = Integer.parseInt(semester.getEditableText().toString());
//                String mcode= moduleCode.getEditableText().toString();
//                String ifac= faculty.getEditableText().toString();
//                String iexam = exam.getEditableText().toString();
//
//                pastpaperHelper  pastpaperHelper = new pastpaperHelper(iyear, isem, mcode, ifac, iexam);
//
//                mDatabase.child(mcode).setValue(pastpaperHelper);

                int iyear = Integer.parseInt(year.getText().toString());
                int sem = Integer.parseInt(semester.getText().toString());
                String mcode = moduleCode.getText().toString();
                String ifac = faculty.getText().toString();
                String iexam = exam.getText().toString();

                pastpaperHelper paperHelper = new pastpaperHelper(iyear, sem, mcode, ifac, iexam);


                mDatabase.child(mcode).setValue(paperHelper);



            }
        });









    }
}
