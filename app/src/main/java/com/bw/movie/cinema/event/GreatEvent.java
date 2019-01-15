package com.bw.movie.cinema.event;

import android.widget.CheckBox;

public class GreatEvent {
    private boolean b;
    private CheckBox checkBox;
    private int id ;

    public GreatEvent(boolean b, CheckBox checkBox, int id) {
        this.b = b;
        this.checkBox = checkBox;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public GreatEvent(boolean b) {
        this.b = b;
    }


    public boolean isB() {
        return b;
    }
}
