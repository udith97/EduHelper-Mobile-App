package com.example.eduhelper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RetrivePastpaperData extends AppCompatActivity {

    private RecyclerView recyclerViewp;
    private ArrayList<PastpaperHelper> paperdata;
    private PaperAdapter paperAdapter;
    private Button inputredirect, downloadpdf;

    DatabaseReference dref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrive_pastpaper_data);

        recyclerViewp= findViewById(R.id.recu);
        recyclerViewp.setLayoutManager(new LinearLayoutManager(this));
        paperdata = new ArrayList<PastpaperHelper>();
        inputredirect = findViewById(R.id.redirectbtn);
        downloadpdf = findViewById(R.id.paperdown);

        dref = FirebaseDatabase.getInstance().getReference().child("pastPapers");
        dref.addListenerForSingleValueEvent(valueEventListener);


        inputredirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Insert_P_Paper.class));
            }
        });

        downloadpdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RetrivePastpaperData.this, DownpdfPaper.class));
            }
        });


    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                PastpaperHelper helpc = dataSnapshot1.getValue(PastpaperHelper.class);
                paperdata.add(helpc);
            }
            paperAdapter = new PaperAdapter(RetrivePastpaperData.this,paperdata);
            recyclerViewp.setAdapter(paperAdapter);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };
}
