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

public class LecturerAdapterClass extends RecyclerView.Adapter<LecturerAdapterClass.MyViewHolder>{

     Context context;
     ArrayList<lecturerHelper> lecHelper;

    public LecturerAdapterClass(Context c,ArrayList<lecturerHelper> lecHelper){
        this.context = c;
        this.lecHelper = lecHelper;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lecturer_card_holder,parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final lecturerHelper lecHelper = this.lecHelper.get(position);
        holder.viewName.setText(lecHelper.getName());
        holder.viewMCode.setText(lecHelper.getModuleCode());
        holder.viewLoc.setText(lecHelper.getLocation());
        holder.viewMail.setText(lecHelper.getEmail());
        holder.viewCont.setText(lecHelper.getContact());

        holder.DeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("lecturer").child(lecHelper.getName());
                databaseReference.removeValue();
                Toast.makeText(context, "Lecturer Deleted.",Toast.LENGTH_SHORT).show();
                context.startActivity(new Intent(context.getApplicationContext(), List_Lecturers.class));
            }
        });

        holder.UpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),UpdateData.class);
                i.putExtra("lname",lecHelper.getName());
                i.putExtra("lmoduleCode",lecHelper.getModuleCode());
                i.putExtra("llocation",lecHelper.getLocation());
                i.putExtra("lemail",lecHelper.getEmail());
                i.putExtra("lcontactNumber",lecHelper.getContact());
                v.getContext().startActivity(i);

            }
        });
    }

    @Override
    public int getItemCount() {
        return lecHelper.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView viewName, viewMCode, viewLoc, viewMail, viewCont;
        Button DeleteBtn, UpdateBtn;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            viewName = itemView.findViewById(R.id.lecname);
            viewMCode = itemView.findViewById(R.id.mcode);
            viewLoc = itemView.findViewById(R.id.loc);
            viewMail = itemView.findViewById(R.id.mail);
            viewCont = itemView.findViewById(R.id.cont);
            DeleteBtn = itemView.findViewById(R.id.deleteBtn);
            UpdateBtn = itemView.findViewById(R.id.updateBtn);

        }

    }
}