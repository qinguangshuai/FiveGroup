package com.bw.movie.cinema.event;

import android.widget.CheckBox;

public class GoodEvent {
    private boolean isChecked;
    private CheckBox mCheckBox;
    private int index ;

    public int getIndex() {
        return index;
    }


    public GoodEvent(boolean isChecked,  CheckBox mCheckBox, int index) {
        this.isChecked = isChecked;
        this.mCheckBox = mCheckBox;
        this.index = index;
    }


    public CheckBox getmCheckBox() {
        return mCheckBox;
    }

    public GoodEvent(boolean isChecked,  CheckBox mCheckBox) {
        this.isChecked = isChecked;
        this.mCheckBox = mCheckBox;
    }

    public GoodEvent(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public boolean isChecked() {
        return isChecked;
    }

}
