package com.bw.movie.util;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/*
 *作者:ash
 *TODO:
 *
 */
public class RecyclerViewScrollUtil {

    //一个RecyclerView 滚动的 工具类
    public static void Scroll(RecyclerView recyclerView, final boolean isV , final onEvent onEvent){
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            //用来标记是否正在向最后一个滑动
            boolean isSlidingToLast = false;
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                //设置什么布局管理器,就获取什么的布局管理器
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                // 当停止滑动时
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    //获取最后一个完全显示的ItemPosition ,角标值
                    int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
                    //所有条目,数量值
                    int totalItemCount = manager.getItemCount();
                    // 判断是否滚动到底部，并且是向右滚动
                    if (lastVisibleItem == (totalItemCount - 1) && isSlidingToLast) {
                        onEvent.info();
                    }
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //dx用来判断横向滑动方向，dy用来判断纵向滑动方向
                //dx>0:向右滑动,dx<0:向左滑动
                //dy>0:向下滑动,dy<0:向上滑动
                if(isV){
                    if (dy > 0) {
                        isSlidingToLast = true;
                    } else {
                        isSlidingToLast = false;
                    }
                }else {
                    if (dx > 0) {
                        isSlidingToLast = true;
                    } else {
                        isSlidingToLast = false;
                    }
                }

            }
        });
    }


    public interface onEvent{
        void info();
    }



}
