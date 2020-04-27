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

public class Update_announcement extends AppCompatActivity {
    EditText u_title,u_fac,u_yr,u_des;
    Button UpdateBtn;
    String title,faculty, year,description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_announcement);

        u_title = findViewById(R.id.u_title);
        u_fac = findViewById(R.id.u_fac);
        u_yr =findViewById(R.id.u_yr);
        u_des = findViewById(R.id.u_des);
        UpdateBtn = findViewById(R.id.u_update);

        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        faculty = intent.getStringExtra("faculty");
        year = intent.getStringExtra("year");
        description = intent.getStringExtra("description");



        u_title.setText(title);
        u_fac.setText(faculty);
        u_yr.setText(year);
        u_des.setText(description);

        UpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("announcement").child(title);
                String utitle,ufac,uyr,udes ;

                utitle = u_title.getText().toString();
                ufac = u_fac.getText().toString();
                uyr = u_yr.getText().toString();
                udes = u_des.getText().toString();


                announcementHelper announcementHelper = new announcementHelper(utitle,ufac,uyr,udes);
                databaseReference.setValue(announcementHelper);
                Toast.makeText(Update_announcement.this,"Announcements Updated.",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),announcement_list.class));
            }
        });

    }
}
