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

public class Ann_pdfAdapter extends RecyclerView.Adapter<Ann_pdfAdapter.pdfann> {
    RecyclerView recyclerViewpdf;
    Context context;
    ArrayList<String> pdfann = new ArrayList<>();
    ArrayList<String> urlPdf = new ArrayList<>();


    public void updtPDF(String name, String Url){
        pdfann.add(name);
        urlPdf.add(Url);
        notifyDataSetChanged();// refreshes recycle view automatically
    }


    public Ann_pdfAdapter(RecyclerView recyclerViewpdf, Context context, ArrayList<String> pdfann, ArrayList<String> urlPdf) {
        this.recyclerViewpdf = recyclerViewpdf;
        this.context = context;
        this.pdfann = pdfann;
        this.urlPdf = urlPdf;
    }

    @NonNull
    @Override
    public pdfann onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ann_item, parent,false);
        return new pdfann(view);
    }

    @Override
    public void onBindViewHolder(@NonNull pdfann holder, int position) {

        holder.filename.setText(pdfann.get(position));
    }

    @Override
    public int getItemCount() {
        return pdfann.size();
    }

    public class pdfann extends RecyclerView.ViewHolder{

        TextView filename;

        public pdfann(@NonNull View itemView) {
            super(itemView);

            filename = itemView.findViewById(R.id.afilename);
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

