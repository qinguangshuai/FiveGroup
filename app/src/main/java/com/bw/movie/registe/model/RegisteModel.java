package com.bw.movie.registe.model;

import com.bw.movie.registe.bean.RegisteUser;
import com.bw.movie.registe.service.RegisteSrevice;
import com.bw.movie.util.OkHttpUtil;

import io.reactivex.Observable;

/**
 * date:2018/12/26    18:40
 * author:Therefore(Lenovo)
 * fileName:RegisteModel
 */
public class RegisteModel {
    public Observable<RegisteUser> postRegiste(String nickName,String phone,String pwd,String pwd2,int sex,String birthday,String imei,String ua,String screenSize,String os,String email){
        return OkHttpUtil.get().createa(RegisteSrevice.class).postRegiste(nickName, phone, pwd, pwd2, sex, birthday, imei, ua, screenSize, os, email);
    }
}
