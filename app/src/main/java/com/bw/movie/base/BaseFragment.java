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

import com.bw.movie.R;

/*
 *  basefragment
 * */
public abstract class BaseFragment<T extends BasePresenter> extends Fragment {

    public AppCompatActivity mActivity;    //提供一个全局化的context

    protected String TAG = "";

    protected boolean isinitData = false; //是否加载了数据

    public View rootView;

    private T mBasePresenter;

    private StatusView statusView;

    //oncreate方法
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = getClass().getSimpleName();     //getName ----“实体名称”      getSimpleName ---- “底层类简称” ---- Main
    }

    //onAttach方法
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (AppCompatActivity) context;
        mBasePresenter = initPresenter();
    }

    //oncreateview方法
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(initLayoutId(), container, false);  //为fragment 添加核心布局
        statusView = initStatuView(rootView);    //调用加载布局
        initVarisble();  //初始化变量
        initView();      //初始化布局
        return statusView;
    }


    //onActivityCreate方法
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initListener();    //事件监听

         /* 界面变为可见时，调用setUserVisibleHint(true)
            界面变为不可见时，调用setUserVisibleHint(false)
            */
        if (!isinitData && getUserVisibleHint()) {   //isinitDat取非只有变为了 ture and 当前页面可见   第一次会执行这个


            if (statusView != null) {  //如果 startusView 不为空

                initData();          //加载数据

                isinitData = true;   //并且 将isinitData 变为true
            }

        } else {    //否则
            onVisiable();
        }

    }

    private StatusView initStatuView(View content) {
        StatusView.Builder builder = new StatusView.Builder(mActivity);
        statusView = builder.contentView(content)
                .emptyId(R.layout.layout_empity2)
                .erroryId(R.layout.layout_error)
                .loadingId(R.layout.layout_loading)
                .build();
        return statusView;
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
        //参数: 如果是 true 即代表fragment 变为可见   反之 false 代表fragment 变得不可见
        if (isVisibleToUser) {
            if (rootView != null) { //如果当前的 fragment 变得可见 而且有不为空
                if (!isinitData) {  //如果当前的
                    initData();
                    isinitData = true;
                } else {
                    onVisiable();
                }
            }
        }
    }

    protected void onVisiable() {

    }

    //显示内容
    public void showContent() {
        statusView.showContent();
    }

    //显示加载布局
    public void showloading() {
        statusView.showLoading();
    }

    //显示空白布局
    public void showEmpty() {
        statusView.showEmpty();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mActivity != null) {
            mActivity = null;
        }
    }
}
