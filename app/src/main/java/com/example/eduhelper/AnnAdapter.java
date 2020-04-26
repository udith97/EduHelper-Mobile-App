
package com.example.eduhelper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AnnAdapter extends RecyclerView.Adapter<AnnAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<announcementHelper> announcementData;

    public AnnAdapter(Context c, ArrayList<announcementHelper> announcementData){
        this.context = c;
        this.announcementData = announcementData;
    }

    @NonNull
    @Override
    public AnnAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ann_card_view,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnnAdapter.MyViewHolder holder, int position) {

        final announcementHelper announcementData = this.announcementData.get(position);

        holder.Atitle.setText(announcementData.getTitle());
        holder.Afac.setText(announcementData.getFaculty());
        holder.Ayr.setText(announcementData.getYear());
        holder.Ades.setText(announcementData.getDescription());
    }

    @Override
    public int getItemCount() {
        return announcementData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Atitle,Afac,Ayr,Ades;
        CardView cardView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            Atitle = itemView.findViewById(R.id.titleAnn);
            Afac = itemView.findViewById(R.id.facAnn);
            Ayr = itemView.findViewById(R.id.yrAnn);
            Ades = itemView.findViewById(R.id.desAnn);
            cardView = itemView.findViewById(R.id.cv1);
        }
    }
}

