package com.example.eduhelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TimetableAdapterClass extends RecyclerView.Adapter<TimetableAdapterClass.MyViewHolder>{
    ArrayList<timetablehelper> list1;
    public TimetableAdapterClass(ArrayList<timetablehelper> list1){
        this.list1 = list1;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.timetable_card_holder,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.viwefaculty.setText(list1.get(i).getTfaculty());
        myViewHolder.viewyear.setText(list1.get(i).getTyear());
        myViewHolder.viewsemester.setText(list1.get(i).getTsemester());
        myViewHolder.viewgroup.setText(list1.get(i).getTgroup());

    }

    @Override
    public int getItemCount() {
        return list1.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView viwefaculty, viewyear, viewsemester, viewgroup;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            viwefaculty = itemView.findViewById(R.id.s1);
            viewyear = itemView.findViewById(R.id.s2);
            viewsemester =itemView.findViewById(R.id.s3);
            viewgroup = itemView.findViewById(R.id.s4);


        }
    }
}
