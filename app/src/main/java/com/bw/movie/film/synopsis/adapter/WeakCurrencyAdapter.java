package com.bw.movie.film.synopsis.adapter;

/*
 *作者:ash
 *TODO:
 *
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.dak.weakview.adapter.WeakViewAdapter;
import com.dak.weakview.adapter.viewholder.WeakCurrencyViewHold;

import java.util.ArrayList;
import java.util.List;


/*-----------
*🖐说明:
*   这是一个 baseadapter 类 一目了然
*/
public abstract class WeakCurrencyAdapter<T> extends WeakViewAdapter<WeakCurrencyViewHold> {
    private List<T> mList = new ArrayList<>();
    private Context mContext;
    private int mLayoutId;

    public WeakCurrencyAdapter(Context context, int layoutId) {
        this.mContext = context;
        this.mLayoutId = layoutId;
    }

    @Override
    public WeakCurrencyViewHold onCreateViewHolder(ViewGroup parent) {
        return new WeakCurrencyViewHold(LayoutInflater.from(mContext).inflate(mLayoutId, parent, false));
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }


    public T getItem(int position) {
        return mList.get(position);
    }


    @Override
    public void notifyItemView(WeakCurrencyViewHold holder, int position) {
        notifyItemView(holder, mList.get(position), position);
    }


    public abstract void notifyItemView(WeakCurrencyViewHold holder, T item, int position);


    public void refreshData(List<T> list) {
        if (list == null)
            return;
        mList.clear();
        mList.addAll(list);
        this.notifyDataSetChanged();
    }
}