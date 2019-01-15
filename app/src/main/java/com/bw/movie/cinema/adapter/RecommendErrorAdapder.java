package com.bw.movie.cinema.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.base.BaseRecyclerAdapter;
import com.bw.movie.greenbean.GreenDaoBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class RecommendErrorAdapder extends BaseRecyclerAdapter<RecommendErrorAdapder.MyViewHolder,GreenDaoBean> {
    private List<GreenDaoBean> list;
    private Context mContext;

    public RecommendErrorAdapder(List<GreenDaoBean> listData, Context context) {
        super(listData, context);
        this.list = list;
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.neightbouritem, viewGroup, false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        ((MyViewHolder) holder).simpleDraweeView.setImageURI(Uri.parse(list.get(i).getImage()));
        ((MyViewHolder) holder).textViewaddress.setText(list.get(i).getKile());
        ((MyViewHolder) holder).textViewname.setText(list.get(i).getTitle());
    }



    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView simpleDraweeView;
        TextView textViewname;
        TextView textViewaddress;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            simpleDraweeView = itemView.findViewById(R.id.simpview);
            textViewname = itemView.findViewById(R.id.nameneighthour);
            textViewaddress = itemView.findViewById(R.id.addressneighthour);
        }
    }
}
