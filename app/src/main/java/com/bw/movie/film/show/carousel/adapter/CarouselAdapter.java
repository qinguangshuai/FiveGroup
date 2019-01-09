package com.bw.movie.film.show.carousel.adapter;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.film.show.carousel.bean.CarouselBean;
import com.bw.movie.film.event.JumpEvent;
import com.bw.movie.util.EmptyUtil;
import com.bw.movie.util.ToastUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import recycler.coverflow.RecyclerCoverFlow;

/*-----------
 *🖐todo:说明
 *   自定义轮播图的适配器
 */
public class CarouselAdapter extends RecyclerCoverFlow.Adapter<RecyclerCoverFlow.ViewHolder> {
    //吐司工具类
    private ToastUtil toast = new ToastUtil();
    //轮播数据
    private CarouselBean mCarouselBean = new CarouselBean();

    //轮播图数据 set方法
    public void setmCarouselBean(CarouselBean mCarouselBean) {
        this.mCarouselBean = mCarouselBean;
    }

    @NonNull
    @Override
    public RecyclerCoverFlow.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.carouse_item, viewGroup, false);
        return new CarouselAdapter.Hodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerCoverFlow.ViewHolder viewHolder, int i) {
        CarouselAdapter.Hodler holder = (CarouselAdapter.Hodler) viewHolder;

        //设置跳转
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new JumpEvent(0));
            }
        });

        //查找图片控件
        SimpleDraweeView img = holder.itemView.findViewById(R.id.entrypicture_item_carouse_item);
        //查找文字控件
        TextView tv = holder.itemView.findViewById(R.id.moviename_item_carouse_item);
        //判断是否为空
        if (EmptyUtil.isNull(mCarouselBean.getResult()) == false) {
            //计算循环下标
            int i1 = i % mCarouselBean.getResult().size();
            //获取集合
            CarouselBean.ResultBean resultBean = mCarouselBean.getResult().get(i1);
            //取出图片
            String imageUrl = resultBean.getImageUrl();
            //赋值
            img.setImageURI(Uri.parse(imageUrl));
            //取出文字并赋值
            tv.setText(resultBean.getName());

        } else {
            toast.Toast("请求数据有误");
        }


    }

    //todo:条目总数 最大整型值
    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    private class Hodler extends RecyclerView.ViewHolder {
        public Hodler(View view) {
            super(view);
        }
    }
}//----------|轮播图适配器函数结束|----------
