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

public class PaperPDFAdapter extends RecyclerView.Adapter<PaperPDFAdapter.PdfViewpaperH> {
    RecyclerView recyclerViewpdf;
    Context context;
    ArrayList<String> pdfpapers = new ArrayList<>();
    ArrayList<String> urlPdf = new ArrayList<>();


    public void updatepdf(String name, String Url){
        pdfpapers.add(name);
        urlPdf.add(Url);
        notifyDataSetChanged();// refreshes recycle view automatically
    }


    public PaperPDFAdapter(RecyclerView recyclerViewpdf, Context context, ArrayList<String> pdfpapers, ArrayList<String> urlPdf) {
        this.recyclerViewpdf = recyclerViewpdf;
        this.context = context;
        this.pdfpapers = pdfpapers;
        this.urlPdf = urlPdf;
    }

    @NonNull
    @Override
    public PdfViewpaperH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.pastpapers_pdf, parent,false);
        return new PdfViewpaperH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PdfViewpaperH holder, int position) {

        holder.filename.setText(pdfpapers.get(position));
    }

    @Override
    public int getItemCount() {
        return pdfpapers.size();
    }

    public class PdfViewpaperH extends RecyclerView.ViewHolder{

        TextView filename;

        public PdfViewpaperH(@NonNull View itemView) {
            super(itemView);

            filename = itemView.findViewById(R.id.paperfilename);
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
