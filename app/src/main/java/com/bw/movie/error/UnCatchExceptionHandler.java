package com.bw.movie.error;

import android.content.Context;
import android.os.Process;

/**
 * date:2018/12/25    16:18
 * author:秦广帅(Lenovo)
 * fileName:UnCatchExceptionHandler
 * 全局异常
 */
public class UnCatchExceptionHandler implements Thread.UncaughtExceptionHandler {

    private Context mContext;
    private Thread.UncaughtExceptionHandler mHandler;

    private UnCatchExceptionHandler(){}

    private static UnCatchExceptionHandler sUnCatchExceptionHandler = new UnCatchExceptionHandler();

    public static UnCatchExceptionHandler getmExceptionHandler(){
        return sUnCatchExceptionHandler;
    }

    public void init(Context mContext){
        this.mContext = mContext.getApplicationContext();
        mHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        if (mHandler != null){
            mHandler.uncaughtException(t, e);
        }else{
            try {

                Thread.sleep(3000);
            } catch (InterruptedException e1) {

                Process.killProcess(Process.myPid());
            }
        }
    }
}
