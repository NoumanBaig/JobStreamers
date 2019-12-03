package com.awt.jobstreamers.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.awt.jobstreamers.R;

public class ShortlistedAdapter extends RecyclerView.Adapter<ShortlistedAdapter.SearchViewHolder> {

    Context mContext;
    public ShortlistedAdapter(Context context) {
        this.mContext=context;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.cell_shortlisted,parent,false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class SearchViewHolder extends RecyclerView.ViewHolder{
        TextView textView_name,textView_company_name,textView_applied_for,textView_rounds_cleared;
        ImageView imageView,imageView_shortlisted;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_name = itemView.findViewById(R.id.txt_name);
            textView_company_name = itemView.findViewById(R.id.txt_company_name);
            textView_applied_for = itemView.findViewById(R.id.txt_applied_for);
            textView_rounds_cleared = itemView.findViewById(R.id.txt_rounds_cleared);
            imageView = itemView.findViewById(R.id.img);
            imageView = itemView.findViewById(R.id.img_shortlisted);
        }
    }
}
