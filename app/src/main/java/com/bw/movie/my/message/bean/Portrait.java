package com.bw.movie.my.message.bean;

import com.bw.movie.base.BaseEntity;

/**
 * date:2019/1/4    20:33
 * author:Therefore(Lenovo)
 * fileName:Portrait
 */
public class Portrait extends BaseEntity {

    private String image;

    public Portrait(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
