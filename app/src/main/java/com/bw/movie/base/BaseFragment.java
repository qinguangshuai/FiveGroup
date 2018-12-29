package com.bw.movie.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/*
*  basefragment
* */
public abstract class BaseFragment<T extends BasePresenter> extends Fragment {
    public AppCompatActivity mActivity;
    protected  String TAG = "";
    protected boolean isinitData=false;
    public View rootView;
    private T mBasePresenter;


    //oncreate方法
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG=getClass().getSimpleName();
    }

    //onAttach方法
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity= (AppCompatActivity) context;
        mBasePresenter=initPresenter();
    }

    //oncreateview方法
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(initLayoutId(), container, false);
        initVarisble();
        initView();
        return rootView;
    }

    //onActivityCreate方法
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
            if(!isinitData){
                initData();
                isinitData=true;
            }else{
                onVisiable();
            }
        initListener();
            if(!isinitData){
                initData();
                isinitData=true;
            }else{
                onVisiable();
            }

    }

    public abstract void initView();
    public abstract void initListener();
    public abstract void initData();
    public abstract int initLayoutId();
    public abstract T initPresenter();
    //初始化变量
    public abstract void initVarisble();
    public T getPresenter() {
        return mBasePresenter;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            if(!isinitData){
                initData();
                isinitData=true;
            }else{
                onVisiable();
            }
        }
    }

    protected void onVisiable(){

    }

    @Override
    public void onDetach() {
        super.onDetach();
        if(mActivity!=null){
            mActivity=null;
        }
    }
}
