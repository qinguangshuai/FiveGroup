package com.bw.movie.film.details.playing.adapter;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.film.show.playing.playing.PlayingBean;
import com.bw.movie.film.synopsis.activity.SynopsisActivity;
import com.bw.movie.util.EmptyUtil;
import com.bw.movie.util.ToastUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/*-----------
 *🖐说明:
 *   正在上映 adapter
 */
public class PlayingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //吐司工具类
    private ToastUtil toast = new ToastUtil();

    // 即将上映 || 正在上映 数据 list
    private List<PlayingBean.ResultBean> playresult = new ArrayList<>();

    //即将上映 || 正在上映  数据 set 方法
    public void setPlayResult(List<PlayingBean.ResultBean> playresult) {
        if (EmptyUtil.isNull(this.playresult) == false) {
            this.playresult.clear();
        }
        this.playresult.addAll(playresult);
    }

    //即将上映 || 正在上映 数据 add 方法
    public void addPlayResult(List<PlayingBean.ResultBean> playresult) {
        if (EmptyUtil.isNull(playresult)) {
            toast.Toast("没有更多了");
        }
        this.playresult.addAll(playresult);
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.popular_item, viewGroup, false);
        return new PlayingAdapter.Hodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, final int i) {
        PlayingAdapter.Hodler holder = (PlayingAdapter.Hodler) viewHolder;
        //查找图片控件
        SimpleDraweeView img = holder.itemView.findViewById(R.id.entrypicture_item_popular_item);
        //查找文字控件
        TextView tv = holder.itemView.findViewById(R.id.moviename_item_popular_item);
        //判断是否为空
        if (EmptyUtil.isNull(playresult) == false) {
            PlayingBean.ResultBean resultBean = playresult.get(i);
            //取出图片
            String imageUrl = resultBean.getImageUrl();
            //赋值
            img.setImageURI(Uri.parse(imageUrl));
            //取出文字并赋值
            tv.setText(resultBean.getName());
        } else {
            toast.Toast("请求数据有误");
        }


        //点击事件
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(viewHolder.itemView.getContext(),SynopsisActivity.class);
                intent.putExtra("详情id",playresult.get(i).getId());
                viewHolder.itemView.getContext().startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        //非空判断
        if (EmptyUtil.isNull(playresult) == false) {
            return playresult.size();
        }
        return 0;
    }

    //内部 holder 类
    private class Hodler extends RecyclerView.ViewHolder {
        public Hodler(View view) {
            super(view);
        }
    }

}//----------|正在上映适配器函数 结束|----------
