package com.bw.movie.wxapi.event;

public class FinishEvent {
private int finishlogin;

    public FinishEvent(int finishlogin) {

        this.finishlogin = finishlogin;
    }

    public int getFinishlogin() {
        return finishlogin;
    }

    public void setFinishlogin(int finishlogin) {
        this.finishlogin = finishlogin;
    }
}
