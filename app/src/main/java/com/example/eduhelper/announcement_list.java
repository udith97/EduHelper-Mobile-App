package com.example.eduhelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
    private Button insertAnnouncement,dwnloadPdf;

    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement_list);

        insertAnnouncement = findViewById(R.id.goToInsertAnn);
        dwnloadPdf = findViewById(R.id.pdfdwn);

        rv = findViewById(R.id.recycle1);
        rv.setLayoutManager(new LinearLayoutManager(this));
        annData = new ArrayList<announcementHelper>();

        ref = FirebaseDatabase.getInstance().getReference().child("announcement");
        ref.addListenerForSingleValueEvent(valueEventListener);

        dwnloadPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(announcement_list.this, DwnPDF_ann.class));
            }
        });

        insertAnnouncement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Add_announcement.class));
            }
        });

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


