package com.bw.movie.cinema.mevaluate.adapder;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.cinema.mevaluate.bean.MevaluateBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * date:2018/12/29    8:41
 * author:张文龙(张文龙)
 * fileName:MevaluateAdapder
 */
public class MevaluateAdapder extends RecyclerView.Adapter<MevaluateAdapder.MevaulateteViewHolder> {
    private List<MevaluateBean.ResultBean> list;
    private Context mContext;

    public MevaluateAdapder(List<MevaluateBean.ResultBean> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MevaulateteViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.mevaluateitem, viewGroup, false);
        return new MevaulateteViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MevaulateteViewHolder mevaulateteViewHolder, int i) {
     mevaulateteViewHolder.textViewname.setText(list.get(i).getCommentUserName());
     mevaulateteViewHolder.textViewconnect.setText(list.get(i).getCommentContent());
     mevaulateteViewHolder.simpleDraweeView.setImageURI(Uri.parse(list.get(i).getCommentHeadPic()));
        long commentTime = list.get(i).getCommentTime();
        GregorianCalendar gc = new GregorianCalendar();
        String s = String.valueOf(commentTime);
        gc.setTimeInMillis(Long.parseLong(s));
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        mevaulateteViewHolder.textViewtime.setText(df.format(gc.getTime()));
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    class MevaulateteViewHolder extends RecyclerView.ViewHolder{
       SimpleDraweeView simpleDraweeView;
       TextView textViewname;
       TextView textViewconnect;
       TextView textViewtime;
        public MevaulateteViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewname = itemView.findViewById(R.id.mevaluatename);
            textViewconnect = itemView.findViewById(R.id.mevaluateconnect);
            textViewtime = itemView.findViewById(R.id.mevaluatetime);
            simpleDraweeView = itemView.findViewById(R.id.mevaluateimage);
        }
    }
}
