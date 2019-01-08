package com.bw.movie.film.synopsis.popwindow.pop;

/*
 *作者:ash
 *TODO:
 *
 */

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.film.details.bean.DetailBean;
import com.bw.movie.film.synopsis.popwindow.adapter.Popupwindow1Adapter;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class DatailPopwindow  {
    private View mDatail;
    private DetailBean.ResultBean result;
    private PopupWindow popupWindow;
    private Context context ;

    public DatailPopwindow(View mDatail, DetailBean.ResultBean result, PopupWindow popupWindow, Context context) {
        this.mDatail = mDatail;
        this.result = result;
        this.popupWindow = popupWindow;
        this.context = context;
    }

    //第一个Popupwindow 的数据
    private void setmDatail(DetailBean.ResultBean result) {
        //获取图片集合
        List<String> posterList = result.getPosterList();
        //找到控件
        TextView back = mDatail.findViewById(R.id.back_pop_datail);
        //关闭方法
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        //查找控件
        RecyclerView mRecyclerView = mDatail.findViewById(R.id.Recyclerview_pop_datail);
        TextView mPlot = mDatail.findViewById(R.id.Plot_pop_datail); //剧情
        TextView mArea = mDatail.findViewById(R.id.area_pop_datail); //产地
        TextView mTime = mDatail.findViewById(R.id.time_pop_datail); //时长
        TextView mDirector = mDatail.findViewById(R.id.director_pop_datail); //导演
        TextView mTyep = mDatail.findViewById(R.id.type_pop_datail);  //类型
        SimpleDraweeView mImg = mDatail.findViewById(R.id.img_pop_datail); //图片
        mImg.setImageURI(Uri.parse(posterList.get(0))); //第一个pop 图片
        mPlot.setText(result.getSummary()); //给剧情赋值
        mArea.setText("地区:" + result.getPlaceOrigin());  //地区
        mTime.setText("时长:" + result.getDuration());  //时长
        mDirector.setText("导演:" + result.getDirector());  //导演
        mTyep.setText("类型:" + result.getMovieTypes());  //类型
        String starring = result.getStarring();
        final String[] split = starring.split(",");

        //适配器!!
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        Popupwindow1Adapter popupwindow1Adapter = new Popupwindow1Adapter();
        popupwindow1Adapter.setData(split);
        mRecyclerView.setAdapter(popupwindow1Adapter);

    }

}
