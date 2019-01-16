package com.bw.movie.login.bean;

import com.bw.movie.base.BaseEntity;

/**
 * date:2019/1/7    20:21
 * author:Therefore(Lenovo)
 * fileName:LoginResultBean
 */
public class LoginResultBean extends BaseEntity {
    private String sessionId;
    private int userId;
    private LoginUserInfoBean userInfo;

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

    public LoginUserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(LoginUserInfoBean userInfo) {
        this.userInfo = userInfo;
    }
}
