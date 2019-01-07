package com.bw.movie.film.event;

import android.widget.CheckBox;

/*
 *作者:ash
 *TODO:
 *
 */
public class PraiseEvent {
    private int i ;
    private CheckBox checkBox;
    private int num ;

    public int getNum() {
        return num;
    }

    public PraiseEvent(int i, CheckBox checkBox, int num) {
        this.i = i;
        this.checkBox = checkBox;
        this.num = num;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public PraiseEvent(int i, CheckBox checkBox) {
        this.i = i;
        this.checkBox = checkBox;
    }

    public int getI() {
        return i;
    }

    public PraiseEvent(int i) {
        this.i = i;
    }
}
