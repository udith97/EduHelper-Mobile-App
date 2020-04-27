
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

public class AnnAdapter extends RecyclerView.Adapter<AnnAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<announcementHelper> annData;

    public AnnAdapter(Context c, ArrayList<announcementHelper> annData){
        this.context = c;
        this.annData = annData;
    }

    @NonNull
    @Override
    public AnnAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ann_card_view,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnnAdapter.MyViewHolder holder, int position) {

        final announcementHelper annData = this.annData.get(position);

        holder.Antitle.setText(annData.getTitle());
        holder.Anfac.setText(annData.getFaculty());
        holder.Anyr.setText(annData.getYear());
        holder.Andes.setText(annData.getDescription());

        holder.Btndele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("announcement").child(annData.getTitle());
                ref.removeValue();
                Toast.makeText(context,"Data Deleted",Toast.LENGTH_SHORT).show();
                context.startActivity(new Intent(context.getApplicationContext(), announcement_list.class));
            }
        });

        holder.Btnudate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),Update_announcement.class);
                i.putExtra("title",annData.getTitle());
                i.putExtra("faculty",annData.getFaculty());
                i.putExtra("year",annData.getYear());
                i.putExtra("description",annData.getDescription());
                v.getContext().startActivity(i);

            }
        });
    }

    @Override
    public int getItemCount() {
        return annData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Antitle,Anfac,Anyr,Andes;
        Button Btndele,Btnudate;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            Antitle = itemView.findViewById(R.id.titleAnn);
            Anfac = itemView.findViewById(R.id.facAnn);
            Anyr = itemView.findViewById(R.id.yrAnn);
            Andes = itemView.findViewById(R.id.desAnn);
            Btndele = itemView.findViewById(R.id.Btndele);
            Btnudate = itemView.findViewById(R.id.Btnudate);
        }
    }
}

