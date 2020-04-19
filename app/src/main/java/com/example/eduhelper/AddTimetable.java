package com.example.eduhelper;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddTimetable extends AppCompatActivity {


    //private static final int PICK_IMG_REQUEST = 1;

    private EditText faculty, year, semester, group;
    private Button submit, choosefile;

    //private Uri fileuri;

    private FirebaseDatabase rootNode;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_timetable);

        faculty = findViewById(R.id.in1);
        year = findViewById(R.id.in2);
        semester = findViewById(R.id.in3);
        group = findViewById(R.id.in4);

        submit = findViewById(R.id.btn_submit);
        choosefile = findViewById(R.id.btn_browse);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                mDatabase = rootNode.getReference("timetables");

               String tfaculty = faculty.getText().toString();
               String tyear = year.getText().toString();
               String tsemester = semester.getText().toString();
               String tgroup = group.getText().toString();
               timetablehelper Timetablehelper = new timetablehelper(tfaculty, tyear, tsemester, tgroup);

               mDatabase.child(tyear).setValue(Timetablehelper);

            }
        });

//        final EditText Input = findViewById(R.id.in1);
//        final Button Clear = findViewById(R.id.clr_btn);
//
//        Clear.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               String Text = Input.getText().toString();
//               if (Text.isEmpty()) {
//                   Toast.makeText(getApplicationContext(), "Already Empty", Toast.LENGTH_SHORT).show();
//               }else{
//                   Input.setText("");
//
//
//               }
//            }
//        });


    }
}
