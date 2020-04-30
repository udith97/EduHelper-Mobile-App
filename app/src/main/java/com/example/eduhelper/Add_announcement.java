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

public class Add_announcement extends AppCompatActivity {



    private EditText title,faculty,year,description;
    private Button submit, browse,upload;

    private Uri urifile;
    private ProgressDialog progressDialog;

    private FirebaseDatabase rootNode;
    private DatabaseReference mDatabase;
    private FirebaseStorage storage;
    private static String ref;
    private TextView message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_announcement);

        storage = FirebaseStorage.getInstance();
        rootNode = FirebaseDatabase.getInstance();


        title = findViewById(R.id.ann_title);
        faculty = findViewById(R.id.ann_faculty);
        year = findViewById(R.id.ann_year);
        description = findViewById(R.id.ann_des);

        submit = findViewById(R.id.submitBtn);
        browse = findViewById(R.id.browseBtn);
        upload = findViewById(R.id.confirmBtn);
        message = findViewById(R.id.txtview);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                mDatabase = rootNode.getReference("announcement");

                String annTitle = title.getText().toString();
                String annYear = year.getText().toString();
                String annFac = faculty.getText().toString();
                String annDes = description.getText().toString();

                if(TextUtils.isEmpty(annTitle)) {
                    title.setError("Title is required.");
                    return;
                }

                if(TextUtils.isEmpty(annFac)) {
                    faculty.setError("Faculty is required.");
                    return;
                }

                if(TextUtils.isEmpty(annYear)) {
                    year.setError("Year is required.");
                    return;
                }

                if(TextUtils.isEmpty(annDes)) {
                    description.setError("Description is required.");
                    return;
                }

                ref = annTitle;
                annTitle = ref;

                announcementHelper annHelper = new announcementHelper(annTitle, annFac, annYear, annDes);

                if(mDatabase != null) {
                    mDatabase.child(annTitle).setValue(annHelper);
                    Toast.makeText(Add_announcement.this,"Insert Successful", Toast.LENGTH_LONG).show();
                     startActivity(new Intent(getApplicationContext(), announcement_list.class));
                }else{
                    Toast.makeText(Add_announcement.this,"Input Failed", Toast.LENGTH_LONG).show();

                }

            }
        });

        browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ContextCompat.checkSelfPermission(Add_announcement.this, Manifest.permission.READ_EXTERNAL_STORAGE) == getPackageManager().PERMISSION_GRANTED){
                    selectpdf();
                }
                else{
                    ActivityCompat.requestPermissions(Add_announcement.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 9);
                }


            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (urifile != null) {
                    uploadPdf();
                }
            }
        });



        final Button Clear = findViewById(R.id.clearBtn);

        Clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Text = title.getText().toString();
                String Text2 = faculty.getText().toString();
                String Text3 = year.getText().toString();
                String Text4 = description.getText().toString();
                if (Text.isEmpty() && Text2.isEmpty() && Text3.isEmpty() && Text4.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Already Empty", Toast.LENGTH_SHORT).show();
                }else{
                    title.setText("");
                    faculty.setText("");
                    year.setText("");
                    description.setText("");


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
        else{Toast.makeText(Add_announcement.this,"please select a file",Toast.LENGTH_SHORT).show();
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
        storageReference.child("announcement").child(fileName).putFile(urifile).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                String url = taskSnapshot.getUploadSessionUri().toString();
                mDatabase = rootNode.getReference();
                mDatabase.child("announcementPDF").child(ref).setValue(url).addOnCompleteListener(new OnCompleteListener<Void>() {

                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(Add_announcement.this, "File successfully Submitted",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(Add_announcement.this, "File submission failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(Add_announcement.this, "File Upload Failed", Toast.LENGTH_SHORT).show();

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
