package com.example.eduhelper;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class PaperAdapter extends RecyclerView.Adapter<PaperAdapter.PaperHolder>{

    private Context context;
    private ArrayList<PastpaperHelper> userData;

    public PaperAdapter(Context c, ArrayList<PastpaperHelper> userData){
        this.context = c;
        this.userData = userData;
    }

    @NonNull
    @Override
    public PaperHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.papers_list,parent,false);

        return new PaperHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PaperHolder holder, int position) {
        final PastpaperHelper pastpaperHelper = this.userData.get(position);
        holder.pyear.setText(pastpaperHelper.getYear());
        holder.pMcode.setText(pastpaperHelper.getModuleCode());
        holder.psem.setText(pastpaperHelper.getSemester());
        holder.pfac.setText(pastpaperHelper.getFaculty());
        holder.pexam.setText(pastpaperHelper.getExam());

        holder.deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("pastPapers").child(pastpaperHelper.getYear());
                databaseReference.removeValue();
                Toast.makeText(context,"Record deleted", Toast.LENGTH_LONG).show();

            }
        });

        holder.uploadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x = new Intent(v.getContext(), PaperUpdate.class);
                x.putExtra("year", pastpaperHelper.getYear() );
                x.putExtra("moduleCode", pastpaperHelper.getModuleCode());
                x.putExtra("semester", pastpaperHelper.getSemester());
                x.putExtra("faculty", pastpaperHelper.getFaculty());
                x.putExtra("exam", pastpaperHelper.getExam());

                v.getContext().startActivity(x);
            }
        });

    }

    @Override
    public int getItemCount() {
        return userData.size();
    }

    public class PaperHolder extends  RecyclerView.ViewHolder{

        TextView pyear, pMcode,psem, pfac, pexam;
        Button deletebtn, uploadbtn;
        CardView cardView;

        public PaperHolder(@NonNull View itemView) {
            super(itemView);

            pyear = itemView.findViewById(R.id.pyear_id);
            pMcode = itemView.findViewById(R.id.module_C);
            psem = itemView.findViewById(R.id.vsem);
            pfac = itemView.findViewById(R.id.vfac);
            pexam = itemView.findViewById(R.id.vexam);
            deletebtn = itemView.findViewById(R.id.vdeletebtn);
            cardView = itemView.findViewById(R.id.holderview);
            uploadbtn = itemView.findViewById(R.id.vupdate);

        }
    }
}
