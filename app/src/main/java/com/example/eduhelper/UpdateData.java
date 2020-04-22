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

public class UpdateData extends AppCompatActivity {
    EditText eName, eMCode, eLocation, eEmail, eContact;
    Button UpdateBtn;
    String name, moduleCode, location, email, contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);

        eName = findViewById(R.id.ename);
        eMCode = findViewById(R.id.emoduleCode);
        eLocation =findViewById(R.id.elocation);
        eEmail = findViewById(R.id.eemail);
        eContact = findViewById(R.id.econtactNumber);
        UpdateBtn = findViewById(R.id.update);

        Intent intent = getIntent();
        name = intent.getStringExtra("lname");
        moduleCode = intent.getStringExtra("lmoduleCode");
        location = intent.getStringExtra("llocation");
        email = intent.getStringExtra("lemail");
        contact = intent.getStringExtra("lcontactNumber");

        eName.setText(name);
        eMCode.setText(moduleCode);
        eLocation.setText(location);
        eEmail.setText(email);
        eContact.setText(contact);

        UpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("lecturer").child(name);
                String uname, umoduleCode, ulocation, uemail, ucontact;

                uname = eName.getText().toString();
                umoduleCode = eMCode.getText().toString();
                ulocation = eLocation.getText().toString();
                uemail = eEmail.getText().toString();
                ucontact = eContact.getText().toString();


                lecturerHelper lecturerHelper = new lecturerHelper(uname, umoduleCode, ulocation, uemail, ucontact);
                databaseReference.setValue(lecturerHelper);
                Toast.makeText(UpdateData.this,"Lecturer Updated.",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), List_Lecturers.class));
            }
        });

    }
}
