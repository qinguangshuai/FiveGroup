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
import com.bw.movie.util.NetBroadCastReciver;
import com.bw.movie.util.NetworkDetermineEvent;
import com.bw.movie.util.NotifyUtil;
import com.bw.movie.util.StatusBarUtil;

import org.greenrobot.eventbus.Subscribe;

/*
 *  baseactivity
 * */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {
    private T mBasePresenter;
    private StatusView statusView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (initLayoutId() != 0) {
            initVariable();
            setContentView(initLayoutId());
            setStatusBarColor(R.color.themColor);
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
        super.setContentView(view);
        statusView = initStatuView(view);
    }

    @Override
    public void setContentView(int layoutResID) {
        View inflate = View.inflate(this, layoutResID, null);
        statusView = initStatuView(inflate);
        super.setContentView(layoutResID);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        statusView = initStatuView(view);
        super.setContentView(view, params);
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

    /**
     * 设置网络监听
     */
    private void setBreoadcast() {
        BroadcastReceiver receiver = new NetBroadCastReciver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(receiver, filter);
    }

    @Subscribe
    public void isNetWork(NetworkDetermineEvent event) {
        boolean aTrue = event.isTrue();
        //有网络
        if (aTrue) {

        } else {
            //无网络
        }
    }

}
