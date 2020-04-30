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
    DatabaseReference databaseReference;



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
        holder.psem.setText(pastpaperHelper.getSemester());
        holder.pMcode.setText(pastpaperHelper.getModuleCode());
        holder.pfac.setText(pastpaperHelper.getFaculty());
        holder.pexam.setText(pastpaperHelper.getExam());

        holder.deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference = FirebaseDatabase.getInstance().getReference("pastPapers").child(pastpaperHelper.getYear());
                databaseReference.removeValue();
                Toast.makeText(context,"Record deleted", Toast.LENGTH_LONG).show();

            }
        });

        holder.updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x = new Intent(v.getContext(), PaperUpdate.class);
                x.putExtra("iyear", pastpaperHelper.getYear() );
                x.putExtra("mcode", pastpaperHelper.getModuleCode());
                x.putExtra("sem", pastpaperHelper.getSemester());
                x.putExtra("ifac", pastpaperHelper.getFaculty());
                x.putExtra("iexam", pastpaperHelper.getExam());

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
        Button deletebtn, updatebtn , viewbtnpp;
        CardView cardView;

        public PaperHolder(@NonNull View itemView) {
            super(itemView);


            pyear = itemView.findViewById(R.id.pyear_id);
            psem = itemView.findViewById(R.id.vsem);
            pMcode = itemView.findViewById(R.id.module_C);
            pexam = itemView.findViewById(R.id.vexam);
            pfac = itemView.findViewById(R.id.vfac);
            deletebtn = itemView.findViewById(R.id.vdeletebtn);
            cardView = itemView.findViewById(R.id.holderview);
            updatebtn = itemView.findViewById(R.id.vupdate);


            viewbtnpp = itemView.findViewById(R.id.upbtnpp);

        }
    }

}


