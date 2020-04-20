package com.example.eduhelper;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class InputPPFirebaseHelper {

    private FirebaseDatabase finstance;
    private DatabaseReference mDatabase;
    private List<PastpaperHelper> papers = new ArrayList<>();

    public interface DataStatus{
        void DataLoaded(List<PastpaperHelper> papers, List<String> keys);
        void DataUpdated();
        void DataDeleted();
        void DataInserted();
    }


    public InputPPFirebaseHelper(){
        finstance = FirebaseDatabase.getInstance();
        mDatabase = finstance.getReference("pastPapers");
    }

    public void readPastPapers(final DataStatus dataStatus){

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            papers.clear();
            List<String> keys = new ArrayList();
            for(DataSnapshot keyNode : dataSnapshot.getChildren()){
                keys.add(keyNode.getKey());
                PastpaperHelper pastpaper = keyNode.getValue(PastpaperHelper.class);
                papers.add(pastpaper);
            }
               dataStatus.DataLoaded(papers,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
