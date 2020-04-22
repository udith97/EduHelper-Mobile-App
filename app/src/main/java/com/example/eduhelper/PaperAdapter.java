package com.example.eduhelper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
        PastpaperHelper pastpaperHelper = this.userData.get(position);
        holder.pyear.setText(pastpaperHelper.getYear());
        holder.pMcode.setText(pastpaperHelper.getModuleCode());
        holder.psem.setText(pastpaperHelper.getSemester());
        holder.pfac.setText(pastpaperHelper.getFaculty());
        holder.pexam.setText(pastpaperHelper.getExam());
    }

    @Override
    public int getItemCount() {
        return userData.size();
    }

    public class PaperHolder extends  RecyclerView.ViewHolder{

        TextView pyear, pMcode,psem, pfac, pexam;

        public PaperHolder(@NonNull View itemView) {
            super(itemView);

            pyear = itemView.findViewById(R.id.pyear_id);
            pMcode = itemView.findViewById(R.id.module_C);
            psem = itemView.findViewById(R.id.vsem);
            pfac = itemView.findViewById(R.id.vfac);
            pexam = itemView.findViewById(R.id.vexam);

        }
    }
}
