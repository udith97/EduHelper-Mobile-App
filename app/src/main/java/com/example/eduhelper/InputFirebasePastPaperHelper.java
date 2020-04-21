package com.example.eduhelper;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class InputFirebasePastPaperHelper {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private List<PastpaperHelper> paper = new ArrayList<>();


    public interface DataStatus{
        void DataLoaded(List<PastpaperHelper> papers, List<String> keys);
        void DataUpdated();
        void DataDeleted();
    }

    public InputFirebasePastPaperHelper() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("pastPapers");
    }

    public void readPastPapers(final DataStatus dataStatus){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                paper.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode: dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    PastpaperHelper pPaper = keyNode.getValue(PastpaperHelper.class);
                    paper.add(pPaper);
                }

                dataStatus.DataLoaded(paper,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
