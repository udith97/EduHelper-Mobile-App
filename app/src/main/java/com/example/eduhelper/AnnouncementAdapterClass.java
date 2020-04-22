package com.example.eduhelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AnnouncementAdapterClass extends RecyclerView.Adapter<AnnouncementAdapterClass.MyViewHolder1>{
    ArrayList<announcementHelper> list1;
    public AnnouncementAdapterClass(ArrayList<announcementHelper> list1){
        this.list1 = list1;
    }

    @NonNull
    @Override
    public MyViewHolder1 onCreateViewHolder(@NonNull ViewGroup viewGroup1, int a) {
        View view = LayoutInflater.from(viewGroup1.getContext()).inflate(R.layout.ann_card,viewGroup1,false);
        return new MyViewHolder1(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder1 myViewHolder1, int a) {
        myViewHolder1.viewTitle.setText(list1.get(a).getTitle());
        myViewHolder1.viewFac.setText(list1.get(a).getFaculty());
        myViewHolder1.viewYr.setText(list1.get(a).getYear());
        myViewHolder1.viewDes.setText(list1.get(a).getDescription());
    }

    @Override
    public int getItemCount() {
        return list1.size();
    }

    class MyViewHolder1 extends RecyclerView.ViewHolder{
        TextView viewTitle, viewFac, viewYr, viewDes;
        public MyViewHolder1(@NonNull View itemView1){
            super(itemView1);

            viewTitle = itemView1.findViewById(R.id.Anntitle);
            viewFac = itemView1.findViewById(R.id.Annfac);
            viewYr = itemView1.findViewById(R.id.Annyr);
            viewDes = itemView1.findViewById(R.id.Anndes);
        }
    }
}
