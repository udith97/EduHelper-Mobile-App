package com.example.eduhelper;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class List_Lecturers extends AppCompatActivity {

    RecyclerView mRecyclerView;
    FirebaseDatabase rootNode;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__lecturers);

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        rootNode = FirebaseDatabase.getInstance();
        mDatabase = rootNode.getReference("lecturer");

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<lecturerHelper, ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<lecturerHelper, ViewHolder>(
                        lecturerHelper.class,
                        R.layout.row,
                        ViewHolder.class,
                        mDatabase
                ){
                   protected void populateViewHolder(ViewHolder viewHolder, lecturerHelper lecHelper, int position){
                       viewHolder.setDetails(getApplicationContext(), lecHelper.getName());
                   }
                };
    }
}
