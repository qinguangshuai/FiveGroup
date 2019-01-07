package com.bw.movie.film.adapter;

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
import com.bw.movie.film.bean.CommentBean;
import com.bw.movie.film.event.PraiseEvent;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/*
 *作者:ash
 *TODO:
 *
 */
public class PopupWindow4Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<CommentBean.ResultBean> result = new ArrayList<>();

    public void setResult(List<CommentBean.ResultBean> result) {
        this.result = result;
    }
    class Holder extends RecyclerView.ViewHolder {
        private final SimpleDraweeView mDraweeView;
        private final TextView mName;
        private final TextView mTime;
        private final TextView mContext;
        private final CheckBox mGood;
        private final CheckBox mComment;

        public Holder(View view) {
            super(view);
            mDraweeView = view.findViewById(R.id.img_item_comment);
            mName = view.findViewById(R.id.name_item_comment);
            mContext = view.findViewById(R.id.context_item_comment);
            mTime = view.findViewById(R.id.time_item_comment);
            mGood = view.findViewById(R.id.good_item_comment);
            mComment = view.findViewById(R.id.comment_item_comment);
        }

        public void setData(final CommentBean.ResultBean resultBean) {
            mDraweeView.setImageURI(Uri.parse(resultBean.getCommentHeadPic()));
            mName.setText(resultBean.getCommentUserName());
            long browseTime = resultBean.getCommentTime();
            GregorianCalendar gc = new GregorianCalendar();
            String s = String.valueOf(browseTime);
            gc.setTimeInMillis(Long.parseLong(s));
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            mTime.setText(df.format(gc.getTime()));
            mGood.setText(resultBean.getGreatNum() + "");
            mGood.setChecked(resultBean.getIsGreat()==0?false:true);
            mComment.setText(resultBean.getReplyNum() + "");
            mContext.setText(resultBean.getCommentContent() + "");


            //点赞
            mGood.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        mGood.setClickable(false);
                        EventBus.getDefault().post(new PraiseEvent(resultBean.getCommentId()));
                    }else {
                        EventBus.getDefault().post(new PraiseEvent(resultBean.getCommentId()));
                    }
                }
            });

            mGood.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mGood.isChecked()){
                        mGood.setText((resultBean.getGreatNum()+1)+"");
                    }else {
                        mGood.setText((resultBean.getGreatNum()+1)+"");
                    }
                }
            });


        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_popcomment, viewGroup, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        final Holder holder = (Holder) viewHolder;
        holder.setData(result.get(i));


    }

    @Override
    public int getItemCount() {
        return result.size();
    }
}
