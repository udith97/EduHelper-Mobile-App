package com.example.eduhelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateDataTimetable extends AppCompatActivity {
    EditText TMfaculty, TMyear, TMsemester, TMgroup;
    Button UpdateBtn;
    String faculty, year, semester, group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data_timetable);

        TMfaculty = findViewById(R.id.tm_faculty);
        TMyear = findViewById(R.id.tm_year);
        TMsemester =findViewById(R.id.tm_semester);
        TMgroup = findViewById(R.id.tm_group);
        UpdateBtn = findViewById(R.id.update);

        Intent intent = getIntent();
        faculty = intent.getStringExtra("tfaculty");
        year = intent.getStringExtra("tyear");
        semester = intent.getStringExtra("tsemester");
        group = intent.getStringExtra("tgroup");


        TMfaculty.setText(faculty);
        TMyear.setText(year);
        TMsemester.setText(semester);
        TMgroup.setText(group);

        UpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("timetables").child(year);
                String rfaculty, ryear, rsemester, rgroup ;

                rfaculty = TMfaculty.getText().toString();
                ryear = TMyear.getText().toString();
                rsemester = TMsemester.getText().toString();
                rgroup = TMgroup.getText().toString();


                timetablehelper timetablehelper = new timetablehelper(rfaculty, ryear, rsemester, rgroup);
                databaseReference.setValue(timetablehelper);
                Toast.makeText(UpdateDataTimetable.this,"Timetable Updated.",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),ListTimetables.class));
            }
        });

    }
}
