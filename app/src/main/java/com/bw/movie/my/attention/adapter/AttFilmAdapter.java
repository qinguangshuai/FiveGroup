package com.bw.movie.my.attention.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.base.BaseRecyclerAdapter;
import com.bw.movie.my.attcinema.bean.AttCinemaUser;
import com.bw.movie.my.attention.bean.MyAttFilmUser;
import com.bw.movie.my.attention.bean.ResultBean;
import com.bw.movie.my.mysound.MySoundAdapter;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * date:2018/12/28    15:33
 * author:Therefore(Lenovo)
 * fileName:AttCinemaAdapter
 */
public class AttFilmAdapter extends BaseRecyclerAdapter<AttFilmAdapter.MyViewHolder,ResultBean> {

    private Context mContext;
    private List<ResultBean> mList;

    public AttFilmAdapter(List listData, Context context) {
        super(listData, context);
        mContext = context;
        mList = listData;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.attcinema, viewGroup, false);
        MyViewHolder holder = new MyViewHolder(view,mHttpClick);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        ResultBean bean = mList.get(i);
        String imageUrl = bean.getImageUrl();
        if (imageUrl==null){
            int launcher = R.mipmap.ic_launcher;
            String s = String.valueOf(launcher);
            ((MyViewHolder)holder).simple.setImageURI(s);
        }else {
            Uri uri = Uri.parse(bean.getImageUrl());
            ((MyViewHolder)holder).simple.setImageURI(uri);
        }
        ((MyViewHolder)holder).text1.setText(bean.getName());
        ((MyViewHolder)holder).text2.setText(bean.getSummary());
        long browseTime = bean.getReleaseTime();
        GregorianCalendar gc = new GregorianCalendar();
        String s = String.valueOf(browseTime);
        gc.setTimeInMillis(Long.parseLong(s));
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        ((MyViewHolder)holder).text3.setText(df.format(gc.getTime()));
    }



    @Override
    public int getItemCount() {
        return mList==null?0:mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        SimpleDraweeView simple;
        TextView text1,text2,text3;
        ImageView attCinemaImage;

        public MyViewHolder(@NonNull View itemView, final HttpClick httpClick) {
            super(itemView);
            simple = itemView.findViewById(R.id.attcinemasimple);
            text1 = itemView.findViewById(R.id.attcinematext1);
            text2 = itemView.findViewById(R.id.attcinematext2);
            text3 = itemView.findViewById(R.id.attcinematext3);
            attCinemaImage = itemView.findViewById(R.id.attCinemaImage);
            attCinemaImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    httpClick.getClick(v,getAdapterPosition());
                }
            });
        }
    }

    private HttpClick mHttpClick;

    public void setHttpClick(HttpClick httpClick) {
        mHttpClick = httpClick;
    }

    public interface HttpClick{
        void getClick(View view,int position);
    }
}
