package com.example.eduhelper;


import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class AddTimetable extends AppCompatActivity {



    private EditText faculty, year, semester, group;
    private Button submit, choosefile, upload;

    private Uri urifile;
    private ProgressDialog progressDialog;

    private FirebaseDatabase rootNode;
    private DatabaseReference mDatabase;
    private FirebaseStorage storage;
    private static String ref;
    private TextView message;
    private static String module;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_timetable);

        storage = FirebaseStorage.getInstance();
        rootNode = FirebaseDatabase.getInstance();


        faculty = findViewById(R.id.in1);
        year = findViewById(R.id.in2);
        semester = findViewById(R.id.in3);
        group = findViewById(R.id.in4);

        submit = findViewById(R.id.btn_submit);
        choosefile = findViewById(R.id.btn_browse);
        upload = findViewById(R.id.btn_confirm);
        message = findViewById(R.id.in5);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                mDatabase = rootNode.getReference("timetables");

               String tfaculty = faculty.getText().toString();
               String tyear = year.getText().toString();
               String tsemester = semester.getText().toString();
               String tgroup = group.getText().toString();


                if(TextUtils.isEmpty(tfaculty)){
                    faculty.setError("Faculty is required.");
                    return;
                }
                if(TextUtils.isEmpty(tyear)){
                    year.setError("Faculty is required.");
                    return;
                }

                if(TextUtils.isEmpty(tsemester)){
                    semester.setError("Faculty is required.");
                    return;
                }
                if(TextUtils.isEmpty(tgroup)){
                    group.setError("Faculty is required.");
                    return;
                }


                ref = tyear;
                tyear = ref;

               timetablehelper Timetablehelper = new timetablehelper(tfaculty, tyear, tsemester, tgroup);

               if(mDatabase != null) {
                   mDatabase.child(tyear).setValue(Timetablehelper);
                   Toast.makeText(AddTimetable.this,"Insert Successful", Toast.LENGTH_LONG).show();
//                   startActivity(new Intent(getApplicationContext(), ));
               }else{
                   Toast.makeText(AddTimetable.this,"Input Failed", Toast.LENGTH_LONG).show();

               }

            }
        });

        choosefile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ContextCompat.checkSelfPermission(AddTimetable.this, Manifest.permission.READ_EXTERNAL_STORAGE) == getPackageManager().PERMISSION_GRANTED){
                    selectpdf();
                }
                else{
                    ActivityCompat.requestPermissions(AddTimetable.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 9);
                }


            }
        });

//        upload.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(urifile != null) {
//
//                }
//            }
//        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (urifile != null) {
                    uploadPdf();
                }
                else{
                    Toast.makeText(AddTimetable.this,"Select a file to uploasd",Toast.LENGTH_SHORT).show();
                }
            }
        });




//        final EditText Input = findViewById(R.id.in1);
        final Button Clear = findViewById(R.id.clr_btn);

        Clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String Text = faculty.getText().toString();
               String Text2 = year.getText().toString();
                String Text3 = semester.getText().toString();
                String Text4 = group.getText().toString();
               if (Text.isEmpty() && Text2.isEmpty() && Text3.isEmpty() && Text4.isEmpty()) {
                   Toast.makeText(getApplicationContext(), "Already Empty", Toast.LENGTH_SHORT).show();
               }else{
                   faculty.setText("");
                   semester.setText("");
                   year.setText("");
                   group.setText("");


               }
            }
        });


    }
    private void selectpdf(){

        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,86);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 86 && resultCode == RESULT_OK && data != null){

            urifile = data.getData();
            message.setText("Selected file: " + data.getData().getLastPathSegment());

        }
        else{Toast.makeText(AddTimetable.this,"please select a file",Toast.LENGTH_SHORT).show();
    }


    }


    private  void uploadPdf(){

        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(progressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("Uploading Files...");
        progressDialog.setProgress(0);
        progressDialog.show();;


        final String fileName = ref;
        StorageReference storageReference = storage.getReference();
        storageReference.child("TimeTable").child(fileName).putFile(urifile).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                String url = taskSnapshot.getUploadSessionUri().toString();
                mDatabase = rootNode.getReference();
                mDatabase.child("TimetabelPDF").child(ref).setValue(url).addOnCompleteListener(new OnCompleteListener<Void>() {

                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(AddTimetable.this, "File successfully Submitted",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(AddTimetable.this, "File submission failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(AddTimetable.this, "File Upload Failed", Toast.LENGTH_SHORT).show();

            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                int currentProgress = (int) (100*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                progressDialog.setProgress(currentProgress);

            }
        }
        );



    }


}
