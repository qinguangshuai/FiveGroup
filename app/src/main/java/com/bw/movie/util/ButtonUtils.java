package com.bw.movie.util;

import android.util.Log;

/**
 * date:2018/12/30    15:41
 * author:Therefore(Lenovo)
 * fileName:ButtonUtils
 */
public class ButtonUtils {
    private static long lastClickTime = 0;
    private static long DIFF = 1000;
    private static int lastButtonId = -1;
    private static long[] s = new long[2];

    /**
     * 判断两次点击的间隔，如果小于1000，则认为是多次无效点击
     *
     * @return
     */
    public static boolean isFastDoubleClick() {
        return isFastDoubleClick(-1, DIFF);
    }

    /**
     * 判断两次点击的间隔，如果小于1000，则认为是多次无效点击
     *
     * @return
     */
    public static boolean isFastDoubleClick(int buttonId) {
        return isFastDoubleClick(buttonId, DIFF);
    }

    /**
     * 判断两次点击的间隔，如果小于diff，则认为是多次无效点击
     *
     * @param diff
     * @return
     */
    public static boolean isFastDoubleClick(int buttonId, long diff) {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (lastButtonId == buttonId && lastClickTime > 0 && timeD < diff) {
            Log.v("isFastDoubleClick", "短时间内按钮多次触发");
            return true;
        }
        lastClickTime = time;
        lastButtonId = buttonId;
        return false;
    }

    public static  boolean isFastClick(){
        System.arraycopy(s,1,s,0,s.length - 1);
        s[s.length - 1] = System.currentTimeMillis();
        if (s[0] >= System.currentTimeMillis() - 1500){
            return true;
        }
        return false;
    }
}
