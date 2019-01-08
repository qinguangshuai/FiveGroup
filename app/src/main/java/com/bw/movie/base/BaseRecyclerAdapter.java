package com.bw.movie.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

public abstract class  BaseRecyclerAdapter<VH extends RecyclerView.ViewHolder,E> extends RecyclerView.Adapter {

    private List<E> listData;
    private Context mContext;
    private final LayoutInflater mLayoutInflater;
    private boolean isShowFootView = false;
    private boolean isLoadMore = false;
    protected int FOOT_TYPE = 0430;
    private OnLoadMoreListener mLoadMoreListener;

    public BaseRecyclerAdapter(List<E> listData, Context context) {
        this.listData = listData;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public BaseRecyclerAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void setListData(List<E> listData) {
        this.listData = listData;
        notifyDataSetChanged();
    }

    public void setLoadMore(boolean loadMore) {
        isLoadMore = loadMore;
    }

    /**
     * 显示footview，默认跟随loadmore，当loadmore为true是显示footview否则不显示
     * 当设为true时，都会显示一个footview
     */
    public void setIsShowFootView(boolean isShowFootView) {
        this.isShowFootView = isShowFootView;
    }

    /**
     * 设置是否底部显示加载更多，默认不显示
     *
     * @param isLoadMore
     */
    public void setIsLoadMore(boolean isLoadMore) {
        this.isLoadMore = isLoadMore;
    }

    @NonNull
    @Override
    public abstract RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i);

    @Override
    public abstract void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i);

    @Override
    public int getItemCount() {
        if ((isShowFootView || isLoadMore))
            return listData == null ? 0 : listData.size() + 1;
        return listData == null ? 0 : listData.size();
    }

    public void addList(List list){
        if (list != null && list.size()>0) {
            listData.addAll(list);
        }
    }

    public void deleteList(List list){
        if (list != null && list.size()>0) {
            listData.removeAll(list);
        }
    }

    /**
     * 加载更多的接口,滑动到底部自动加载数据
     */
    public interface OnLoadMoreListener {
        void loadMore();
    }

    /**
     * 设置加载更多的监听事件
     *
     * @param moreListerner
     */
    public void setOnLoadMoreListerner(OnLoadMoreListener moreListerner) {//对外暴露的接口  设置加载更多的监听事件
        isLoadMore = true;
        this.mLoadMoreListener = moreListerner;
    }

    public void clearList(){
        listData.clear();
    }

    public E getItem(int posito){
        return   listData.get(posito);
    }

    public void append(E o){
        listData.add(o);
        notifyItemInserted(listData.size()-2);
    }

    public void appendHead(E o){
        listData.add(0,o);
        notifyItemInserted(0);
    }
}
