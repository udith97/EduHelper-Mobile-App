package com.example.eduhelper;

import android.os.Bundle;

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

public class announcement_list extends AppCompatActivity {
    private RecyclerView rv;
    private ArrayList<announcementHelper>annData;
    private AnnAdapter annAdapter;

    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement_list);

        rv = findViewById(R.id.recycle1);
        rv.setLayoutManager(new LinearLayoutManager(this));
        annData = new ArrayList<announcementHelper>();

        ref = FirebaseDatabase.getInstance().getReference().child("announcement");
        ref.addListenerForSingleValueEvent(valueEventListener);
    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                announcementHelper AHelper = dataSnapshot1.getValue(announcementHelper.class);
                annData.add(AHelper);
            }
            annAdapter = new AnnAdapter(announcement_list.this,annData);
            rv.setAdapter(annAdapter);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };
}


