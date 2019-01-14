package com.bw.movie.cinema.mevaluate.adapder;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.BaseRecyclerAdapter;
import com.bw.movie.cinema.event.GoodEvent;
import com.bw.movie.cinema.good.bean.GoodBean;
import com.bw.movie.cinema.good.presenter.GoodPresenter;
import com.bw.movie.cinema.good.view.GoodView;
import com.bw.movie.cinema.mevaluate.bean.MevaResultBean;
import com.bw.movie.cinema.mevaluate.bean.MevaluateBean;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * date:2018/12/29    8:41
 * author:张文龙(张文龙)
 * fileName:MevaluateAdapder
 */
public class MevaluateAdapder extends BaseRecyclerAdapter<MevaluateAdapder.MevaulateteViewHolder,MevaResultBean> {
    private List<MevaResultBean> list;
    private Context mContext;

    public MevaluateAdapder(List<MevaResultBean> listData, Context context) {
        super(listData, context);
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
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int i) {
        if (list.get(i).getIsGreat() == 1) {
            ((MevaulateteViewHolder)holder).checkBox.setButtonDrawable(R.drawable.com_icon_praise_selected_hdpi);
            ((MevaulateteViewHolder)holder).checkBox.setChecked(true);
        } else {
            ((MevaulateteViewHolder)holder).checkBox.setButtonDrawable(R.drawable.com_icon_praise_default_hdpi);
            ((MevaulateteViewHolder)holder).checkBox.setChecked(false);
        }
        ((MevaulateteViewHolder)holder).textViewname.setText(list.get(i).getCommentUserName());
        ((MevaulateteViewHolder)holder).textViewconnect.setText(list.get(i).getCommentContent());
        ((MevaulateteViewHolder)holder).simpleDraweeView.setImageURI(Uri.parse(list.get(i).getCommentHeadPic()));
        long commentTime = list.get(i).getCommentTime();
        GregorianCalendar gc = new GregorianCalendar();
        String s = String.valueOf(commentTime);
        gc.setTimeInMillis(Long.parseLong(s));
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        ((MevaulateteViewHolder)holder).textViewtime.setText(df.format(gc.getTime()));
        ((MevaulateteViewHolder)holder).textViewmevalue.setText(list.get(i).getGreatNum() + "");

        ((MevaulateteViewHolder)holder).checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                EventBus.getDefault().post(new GoodEvent(isChecked,((MevaulateteViewHolder)holder).checkBox,i));
            }
        });
    }

    public void good(){

    }



    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }


    class MevaulateteViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView simpleDraweeView;
        TextView textViewname;
        TextView textViewconnect;
        TextView textViewtime;
        TextView textViewmevalue;
        CheckBox checkBox;

        public MevaulateteViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewname = itemView.findViewById(R.id.mevaluatename);
            textViewconnect = itemView.findViewById(R.id.mevaluateconnect);
            textViewtime = itemView.findViewById(R.id.mevaluatetime);
            simpleDraweeView = itemView.findViewById(R.id.mevaluateimage);
            textViewmevalue = itemView.findViewById(R.id.mevaltext);
            checkBox = itemView.findViewById(R.id.mevalchaeckbox);
        }
    }


}
