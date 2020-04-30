package com.example.eduhelper;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Add_Lecturers extends AppCompatActivity {

    //private static final int PICK_IMG_REQUEST = 1;
    EditText lecturerName, lecturerModule, lecturerLocation, lecturerEmail, lecturerContact;
    Button insertBtn;

    FirebaseDatabase rootNode;
    DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__lecturers);

        lecturerName = findViewById(R.id.name);
        lecturerModule = findViewById(R.id.moduleCode);
        lecturerLocation = findViewById(R.id.location);
        lecturerEmail = findViewById(R.id.email);
        lecturerContact = findViewById(R.id.contactNumber);

        insertBtn = findViewById(R.id.insertLecturer);

        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rootNode = FirebaseDatabase.getInstance();
                mDatabase = rootNode.getReference("lecturer");


                String lname = lecturerName.getText().toString();
                String lmoduleCode = lecturerModule.getText().toString();
                String llocation = lecturerLocation.getText().toString();
                String lemail = lecturerEmail.getText().toString();
                String lcontactNumber = lecturerContact.getText().toString();

                if(TextUtils.isEmpty(lname)){
                    lecturerName.setError("Name is required.");
                    return;
                }
                if(TextUtils.isEmpty(lmoduleCode)){
                    lecturerModule.setError("Module Code is required.");
                    return;
                }
                if(TextUtils.isEmpty(llocation)){
                    lecturerLocation.setError("Location is required.");
                    return;
                }
                if(TextUtils.isEmpty(lemail)){
                    lecturerEmail.setError("Email is required.");
                    return;
                }
                if(TextUtils.isEmpty(lcontactNumber)){
                    lecturerContact.setError("Contact is required.");
                    return;
                }

                lecturerHelper lecHelper = new lecturerHelper(lname, lmoduleCode, llocation, lemail, lcontactNumber);
                if (mDatabase != null) {
                    mDatabase.child(lname).setValue(lecHelper);
                    Toast.makeText(Add_Lecturers.this, "Insert Successful.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), List_Lecturers.class));
                } else {
                    Toast.makeText(Add_Lecturers.this, "Error !", Toast.LENGTH_SHORT).show();
                }

                //uploadDataToFirebase();
            }

        });

    }
}