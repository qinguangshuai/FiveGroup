package com.bw.movie.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.bw.movie.base.BaseEvent;

public class NetStateBroadReciver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //此处是主要代码，
        //如果是在开启wifi连接和有网络状态下
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            NetworkInfo info = intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
            if (NetworkInfo.State.CONNECTED == info.getState()) {
                //有网络
                BaseEvent.post(new NetWorkChangeEvent(true));
            } else {
                //无网络
                BaseEvent.post(new NetWorkChangeEvent(false));
            }
        }
    }
}
