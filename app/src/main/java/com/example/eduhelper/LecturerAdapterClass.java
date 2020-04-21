package com.example.eduhelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LecturerAdapterClass extends RecyclerView.Adapter<LecturerAdapterClass.MyViewHolder>{
    ArrayList<lecturerHelper> list;
    public LecturerAdapterClass(ArrayList<lecturerHelper> list){
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lecturer_card_holder,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.viweName.setText(list.get(i).getName());
        myViewHolder.viewMCode.setText(list.get(i).getModuleCode());
        myViewHolder.viewLoc.setText(list.get(i).getLocation());
        myViewHolder.viewMail.setText(list.get(i).getEmail());
        myViewHolder.viewCont.setText(list.get(i).getContact());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView viweName, viewMCode, viewLoc, viewMail, viewCont;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            viweName = itemView.findViewById(R.id.lecname);
            viewMCode = itemView.findViewById(R.id.mcode);
            viewLoc =itemView.findViewById(R.id.loc);
            viewMail = itemView.findViewById(R.id.mail);
            viewCont = itemView.findViewById(R.id.cont);


        }
    }
}
