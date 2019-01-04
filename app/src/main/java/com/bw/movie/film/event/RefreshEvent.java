package com.bw.movie.film.event;

/*
 *作者:ash
 *TODO:
 *
 */
public class RefreshEvent {
   private boolean b ;
   private int i;

    public RefreshEvent(boolean b, int i) {
        this.b = b;
        this.i = i;
    }

    public boolean isB() {
        return b;
    }

    public int getI() {
        return i;
    }
}
