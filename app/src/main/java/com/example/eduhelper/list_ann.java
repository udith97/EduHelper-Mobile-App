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

public class list_ann extends AppCompatActivity {
   private RecyclerView recyclerView;
   private ArrayList<announcementHelper> announcementHelpers;
   private AnnouncementAdapter announcementAdapter;

   DatabaseReference dRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_ann);

        recyclerView = findViewById(R.id.rv2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        announcementHelpers = new ArrayList<announcementHelper>();

        dRef = FirebaseDatabase.getInstance().getReference().child("announcement");
        dRef.addListenerForSingleValueEvent(valueEventListener);


    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                announcementHelper aHelper = dataSnapshot1.getValue(announcementHelper.class);
                announcementHelpers.add(aHelper);
            }
            announcementAdapter = new AnnouncementAdapter(list_ann.this,announcementHelpers);
            recyclerView.setAdapter(announcementAdapter);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };
}
