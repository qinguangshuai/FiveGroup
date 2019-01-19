package com.bw.movie.film.details.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/*
 *▶ 作者: ash ◀
 * 2019/1/19 13:50 01
 *(◕ᴗ◕✿)
 *
 *页面功能-1： 【】 ✔
 *页面功能-2： 【】 ✔
 *页面功能-3： 【】 ✔
 *页面功能-4： 【】 ✔
 *页面功能-5： 【】 ✔
 *页面功能-6： 【】 ✔
 *
 */


@Entity(nameInDb = "cache")
public class CacheBean {

    @Id(autoincrement = true)
    private Long id;
    private int followMovie;
    private String name;
    private String summary;
    @Generated(hash = 1179632581)
    public CacheBean(Long id, int followMovie, String name, String summary) {
        this.id = id;
        this.followMovie = followMovie;
        this.name = name;
        this.summary = summary;
    }
    @Generated(hash = 573552170)
    public CacheBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getFollowMovie() {
        return this.followMovie;
    }
    public void setFollowMovie(int followMovie) {
        this.followMovie = followMovie;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSummary() {
        return this.summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }


}
