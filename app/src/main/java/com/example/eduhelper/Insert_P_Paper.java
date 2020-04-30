package com.example.eduhelper;


import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
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


public class Insert_P_Paper extends AppCompatActivity {

    private EditText year,faculty, moduleCode, exam, semester;

    private Button submit, choosefile, upload, downloadpdf;
    private TextView notification;
    private Uri fileUri;
    private ProgressDialog progressDialog;

    private FirebaseDatabase rootNode;
    private DatabaseReference mDatabase;
    private FirebaseStorage storage;
    private static String ref;
    private   static String module;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert__p__paper);


        storage = FirebaseStorage.getInstance();
        rootNode = FirebaseDatabase.getInstance();



        year = findViewById(R.id.ip_year);
        semester = findViewById(R.id.ip_semester);
        moduleCode = findViewById(R.id.ip_modulecode);
        exam = findViewById(R.id.ip_exam);
        faculty = findViewById(R.id.ip_faculty);


        submit = findViewById(R.id.ipSubmit);
        choosefile = findViewById(R.id.ip_choosefile_btn);
        upload = findViewById(R.id.ipupload);
        notification = findViewById(R.id.ip_showtext);
        downloadpdf = findViewById(R.id.paperdown);






        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String iyear = year.getText().toString();
                String sem = semester.getText().toString();
                String mcode = moduleCode.getText().toString();
                String ifac = faculty.getText().toString();
                String iexam = exam.getText().toString();
                ref = iyear;
                iyear = ref;

                module = mcode;
                mcode = module;


                if(TextUtils.isEmpty(iyear)){
                    year.setError("Year is required.");
                    return;
                }
                if(TextUtils.isEmpty(sem)){
                    semester.setError("Semester is required.");
                    return;
                }

                if(TextUtils.isEmpty(mcode)){
                    moduleCode.setError("Module Code is required.");
                    return;
                }
                if(TextUtils.isEmpty(ifac)){
                    faculty.setError("Faculty is required.");
                    return;
                }
                if(TextUtils.isEmpty(iexam)){
                    exam.setError("Exam is required.");
                    return;
                }



                PastpaperHelper paperHelper = new PastpaperHelper(iyear, sem, mcode, ifac, iexam);
                mDatabase = rootNode.getReference("pastPapers");

                if(mDatabase != null ) {

                    mDatabase.child(iyear).setValue(paperHelper);

                    Toast.makeText(Insert_P_Paper.this,"Insert Successful", Toast.LENGTH_SHORT).show();
//            startActivity(new Intent(getApplicationContext(), ViewPastPapers.class));
                }

                else{
                    Toast.makeText(Insert_P_Paper.this,"Insert Failed!", Toast.LENGTH_SHORT).show();
                }

            }
        });

//        downloadpdf.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(Insert_P_Paper.this, PaperPDFAdapter.class));
//            }
//        });


        choosefile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(Insert_P_Paper.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                    selectPdf();
                }
                else{
                    ActivityCompat.requestPermissions(Insert_P_Paper.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},9);
                }
            }
        });



        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fileUri != null) {
                    uploadFile();

                }
                else{
                    Toast.makeText(Insert_P_Paper.this,"Select a file to upload", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

//
//    private void inputText(){
//
//
//    }



    @Override
    public void onRequestPermissionsResult(int requestCode,  String[] permissions, int[] grantResults) {

        if(requestCode == 9 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            selectPdf();
        }
        else{
            Toast.makeText(Insert_P_Paper.this, "Please Provide Permission",Toast.LENGTH_SHORT).show();
        }
    }

    private void selectPdf(){

        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,86);
    }

    private void uploadFile(){

        progressDialog =new ProgressDialog(this);
        progressDialog.setProgressStyle(progressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("Uploading file..");
        progressDialog.setProgress(0);
        progressDialog.show();

        final String fileName =  module;
        StorageReference storageReference = storage.getReference();
        storageReference.child("Past_Papers").child(fileName).putFile(fileUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                      String url = taskSnapshot.getUploadSessionUri().toString();
                      mDatabase = rootNode.getReference();
                      mDatabase.child("pastPaperPDF").child(module).setValue(url).addOnCompleteListener(new OnCompleteListener<Void>() {

                          @Override
                          public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(Insert_P_Paper.this, "File successfully Submitted",Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(Insert_P_Paper.this, "File submission failed", Toast.LENGTH_SHORT).show();
                                }
                          }
                      });
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(Insert_P_Paper.this, "File Upload Failed", Toast.LENGTH_SHORT).show();

            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                int currentProgress = (int) (100*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                progressDialog.setProgress(currentProgress);

            }





        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 86 && resultCode == RESULT_OK && data != null) {
            fileUri = data.getData();
            notification.setText("Selected file: " + data.getData().getLastPathSegment());
        } else {
            Toast.makeText(Insert_P_Paper.this, "Please select a file", Toast.LENGTH_LONG).show();
        }
    }

}
