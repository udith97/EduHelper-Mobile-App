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

    private RecyclerView recyclerView;
    private ArrayList<PastpaperHelper> paperdata;
    private PaperAdapter paperAdapter;
    private Button inputredirect;

    DatabaseReference dref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrive_pastpaper_data);

        recyclerView = findViewById(R.id.recu);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        paperdata = new ArrayList<PastpaperHelper>();
        inputredirect = findViewById(R.id.redirectbtn);

        dref = FirebaseDatabase.getInstance().getReference().child("pastPapers");
        dref.addListenerForSingleValueEvent(valueEventListener);


        inputredirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Insert_P_Paper.class));
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
            recyclerView.setAdapter(paperAdapter);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };
}
