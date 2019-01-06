package com.bw.movie.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

/**
 * date:2019/1/5    12:38
 * author:Therefore(Lenovo)
 * fileName:NetBroadCastReciver
 */
public class NetBroadCastReciver extends BroadcastReceiver {
    /**
     * 只有当网络改变的时候才会 经过广播。
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        //此处是主要代码，
        // 如果是在开启wifi连接和有网络状态下
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
            if (NetworkInfo.State.CONNECTED == info.getState()) {
                //连接状态 处理自己的业务逻辑

                EventBus.getDefault().post(new NetworkDetermineEvent(true));

            } else {
                Toast.makeText(context, "网络链接失败", Toast.LENGTH_SHORT).show();
                EventBus.getDefault().post(new NetworkDetermineEvent(false));
            }
        }
    }
}

