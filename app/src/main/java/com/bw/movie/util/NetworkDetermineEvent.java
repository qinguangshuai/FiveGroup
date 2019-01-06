package com.bw.movie.util;

/**
 * author:&Nathaniel
 * data:2019/1/4
 * e-mail:q1642625949@163.com
 */

//判断网络event
public class NetworkDetermineEvent {
    private boolean isTrue;

    public NetworkDetermineEvent(boolean isTrue) {
        this.isTrue = isTrue;
    }

    public boolean isTrue() {
        return isTrue;
    }

    public void setTrue(boolean aTrue) {
        isTrue = aTrue;
    }
}
