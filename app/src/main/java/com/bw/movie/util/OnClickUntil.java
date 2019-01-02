package com.bw.movie.util;

import android.util.Log;

/**
 * date:2019/1/1    18:33
 * author:Therefore(Lenovo)
 * fileName:OnClickUntil
 */
public class OnClickUntil {
    // 两次点击按钮之间的点击间隔不能少于1000毫秒
    private static final int MIN_CLICK_DELAY_TIME = 400;
    private static long lastClickTime=0;

    public static boolean isFastClick() {
        boolean flag = false;
        long curClickTime = System.currentTimeMillis();
        long result=curClickTime - lastClickTime;
        Log.i("curClickTime",curClickTime+">>>"+result);
        if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
            flag = true;
        }
        lastClickTime = curClickTime;
        return flag;
    }
}
