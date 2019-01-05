package com.bw.movie.cinema.bean;

/**
 * date:2018/12/29    20:06
 * author:Therefore(Lenovo)
 * fileName:AddressUser
 */
public class AddressUser {

    private String city;
    private String cid;

    public AddressUser(String city, String cid) {
        this.city = city;
        this.cid = cid;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }
}
