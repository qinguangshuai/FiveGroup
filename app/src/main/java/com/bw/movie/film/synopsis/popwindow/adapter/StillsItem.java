package com.bw.movie.film.synopsis.popwindow.adapter;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class StillsItem implements MultiItemEntity {
    public static final int IMGBIG=1;
    public static final  int IMGSMALL=2;
    private int itemType;

    public StillsItem(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
