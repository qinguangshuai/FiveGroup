package com.bw.movie.my.mysound;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.base.BaseRecyclerAdapter;
import com.bw.movie.my.attention.bean.MyAttFilmUser;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * date:2018/12/28    15:33
 * author:Therefore(Lenovo)
 * fileName:AttCinemaAdapter
 */
public class MySoundAdapter extends BaseRecyclerAdapter<MySoundAdapter.MyViewHolder,ResultBean> {

    private Context mContext;
    private List<ResultBean> mList;

    public MySoundAdapter(List<ResultBean> listData, Context context) {
        super(listData, context);
        mContext = context;
        mList = listData;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.mysound, viewGroup, false);
        MyViewHolder holder = new MyViewHolder(view,mHttpClick);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        ResultBean bean = mList.get(i);
        ((MyViewHolder)holder).text1.setText(bean.getTitle());
        ((MyViewHolder)holder).text2.setText(bean.getContent());
        long browseTime = bean.getPushTime();
        GregorianCalendar gc = new GregorianCalendar();
        String s = String.valueOf(browseTime);
        gc.setTimeInMillis(Long.parseLong(s));
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        ((MyViewHolder)holder).text3.setText(df.format(gc.getTime()));
        if (bean.getStatus()==0){
            ((MyViewHolder)holder).text4.setVisibility(View.VISIBLE);
            ((MyViewHolder)holder).text4.setText("1");
        }else {
            ((MyViewHolder)holder).text4.setVisibility(View.GONE);
        }
    }



    @Override
    public int getItemCount() {
        return mList==null?0:mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView text1,text2,text3;
        RadioButton text4;

        public MyViewHolder(@NonNull View itemView, final HttpClick httpClick) {
            super(itemView);
            text1 = itemView.findViewById(R.id.soundtext1);
            text2 = itemView.findViewById(R.id.soundtext2);
            text3 = itemView.findViewById(R.id.soundtext3);
            text4 = itemView.findViewById(R.id.soundtext4);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    httpClick.getClick(v,getAdapterPosition());
                    text4.setVisibility(View.GONE);
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
