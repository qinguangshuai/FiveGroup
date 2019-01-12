package com.bw.movie.greenbean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "GreenBean")
public class GreenDaoBean {
    @Id(autoincrement = true)
    private Long mId;
    private String title;
    private String kile;
    private String image;
    @Generated(hash = 1165964678)
    public GreenDaoBean(Long mId, String title, String kile, String image) {
        this.mId = mId;
        this.title = title;
        this.kile = kile;
        this.image = image;
    }

    public GreenDaoBean(String title, String kile, String image) {
        this.title = title;
        this.kile = kile;
        this.image = image;
    }

    @Generated(hash = 826843181)
    public GreenDaoBean() {
    }
    public Long getMId() {
        return this.mId;
    }
    public void setMId(Long mId) {
        this.mId = mId;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getKile() {
        return this.kile;
    }
    public void setKile(String kile) {
        this.kile = kile;
    }
    public String getImage() {
        return this.image;
    }
    public void setImage(String image) {
        this.image = image;
    }
  

 }
