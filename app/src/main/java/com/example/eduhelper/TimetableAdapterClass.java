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

public class TimetableAdapterClass extends RecyclerView.Adapter<TimetableAdapterClass.MyViewHolder>{

    private ArrayList<timetablehelper> thelper;
    private Context context;


    public TimetableAdapterClass(Context c,ArrayList<timetablehelper> thelper){
        this.context = c;
        this.thelper = thelper;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.timetable_card_holder,parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final timetablehelper thelper = this.thelper.get(position);
        holder.viewFaculty.setText(thelper.getTfaculty());
        holder.viewYear.setText(thelper.getTyear());
        holder.viewSemester.setText(thelper.getTsemester());
        holder.viewGroup.setText(thelper.getTgroup());

        holder.DeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("timetables").child(thelper.getTyear());
                databaseReference.removeValue();
                Toast.makeText(context, "Timetable Deleted.",Toast.LENGTH_SHORT).show();
                context.startActivity(new Intent(context.getApplicationContext(), ListTimetables.class));
            }
        });

        holder.UpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),UpdateDataTimetable.class);
                i.putExtra("tfaculty",thelper.getTfaculty());
                i.putExtra("tyear",thelper.getTyear());
                i.putExtra("tsemester",thelper.getTsemester());
                i.putExtra("tgroup",thelper.getTgroup());
                v.getContext().startActivity(i);

            }
        });
    }

    @Override
    public int getItemCount() {return thelper.size(); }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView viewFaculty, viewYear, viewSemester, viewGroup;
        Button DeleteBtn, UpdateBtn;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            viewFaculty = itemView.findViewById(R.id.s1);
            viewYear = itemView.findViewById(R.id.s2);
            viewSemester = itemView.findViewById(R.id.s3);
            viewGroup = itemView.findViewById(R.id.s4);
            DeleteBtn = itemView.findViewById(R.id.deleteBtn);
            UpdateBtn = itemView.findViewById(R.id.updateBtn);

        }
    }
}
