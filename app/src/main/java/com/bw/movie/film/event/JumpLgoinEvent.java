package com.bw.movie.film.event;

/*
 *▶ 作者: ash ◀
 *@date:2019/1/11 12:04
 *(◕ᴗ◕✿)
 *
 *页面功能-1： 【】 ✔
 *页面功能-2： 【】 ✔
 *页面功能-3： 【】 ✔
 *页面功能-4： 【】 ✔
 *页面功能-5： 【】 ✔
 *页面功能-6： 【】 ✔
 *
 */
public class JumpLgoinEvent {
    private boolean b;
    private int resher;

    public void setA(int resher) {
        this.resher = resher;
    }

    public int getA() {
        return resher;
    }

    public JumpLgoinEvent(boolean b) {
        this.b = b;
    }

    public JumpLgoinEvent(int resher) {
        this.resher = resher;
    }

    public boolean isB() {
        return b;
    }
}
