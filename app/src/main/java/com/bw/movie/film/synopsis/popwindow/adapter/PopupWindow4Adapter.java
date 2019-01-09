package com.bw.movie.film.synopsis.popwindow.adapter;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.film.synopsis.bean.CommentBean;
import com.bw.movie.film.event.PraiseEvent;
import com.bw.movie.film.synopsis.bean.ResultBean;
import com.bw.movie.util.EmptyUtil;
import com.bw.movie.util.ToastUtil;
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

    private EmptyUtil emptyUtil = new EmptyUtil();

    private ToastUtil toast = new ToastUtil();

    private List<ResultBean> result = new ArrayList<>();


    public void setResult(List< ResultBean> result) {
        this.result = result;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void addResult(List<ResultBean> result) {
        if (emptyUtil.isNull(result)) {
            toast.Toast("没更多了,官人明天来~");
        } else {
            this.result.addAll(result);
        }
    }


    class Holder extends RecyclerView.ViewHolder {
        private final SimpleDraweeView mDraweeView;
        private final TextView mName;
        private final TextView mTime;
        private final TextView mContext;
        private final RadioButton mGood;
        private final CheckBox mComment;

        public Holder(View view) {
            super(view);
            mDraweeView = view.findViewById(R.id.img_item_comment);
            mName = view.findViewById(R.id.name_item_comment);
            mContext = view.findViewById(R.id.context_item_comment);
            mTime = view.findViewById(R.id.time_item_comment);
            mGood = view.findViewById(R.id.good_item_comment);
            mComment = view.findViewById(R.id.comment_item_comment);

            mComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getData.isData(v, getAdapterPosition());
                }
            });
        }

        public void setData(final  ResultBean resultBean) {
            mDraweeView.setImageURI(Uri.parse(resultBean.getCommentHeadPic()));
            mName.setText(resultBean.getCommentUserName());
            long browseTime = resultBean.getCommentTime();
            GregorianCalendar gc = new GregorianCalendar();
            String s = String.valueOf(browseTime);
            gc.setTimeInMillis(Long.parseLong(s));
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            mTime.setText(df.format(gc.getTime()));
            mGood.setText(resultBean.getGreatNum() + "");
            mGood.setChecked(resultBean.getIsGreat() == 0 ? false : true);
            mComment.setText(resultBean.getReplyNum() + "");
            mGood.setText(resultBean.getGreatNum() + "");
            mComment.setText(resultBean.getReplyNum() + "");
            mContext.setText(resultBean.getCommentContent() + "");


        }
    }

    private getData getData;

    public void setGetData(PopupWindow4Adapter.getData getData) {
        this.getData = getData;
    }

    public interface getData {
        void isData(View view, int position);
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
        final  ResultBean resultBean = result.get(i);
        holder.setData(resultBean);

        //点击 点赞
        holder.mGood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new PraiseEvent(resultBean.getCommentId(), holder.mGood, resultBean.getGreatNum(), i));
            }
        });

    }

    @Override
    public int getItemCount() {
        if (emptyUtil.isNull(result) == false) {
            return result.size();
        } else {
            return 0;
        }
    }


}
