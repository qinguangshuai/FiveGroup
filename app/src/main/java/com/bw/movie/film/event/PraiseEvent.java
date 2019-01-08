package com.bw.movie.film.event;

import android.widget.CheckBox;
import android.widget.RadioButton;

/*
 *作者:ash
 *TODO:
 *
 */
public class PraiseEvent {
    private int i ;
    private RadioButton radioButton;
    private int num ;
    private int index ;

    public PraiseEvent(int i,RadioButton radioButton, int num, int index) {
        this.i = i;
        this.radioButton = radioButton;
        this.num = num;
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public int getNum() {
        return num;
    }

    public RadioButton getRadioButton() {
        return radioButton;
    }

    public int getI() {
        return i;
    }

    public PraiseEvent(int i) {
        this.i = i;
    }
}
