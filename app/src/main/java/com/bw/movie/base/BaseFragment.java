package com.bw.movie.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.util.NetStateBroadReciver;
import com.bw.movie.util.NetWorkChangeEvent;

import org.greenrobot.eventbus.Subscribe;

/*
 *  basefragment
 * */
public abstract class BaseFragment<T extends BasePresenter> extends Fragment {

    public AppCompatActivity mActivity;
    protected String TAG = "";
    protected boolean isinitData = false;
    public View rootView;
    private T mBasePresenter;
    private StatusView statusView;
    private ErrorView mErrorView;

    //oncreate方法
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = getClass().getSimpleName();
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
        rootView = inflater.inflate(initLayoutId(), container, false);
        initVarisble();
        initView();
        return rootView;
    }

    //onActivityCreate方法
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initListener();
        if (!isinitData && getUserVisibleHint()) {
            initData();
            isinitData = true;
            BaseEvent.register(this);
            setBreoadcast();
        } else {
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
        if (isVisibleToUser) {
            if (rootView != null) {
                if (!isinitData) {
                    initData();
                    isinitData = true;
                } else {
                    onVisiable();
                }
            }
        }
    }

    private StatusView initStatuView(View content) {
        StatusView.Builder builder = new StatusView.Builder(getActivity());
        statusView = builder.contentView(content)
                .emptyId(R.layout.layout_empity2)
                .erroryId(R.layout.layout_error)
                .loadingId(R.layout.layout_loading)
                .build();
        return statusView;
    }

    protected void onVisiable() {

    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mActivity != null) {
            mActivity = null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        BaseEvent.unregister(this);
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
        getActivity().registerReceiver(receiver, filter);
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
