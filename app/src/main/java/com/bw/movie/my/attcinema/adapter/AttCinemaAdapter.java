package com.bw.movie.my.attcinema.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.my.attcinema.bean.AttCinemaUser;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * date:2018/12/28    15:33
 * author:Therefore(Lenovo)
 * fileName:AttCinemaAdapter
 */
public class AttCinemaAdapter extends RecyclerView.Adapter<AttCinemaAdapter.MyViewHolder> {

    private Context mContext;
    private List<AttCinemaUser.ResultBean> mList;

    public AttCinemaAdapter(Context context, List<AttCinemaUser.ResultBean> list) {
        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.attcinema, viewGroup, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        AttCinemaUser.ResultBean bean = mList.get(i);
        Uri uri = Uri.parse(bean.getLogo());
        myViewHolder.simple.setImageURI(uri);
        myViewHolder.text1.setText(bean.getName());
        myViewHolder.text2.setText(bean.getAddress());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        SimpleDraweeView simple;
        TextView text1,text2,text3;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            simple = itemView.findViewById(R.id.attcinemasimple);
            text1 = itemView.findViewById(R.id.attcinematext1);
            text2 = itemView.findViewById(R.id.attcinematext2);
            text3 = itemView.findViewById(R.id.attcinematext3);
        }
    }
}
