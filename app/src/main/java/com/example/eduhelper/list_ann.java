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

public class list_ann extends AppCompatActivity {
    DatabaseReference ref;
    ArrayList<announcementHelper> list1;
    RecyclerView recyclerView;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_ann);

        ref = FirebaseDatabase.getInstance().getReference().child("announcement");
        recyclerView = findViewById(R.id.rv1);
        searchView = findViewById(R.id.searchView);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (ref != null){
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists())
                    {
                        list1 = new ArrayList<>();
                        for(DataSnapshot ds : dataSnapshot.getChildren())
                        {
                            list1.add(ds.getValue(announcementHelper.class));
                        }
                        AnnouncementAdapterClass announcementAdapterClass = new AnnouncementAdapterClass(list1);
                        recyclerView.setAdapter(announcementAdapterClass);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(list_ann.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        }
        if (searchView != null)
        {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    search(s);
                    return true;
                }
            });
        }

    }

    private void search(String str)
    {
        ArrayList<announcementHelper> myList1 = new ArrayList<>();
        for (announcementHelper object : list1)
        {
            if (object.getTitle().toLowerCase().contains(str.toLowerCase()))
            {
                myList1.add(object);
            }
        }
        AnnouncementAdapterClass announcementAdapterClass = new AnnouncementAdapterClass(myList1);
        recyclerView.setAdapter(announcementAdapterClass);

    }
}
