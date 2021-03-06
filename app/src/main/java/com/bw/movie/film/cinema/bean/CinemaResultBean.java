package com.bw.movie.film.cinema.bean;

import com.bw.movie.base.BaseEntity;

/**
 * date:2019/1/7    20:35
 * author:Therefore(Lenovo)
 * fileName:CinemaResultBean
 */
public class CinemaResultBean extends BaseEntity {
    private String address;
    private int commentTotal;
    private int distance;
    private int followCinema;
    private int id;
    private String logo;
    private String name;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCommentTotal() {
        return commentTotal;
    }

    public void setCommentTotal(int commentTotal) {
        this.commentTotal = commentTotal;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getFollowCinema() {
        return followCinema;
    }

    public void setFollowCinema(int followCinema) {
        this.followCinema = followCinema;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
