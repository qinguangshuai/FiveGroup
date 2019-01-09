package com.bw.movie.cinema.event;

import android.widget.CheckBox;

public class NeighbourEvent  {
    private boolean isChecked;
    private CheckBox checkBox;
    private int id;

    public NeighbourEvent(boolean isChecked, CheckBox checkBox, int id) {
        this.isChecked = isChecked;
        this.checkBox = checkBox;
        this.id = id;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
