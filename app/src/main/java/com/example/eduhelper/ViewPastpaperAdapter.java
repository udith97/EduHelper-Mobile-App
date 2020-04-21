package com.example.eduhelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewPastpaperAdapter extends RecyclerView.Adapter<ViewPastpaperAdapter.ViewHolderm> {

    ArrayList<Paper> list;
    public ViewPastpaperAdapter(ArrayList<Paper> list){
        this.list = list;
    }
    @NonNull
    @Override
    public ViewHolderm onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pastpaper_holder,parent,false);

        return new ViewHolderm(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderm holder, int position) {

        holder.samplehead.setText(list.get(position).getYear());
        holder.description.setText(list.get(position).getModuleCode());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolderm extends RecyclerView.ViewHolder{
        TextView samplehead, description;
        public ViewHolderm(@NonNull View itemView) {
            super(itemView);

                samplehead = itemView.findViewById(R.id.samplehead);
                description = itemView.findViewById(R.id.description);


        }
    }

}
