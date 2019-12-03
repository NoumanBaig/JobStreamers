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

import java.util.ArrayList;

public class BlockAdapter extends RecyclerView.Adapter<BlockAdapter.SearchViewHolder> {

    Context mContext;
//    ArrayList<String> arrayList;
    public BlockAdapter(Context context) {
        this.mContext=context;
//        this.arrayList=arrayList;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.cell_block,parent,false);
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
        TextView textView;
        ImageView imageView;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
//            textView = itemView.findViewById(R.id.txt);
//            imageView = itemView.findViewById(R.id.img_close);
        }
    }

//    public void deleteItem(int index) {
//        if (arrayList.size() > index) {
//            arrayList.remove(index);
//            notifyDataSetChanged();
//        }
//    }
}
