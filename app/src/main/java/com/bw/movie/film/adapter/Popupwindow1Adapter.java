package com.bw.movie.film.adapter;

/*
 *作者:ash
 *TODO:
 *      第一个 popupwindow 适配器
 */

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;

public class Popupwindow1Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private String[] split;
    public void setData(String[] split) {
        this.split = split;
    }

    class Holder extends RecyclerView.ViewHolder {
        private final TextView tv;

        public Holder(View view) {
            super(view);
            tv = view.findViewById(R.id.tv_item_poprecy);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_poprecy, viewGroup, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Holder holder = (Holder) viewHolder;
        holder.tv.setText(split[i]);
    }

    @Override
    public int getItemCount() {
        return split.length;
    }

}
