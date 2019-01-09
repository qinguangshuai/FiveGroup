package com.bw.movie.base;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;

import com.bw.movie.error.AppManager;
import com.bw.movie.util.NetStateBroadReciver;
import com.bw.movie.util.NetWorkChangeEvent;

import com.bw.movie.util.StatusBarUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/*
 *  baseactivity
 * */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {
    private T mBasePresenter;
    private StatusView statusView;
    private ErrorView mErrorView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //设置全局显示
        mErrorView = new ErrorView(this);
        ((ViewGroup) getWindow().getDecorView()).addView(mErrorView);
        if (initLayoutId() != 0) {
            initVariable();
            EventBus.getDefault().register(this);
            setContentView(initLayoutId());
            setStatusBarColor(R.color.themColor);
            AppManager.getAppManager().addActivity(this);
            initView();
            initListener();
            initData();
            setBreoadcast();
        } else {
            finish();
        }
    }

    public abstract void initView();

    public abstract void initListener();

    public abstract void initData();

    public abstract int initLayoutId();

    //初始化变量
    public abstract void initVariable();

    public abstract T initPresenter();

    //设置沉浸式状态栏
    public void setStatusBarColor(int color) {
        StatusBarUtil.setStatusBarColor(color);
    }

    @Override
    public void setContentView(View view) {

        initStatuView(view);
        super.setContentView(statusView);

    }

    @Override
    public void setContentView(int layoutResID) {
        View inflate = View.inflate(this, layoutResID, null);
        statusView = initStatuView(inflate);
        super.setContentView(statusView);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        statusView = initStatuView(view);
        super.setContentView(statusView, params);
    }

    private StatusView initStatuView(View content) {
        StatusView.Builder builder = new StatusView.Builder(this);
        statusView = builder.contentView(content)
                .emptyId(R.layout.layout_empity2)
                .erroryId(R.layout.layout_error)
                .loadingId(R.layout.layout_loading)
                .build();
        return statusView;
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        mBasePresenter = initPresenter();
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mBasePresenter != null) {
            mBasePresenter.onDestory();
        }
    }

    public T getPresenter() {
        return mBasePresenter;
    }

    public void showEmptyLayout() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    //显示内容
    public void showContent() {
        statusView.showContent();
    }

    /**
     * 设置网络监听
     */
    private void setBreoadcast() {
        BroadcastReceiver receiver = new NetStateBroadReciver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(receiver, filter);
    }
    @Subscribe
    public void isNetWork(NetWorkChangeEvent event) {
        boolean aTrue = event.isConnected;
        //有网络
        if (aTrue) {
            showContent();
            mErrorView.setVisibility(View.GONE);
        } else {
            mErrorView.setVisibility(View.VISIBLE);
        }
    }
}
