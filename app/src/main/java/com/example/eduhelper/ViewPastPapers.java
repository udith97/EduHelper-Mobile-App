package com.example.eduhelper;


import android.os.Bundle;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;



public class ViewPastPapers extends AppCompatActivity {

     DatabaseReference mDatabase;
     ArrayList<PastpaperHelper> list;
     RecyclerView recyclerView;
     SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_past_papers);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("pastPapers").child("year");
        recyclerView = findViewById(R.id.vppviewR);
        searchView = findViewById(R.id.searchView);

    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mDatabase != null){
            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()){
                        list = new ArrayList<>();
                        for (DataSnapshot x : dataSnapshot.getChildren()){
                            list.add(x.getValue(PastpaperHelper.class));
                        }

                        ViewPastpaperAdapter adapter = new ViewPastpaperAdapter(list);
                        recyclerView.setAdapter(adapter);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(ViewPastPapers.this, databaseError.getMessage(), Toast.LENGTH_SHORT);
                }
            });
        }

        if (searchView != null){
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {

                    search(newText);

                    return false;
                }
            });
        }

    }

    private void search( String str){
        ArrayList<PastpaperHelper> Plist = new ArrayList<>();
        for(PastpaperHelper object : Plist){
            if (object.getModuleCode().toLowerCase().contains(str.toLowerCase())){
                Plist.add(object);
            }
        }

        ViewPastpaperAdapter adapterClass = new ViewPastpaperAdapter(Plist);
        recyclerView.setAdapter(adapterClass);
    }
}
