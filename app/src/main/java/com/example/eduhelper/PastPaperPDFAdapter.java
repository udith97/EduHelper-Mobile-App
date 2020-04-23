package com.example.eduhelper;

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


public class PastPaperPDFAdapter extends RecyclerView.Adapter<PastPaperPDFAdapter.ViewHolder> {

    RecyclerView recyclerView;
    Context context;
    ArrayList <String> items = new ArrayList<>();
    ArrayList<String> urlpdf = new ArrayList<>();


    public void update(String name, String url){

        items.add(name);
        urlpdf.add(url);
        notifyDataSetChanged(); // refreshes the recycler view automatically.

    }


    public PastPaperPDFAdapter(RecyclerView recyclerView, Context context, ArrayList<String> items , ArrayList<String> urlpdf) {
        this.recyclerView = recyclerView;
        this.context = context;
        this.items = items;
        this.urlpdf = urlpdf;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.pp_pdfitem,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.nameofFile.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
            TextView nameofFile;
        public ViewHolder(View itemView){
            super(itemView);

            nameofFile = itemView.findViewById(R.id.pdfFilename);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = recyclerView.getChildLayoutPosition(v);
                    Intent intent = new Intent();
                    intent.setType(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(urlpdf.get(position)));
                    context.startActivity(intent);
                }
            });
        }
    }


}
