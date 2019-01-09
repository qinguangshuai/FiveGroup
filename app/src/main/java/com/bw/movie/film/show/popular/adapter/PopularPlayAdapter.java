package com.bw.movie.film.show.popular.adapter;

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
import com.bw.movie.film.show.popular.bean.PopularBean;
import com.bw.movie.film.event.JumpForThreeActivityBean;
import com.bw.movie.film.event.RefreshEvent;
import com.bw.movie.util.EmptyUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/*-----------
*🖐说明:
*   正在上映
*/
public class PopularPlayAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    //判空工具类

    //热门电影数据 list
    private List<PopularBean.ResultBean> result = new ArrayList<>();

    //热门电影 数据 set 方法
    public void setResult(List<PopularBean.ResultBean> result) {
        if (EmptyUtil.isNull(this.result) == false) {
            this.result.clear();
        }
        this.result.addAll(result);
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_details, viewGroup, false);
        return new PopularPlayAdapter.Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        final PopularBean.ResultBean resultBean = result.get(i);
        PopularPlayAdapter.Holder holder = (PopularPlayAdapter.Holder) viewHolder;
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
        if(EmptyUtil.isNull(result)==false){
            return result.size();
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

        public void setData(final PopularBean.ResultBean resultBean) {
            mSimpleDraweeView.setImageURI(Uri.parse(resultBean.getImageUrl()));
            mName.setText(resultBean.getName());
            mIntroduction.setText(resultBean.getSummary());

            mIntroduction.setText(resultBean.getSummary());
            //为 爱心赋值
            if(resultBean.getFollowMovie()==2){
                mHart.setChecked(false);
            }else {
                mHart.setChecked(true);
            }
            //改变电影关注状态
            mHart.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        EventBus.getDefault().post(new RefreshEvent(true, resultBean.getId()));
                    }else {
                        EventBus.getDefault().post(new RefreshEvent(false, resultBean.getId()));
                    }
                }
            });
        }

    }
}
