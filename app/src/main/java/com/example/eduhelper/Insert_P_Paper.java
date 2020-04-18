package com.example.eduhelper;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Insert_P_Paper extends AppCompatActivity {

    private static final int PICK_IMG_REQUEST = 1;
    private EditText year,faculty, moduleCode, exam, semester;
    private Button submit, choosefile;

    private Uri fileuri;

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
        choosefile = findViewById(R.id.ip_choosefile_btn);



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                mDatabase = rootNode.getReference("pastPapers");

                int iyear = Integer.parseInt(year.getText().toString());
                int sem = Integer.parseInt(semester.getText().toString());
                String mcode = moduleCode.getText().toString();
                String ifac = faculty.getText().toString();
                String iexam = exam.getText().toString();

                pastpaperHelper paperHelper = new pastpaperHelper(iyear, sem, mcode, ifac, iexam);

                mDatabase.child(mcode).setValue(paperHelper);

            }
        });


        choosefile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenFileChooser();
            }
        });
    }


    private void OpenFileChooser(){
        Intent intent = new Intent();

        intent.setType("file/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMG_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMG_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){

            fileuri = data.getData();


        }
    }
}
