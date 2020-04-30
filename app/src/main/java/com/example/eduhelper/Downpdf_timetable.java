package com.example.eduhelper;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Downpdf_timetable extends AppCompatActivity {

    RecyclerView rview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_downpdf_timetable);
        timetablehelper helper = new timetablehelper();

        String fileName = System.currentTimeMillis()+"";
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("TimetabelPDF");
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                try {
                    String filename = dataSnapshot.getKey();
                    String url = dataSnapshot.getValue(String.class);

                    ((TimetablePDFadapter) rview.getAdapter()).updatepPDFt(filename, url);
                }catch(Exception e){
                    System.out.println("print Error");
                }


//                String x = dataSnapshot.getKey();
//                String y = dataSnapshot.getValue(String.class);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        rview = findViewById(R.id.pdfrecycletimetable);

        rview.setLayoutManager(new LinearLayoutManager(Downpdf_timetable.this));
        TimetablePDFadapter timetablePDFadapter = new TimetablePDFadapter(rview, Downpdf_timetable.this, new ArrayList<String>(), new ArrayList<String>());
        rview.setAdapter(timetablePDFadapter);
    }
}
