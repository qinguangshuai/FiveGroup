package com.bw.movie.base;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.MyApp;
import com.bw.movie.R;
import com.bw.movie.error.AppManager;
import com.bw.movie.util.NetStateBroadReciver;

import com.bw.movie.util.NewThread;
import com.bw.movie.util.StatusBarUtil;

/*
 *  baseactivity
 * */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {

    private T mBasePresenter;
    private StatusView mStatusView;
    private ErrorView mErrorView;
    private BroadcastReceiver mReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (initLayoutId() != 0) {
            //设置全局显示
            mErrorView = new ErrorView(this);
            ((ViewGroup) getWindow().getDecorView()).addView(mErrorView);
            initVariable();
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
        super.setContentView(mStatusView);
    }

    @Override
    public void setContentView(int layoutResID) {
        View inflate = View.inflate(this, layoutResID, null);
        mStatusView = initStatuView(inflate);
        super.setContentView(mStatusView);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        mStatusView = initStatuView(view);
        super.setContentView(mStatusView, params);
    }

    private StatusView initStatuView(View content) {
        StatusView.Builder builder = new StatusView.Builder(this);
        mStatusView = builder.contentView(content)
                .emptyId(R.layout.layout_empity2)
                .erroryId(R.layout.layout_error)
                .loadingId(R.layout.layout_loading)
                .build();
        return mStatusView;
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
        mErrorView.unRegister();
        if (mReceiver != null) {
            unregisterReceiver(mReceiver);
        }
    }

    //显示内容
    public void showContent() {
        mStatusView.showContent();
    }

    //加载数据
    public void showloading() {



        mStatusView.showLoading();
    }
    //显示空白布局
    public void showEmpty(){



        mStatusView.showEmpty();

    }

    /**
     * 设置网络监听
     */
    private void setBreoadcast() {
        mReceiver = new NetStateBroadReciver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mReceiver, filter);
    }

    float x1, x2;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            //当手指按下的时候
            x1 = event.getX();
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {
            //当手指离开的时候
            x2 = event.getX();
            if (x2 - x1 > 100) {
                finish();
            }
        }
        return super.onTouchEvent(event);
    }
}
