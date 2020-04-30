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

public class List_Lecturers<searchView> extends AppCompatActivity {
    DatabaseReference ref;
    private ArrayList<lecturerHelper> lecHelper;
    private  RecyclerView recyclerView;
    private LecturerAdapterClass lecturerAdapterClass;
    private Button GoToInsert;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__lecturers);
        searchView = findViewById(R.id.searchView);
        GoToInsert = findViewById(R.id.goToInsertLecturer);
        recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        lecHelper = new ArrayList<lecturerHelper>();

        ref = FirebaseDatabase.getInstance().getReference().child("lecturer");
        ref.addListenerForSingleValueEvent(valueEventListener);

       searchView = findViewById(R.id.searchView);

        GoToInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Add_Lecturers.class));
            }
        });

    }


    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                lecturerHelper helper = dataSnapshot1.getValue(lecturerHelper.class);
                lecHelper.add(helper);
            }
            lecturerAdapterClass = new LecturerAdapterClass(List_Lecturers.this,lecHelper);
            recyclerView.setAdapter(lecturerAdapterClass);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }


    };


}
