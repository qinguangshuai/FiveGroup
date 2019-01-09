package com.bw.movie.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * date:2018/12/25    18:22
 * author:Therefore(Lenovo)
 * fileName:NewThread
 */
public class NewThread {


    //懒汉式 双重 锁  单例模式
    private NewThread() {
    }

    private static NewThread mNewThread;

    public static NewThread getmNewThread(){
        if(mNewThread == null){
            synchronized (NewThread.class){
                if(mNewThread==null){
                    mNewThread = new NewThread();
                }
            }
        }
        return mNewThread;
    }


    //判断网络是否连接
    public boolean isNetWork(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null) {
            return true;
        }
        return false;
    }

    //判断是否为wifi
    public boolean isWifi(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.getType() == connectivityManager.TYPE_WIFI) {
            return true;
        }
        return false;
    }
}
