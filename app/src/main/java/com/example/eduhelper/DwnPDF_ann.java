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

public class DwnPDF_ann extends AppCompatActivity {

    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dwn_pdf_ann);
        announcementHelper AHelper = new announcementHelper();

        String fileName = System.currentTimeMillis()+"";
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("announcementPDF");
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                try {
                    String filename = dataSnapshot.getKey();
                    String url = dataSnapshot.getValue(String.class);

                    ((Ann_pdfAdapter) rv.getAdapter()).updtPDF(filename, url);
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

        rv = findViewById(R.id.pdfrecycleann);

        rv.setLayoutManager(new LinearLayoutManager(DwnPDF_ann.this));
        Ann_pdfAdapter ann_pdfAdapter = new Ann_pdfAdapter(rv, DwnPDF_ann.this, new ArrayList<String>(), new ArrayList<String>());
        rv.setAdapter(ann_pdfAdapter);
    }
}

