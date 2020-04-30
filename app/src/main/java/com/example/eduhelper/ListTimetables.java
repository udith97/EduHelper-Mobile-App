package com.example.eduhelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListTimetables extends AppCompatActivity {
    DatabaseReference ref;
    private ArrayList<timetablehelper> thelper;
    private  RecyclerView recyclerView;
    private TimetableAdapterClass timetableAdapterClass;
    private Button goInsert, downloadpdf;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_timetables);

        goInsert = findViewById(R.id.btn_ins);
        downloadpdf = findViewById(R.id.down_pdf);


        recyclerView = findViewById(R.id.rv1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        thelper = new ArrayList<timetablehelper>();

        ref = FirebaseDatabase.getInstance().getReference().child("timetables");
        ref.addListenerForSingleValueEvent(valueEventListener);

        searchView = findViewById(R.id.searchView);

       downloadpdf.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(ListTimetables.this, Downpdf_timetable.class));
           }
       });

        goInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AddTimetable.class));
            }
        });

    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                timetablehelper helper = dataSnapshot1.getValue(timetablehelper.class);
                thelper.add(helper);
            }
            timetableAdapterClass = new TimetableAdapterClass(ListTimetables.this,thelper);
            recyclerView.setAdapter(timetableAdapterClass);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };

}
