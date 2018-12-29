package com.bw.movie.base;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecyclerAdapter<VH extends RecyclerView.ViewHolder,E> extends RecyclerView.Adapter {
    private List<E> listData = new ArrayList();

    @NonNull
    @Override
    public abstract RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i);


    @Override
    public abstract void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i);


    @Override
    public int getItemCount() {
        return listData.size();
    }


    public void  addList(List list){
        if (list != null && list.size()>0) {
            listData.addAll(list);
        }

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
