package com.bw.movie.film.details.popular.adapter;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.film.activity.SynopsisActivity;
import com.bw.movie.film.show.popular.bean.PopularBean;
import com.bw.movie.util.EmptyUtil;
import com.bw.movie.util.ToastUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/*-----------
 *🖐说明:
 *   热门电影 adapter 管理类
 */
public class PopularAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //非空判断工具类
    private EmptyUtil emptyUtil = new EmptyUtil();

    //吐司工具类
    private ToastUtil toast = new ToastUtil();


    //热门电影数据 list
    private List<PopularBean.ResultBean> result = new ArrayList<>();

    //热门电影 数据 set 方法
    public void setResult(List<PopularBean.ResultBean> result) {
        if (emptyUtil.isNull(this.result) == false) {
            this.result.clear();
        }
        this.result.addAll(result);
    }

    //热门电影 add 方法
    public void addResult(List<PopularBean.ResultBean> result) {
        this.result.addAll(result);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.popular_item, viewGroup, false);
        return new PopularAdapter.Hodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, final int i) {
        PopularAdapter.Hodler holder = (PopularAdapter.Hodler) viewHolder;
        //查找图片控件
        SimpleDraweeView img = holder.itemView.findViewById(R.id.entrypicture_item_popular_item);
        //查找文字控件
        TextView tv = holder.itemView.findViewById(R.id.moviename_item_popular_item);
        //判断是否为空
        if (emptyUtil.isNull(result) == false) {
            PopularBean.ResultBean resultBean = result.get(i);
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
                intent.putExtra("详情id",result.get(i).getId());
                viewHolder.itemView.getContext().startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        //非空判断
        if (emptyUtil.isNull(result) == false) {
            return result.size();
        }
        return 0;
    }

    //内部 holder 类
    private class Hodler extends RecyclerView.ViewHolder {
        public Hodler(View view) {
            super(view);
        }
    }
}//----------|热门电影适配器结束|----------
