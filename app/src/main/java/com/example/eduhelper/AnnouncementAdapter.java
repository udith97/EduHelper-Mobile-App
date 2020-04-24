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
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AnnouncementAdapter extends RecyclerView.Adapter<AnnouncementAdapter.MyViewHolder>{
    private Context context;
    private ArrayList<announcementHelper> announcementHelpers;

    public AnnouncementAdapter(Context c,ArrayList<announcementHelper> announcementHelpers){
        this.context = c;
        this.announcementHelpers = announcementHelpers;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ann_card,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        announcementHelper announcementHelpers = this.announcementHelpers.get(position);

        holder.viewTitle.setText(announcementHelpers.getTitle());
        holder.viewFaculty.setText(announcementHelpers.getFaculty());
        holder.viewYear.setText(announcementHelpers.getYear());
        holder.viewDes.setText(announcementHelpers.getDescription());


    }

    @Override
    public int getItemCount() {
        return announcementHelpers.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
       TextView viewTitle, viewFaculty, viewYear, viewDes;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            viewTitle = itemView.findViewById(R.id.anntitle);
            viewFaculty = itemView.findViewById(R.id.annfac);
            viewYear = itemView.findViewById(R.id.annyr);
            viewDes = itemView.findViewById(R.id.anndes);

        }
    }
}
