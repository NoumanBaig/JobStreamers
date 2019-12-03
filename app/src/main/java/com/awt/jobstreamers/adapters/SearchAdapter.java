package com.awt.jobstreamers.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.awt.jobstreamers.R;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    Context mContext;
    public SearchAdapter(Context context) {
        this.mContext=context;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.cell_search,parent,false);
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
        TextView textView_company,textView_jobType,textView_jobRole;
        Button button_applied,button_applyNow;
        ImageView imageView;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_company = itemView.findViewById(R.id.txt_company_name);
            textView_jobType = itemView.findViewById(R.id.txt_job_type);
            textView_jobRole = itemView.findViewById(R.id.txt_job_role);
            button_applied = itemView.findViewById(R.id.btn_applied);
            button_applyNow = itemView.findViewById(R.id.btn_applyNow);
            imageView = itemView.findViewById(R.id.img);
        }
    }
}
