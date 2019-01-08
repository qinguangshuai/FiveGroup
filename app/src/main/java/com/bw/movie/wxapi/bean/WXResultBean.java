package com.bw.movie.wxapi.bean;

/**
 * date:2019/1/7    21:00
 * author:Therefore(Lenovo)
 * fileName:WXResultBean
 */
public class WXResultBean {
    private String sessionId;
    private int userId;
    private WXUserInfoBean userInfo;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public WXUserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(WXUserInfoBean userInfo) {
        this.userInfo = userInfo;
    }
}
