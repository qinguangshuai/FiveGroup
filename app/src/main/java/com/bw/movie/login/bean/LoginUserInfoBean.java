package com.bw.movie.login.bean;

import com.bw.movie.base.BaseEntity;

/**
 * date:2019/1/7    20:24
 * author:Therefore(Lenovo)
 * fileName:LoginUserInfoBean
 */
public class LoginUserInfoBean extends BaseEntity {
    private long birthday;
    private int id;
    private long lastLoginTime;
    private String nickName;
    private String phone;
    private int sex;
    private String headPic;

    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }
}
