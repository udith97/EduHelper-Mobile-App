package com.example.eduhelper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TimetablePDFadapter extends RecyclerView.Adapter<TimetablePDFadapter.pdftimetable> {
    RecyclerView recyclerViewpdf;
    Context context;
    ArrayList<String> pdftimetable = new ArrayList<>();
    ArrayList<String> urlPdf = new ArrayList<>();


    public void updatepPDFt(String name, String Url){
        pdftimetable.add(name);
        urlPdf.add(Url);
        notifyDataSetChanged();// refreshes recycle view automatically
    }


    public TimetablePDFadapter(RecyclerView recyclerViewpdf, Context context, ArrayList<String> pdftimetable, ArrayList<String> urlPdf) {
        this.recyclerViewpdf = recyclerViewpdf;
        this.context = context;
        this.pdftimetable = pdftimetable;
        this.urlPdf = urlPdf;
    }

    @NonNull
    @Override
    public pdftimetable onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.timetableitem, parent,false);
        return new pdftimetable(view);
    }

    @Override
    public void onBindViewHolder(@NonNull pdftimetable holder, int position) {

        holder.filename.setText(pdftimetable.get(position));
    }

    @Override
    public int getItemCount() {
        return pdftimetable.size();
    }

    public class pdftimetable extends RecyclerView.ViewHolder{

        TextView filename;

        public pdftimetable(@NonNull View itemView) {
            super(itemView);

            filename = itemView.findViewById(R.id.tfilename);
            itemView.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("IntentReset")
                @Override
                public void onClick(View view) {


                    int position = recyclerViewpdf.getChildLayoutPosition(view);
                    Intent intent = new Intent();
                    intent.setType(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(urlPdf.get(position)));
                    context.startActivity(intent);

                }
            });
        }
    }
}
