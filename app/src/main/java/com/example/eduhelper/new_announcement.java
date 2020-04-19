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

public class new_announcement extends AppCompatActivity {
    private static final int PICK_IMG_REQUEST = 1;
    private EditText title,faculty,year,description ;
    private Button submit, browse;

    private Uri fileuri;

    private FirebaseDatabase rootNode;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_announcement);

        title = findViewById(R.id.ann_title);
        faculty = findViewById(R.id.ann_faculty);
        year = findViewById(R.id.ann_year);
        description = findViewById(R.id.ann_des);




        submit = findViewById(R.id.submitBtn);
        browse = findViewById(R.id.browseBtn);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rootNode = FirebaseDatabase.getInstance();
                mDatabase = rootNode.getReference("announcement");

                String annTitle = title.getText().toString();
                int annYear = Integer.parseInt(year.getText().toString());
                String annFac = faculty.getText().toString();
                String annDes = description.getText().toString();

                announcementHelper annHelper = new announcementHelper(annTitle, annFac, annYear, annDes);
                if(mDatabase != null ) {
                    mDatabase.child(annTitle).setValue(annHelper);
                    Toast.makeText(new_announcement.this,"Insert Successful", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), Announcement.class));
                }

                else{
                    Toast.makeText(new_announcement.this,"Insert Failed!", Toast.LENGTH_LONG).show();
                }
            }
        });
        browse.setOnClickListener(new View.OnClickListener() {
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
