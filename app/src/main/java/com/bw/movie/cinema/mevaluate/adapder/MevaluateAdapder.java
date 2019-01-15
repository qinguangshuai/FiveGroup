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

import com.bw.movie.R;
import com.bw.movie.base.BaseRecyclerAdapter;
import com.bw.movie.cinema.event.GoodEvent;
import com.bw.movie.cinema.mevaluate.bean.MevaResultBean;
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
    private List<MevaResultBean> mList;
    private Context mContext;

    public MevaluateAdapder(List listData, Context context) {
        super(listData, context);
        this.mList = listData;
        this.mContext = context;
    }



    @NonNull
    @Override
    public MevaulateteViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.mevaluateitem, viewGroup, false);
        return new MevaulateteViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int i) {
        if (mList.get(i).getIsGreat() == 1) {
            ((MevaulateteViewHolder)holder).checkBox.setButtonDrawable(R.drawable.com_icon_praise_selected_hdpi);
            ((MevaulateteViewHolder)holder).checkBox.setChecked(true);
        } else {
            ((MevaulateteViewHolder)holder).checkBox.setButtonDrawable(R.drawable.com_icon_praise_default_hdpi);
            ((MevaulateteViewHolder)holder).checkBox.setChecked(false);
        }
        ((MevaulateteViewHolder)holder).textViewname.setText(mList.get(i).getCommentUserName());
        ((MevaulateteViewHolder)holder).textViewconnect.setText(mList.get(i).getCommentContent());
        ((MevaulateteViewHolder)holder).simpleDraweeView.setImageURI(Uri.parse(mList.get(i).getCommentHeadPic()));
        long commentTime = mList.get(i).getCommentTime();
        GregorianCalendar gc = new GregorianCalendar();
        String s = String.valueOf(commentTime);
        gc.setTimeInMillis(Long.parseLong(s));
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        ((MevaulateteViewHolder)holder).textViewtime.setText(df.format(gc.getTime()));
        ((MevaulateteViewHolder)holder).textViewmevalue.setText(mList.get(i).getGreatNum() + "");

        ((MevaulateteViewHolder)holder).checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                EventBus.getDefault().post(new GoodEvent(isChecked,((MevaulateteViewHolder)holder).checkBox,i));
            }
        });
    }

 /*   @Override
    public void onBindViewHolder(@NonNull final MevaulateteViewHolder mevaulateteViewHolder, final int i) {

    }*/

    public void good(){

    }



    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
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
