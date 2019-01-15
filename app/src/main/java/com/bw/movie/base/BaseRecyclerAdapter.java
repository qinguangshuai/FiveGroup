package com.bw.movie.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.List;

public abstract class BaseRecyclerAdapter<VH extends RecyclerView.ViewHolder, T> extends RecyclerView.Adapter {

    private List<T> mListdata;
    private Context mContext;
    private final LayoutInflater mLayoutInflater;
    private boolean isShowFootView = false;
    private boolean isLoadMore = false;
    protected int FOOT_TYPE = 0430;
    private OnLoadMoreListener mLoadMoreListener;

    public BaseRecyclerAdapter(List<T> listData, Context context) {
        this.mListdata = listData;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public BaseRecyclerAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void setListdata(List<T> listdata) {
        this.mListdata = listdata;
        notifyDataSetChanged();
    }

    public List<T> getListdata() {
        return mListdata;
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
            return mListdata == null ? 0 : mListdata.size() + 1;
        return mListdata == null ? 0 : mListdata.size();
    }

    //添加集合
    public void addList(List list) {
        if (list != null && list.size() > 0) {
            mListdata.addAll(list);
        }
    }

    //指定位置添加item
    public void addData(int position,T data){
        mListdata.add(position,data);
        notifyItemChanged(position);
    }

    //删除集合
    public void removeList(List list) {
        if (list != null && list.size() > 0) {
            mListdata.removeAll(list);
        }
    }

    //指定位置删除item
    public void removeData(int position){
        mListdata.remove(position);
        notifyItemChanged(position);
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
    public void setOnLoadMoreListerner(OnLoadMoreListener moreListerner) {
        //对外暴露的接口  设置加载更多的监听事件
        isLoadMore = true;
        this.mLoadMoreListener = moreListerner;
    }

    public void clearList() {
        mListdata.clear();
    }

    public T getItem(int posito) {
        return mListdata.get(posito);
    }

    public void append(T t) {
        mListdata.add(t);
        notifyItemInserted(mListdata.size() - 2);
    }
}
