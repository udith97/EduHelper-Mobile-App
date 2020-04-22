package com.example.eduhelper;

import android.os.Bundle;
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
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_timetables);


        recyclerView = findViewById(R.id.rv1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        thelper = new ArrayList<timetablehelper>();

        ref = FirebaseDatabase.getInstance().getReference().child("timetables");
        ref.addListenerForSingleValueEvent(valueEventListener);

        searchView = findViewById(R.id.searchView);
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
