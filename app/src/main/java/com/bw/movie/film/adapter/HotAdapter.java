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
import com.bw.movie.film.bean.HotPlayBean;
import com.bw.movie.film.event.JumpForThreeActivityBean;
import com.bw.movie.film.event.RefreshEvent;
import com.bw.movie.util.EmptyUtil;
import com.bw.movie.util.ToastUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/*-----------
 *🖐说明:
 *   热门电影
 */

public class HotAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //吐司工具类
    private ToastUtil toast = new ToastUtil();
    //判空工具类
    private EmptyUtil emptyUtil = new EmptyUtil();
    //正在热映 数据 List
    private List<HotPlayBean.ResultBean> hotresult = new ArrayList<>();
    //正在热映 数据 set 方法
    public void setHotResult(List<HotPlayBean.ResultBean> hotresult) {
        if (emptyUtil.isNull(this.hotresult) == false) {
            this.hotresult.clear();
        }
        this.hotresult.addAll(hotresult);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_details, viewGroup, false);
        return new HotAdapter.Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        final HotPlayBean.ResultBean resultBean = hotresult.get(i);
        HotAdapter.Holder holder = (HotAdapter.Holder) viewHolder;
        holder.setData(resultBean);


        //点击跳转事件
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new JumpForThreeActivityBean(resultBean.getId()));
            }
        });

    }

    @Override
    public int getItemCount() {
        if (emptyUtil.isNull(hotresult) == false) {
            return hotresult.size();
        }
        return 0;
    }

    private class Holder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView mSimpleDraweeView;
        private final TextView mName;
        private final TextView mIntroduction;
        private final CheckBox mHart;

        public Holder(View view) {
            super(view);
            mSimpleDraweeView = view.findViewById(R.id.SimpleDraweeView_detaitem);
            mName = view.findViewById(R.id.name_detaitem);
            mIntroduction = view.findViewById(R.id.Introduction_detaitem);
            mHart = view.findViewById(R.id.hart_detaitem);
        }

        public void setData(final HotPlayBean.ResultBean resultBean) {
            mSimpleDraweeView.setImageURI(Uri.parse(resultBean.getImageUrl()));
            mName.setText(resultBean.getName());
            mIntroduction.setText(resultBean.getSummary());
            //为 爱心赋值
            if (resultBean.getFollowMovie() == 2) {
                mHart.setChecked(false);
            } else {
                mHart.setChecked(true);
            }
            //改变电影关注状态

            mHart.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        EventBus.getDefault().post(new RefreshEvent(true, resultBean.getId()));
                    } else {
                        EventBus.getDefault().post(new RefreshEvent(false, resultBean.getId()));
                    }
                }
            });


        }

    }
}
