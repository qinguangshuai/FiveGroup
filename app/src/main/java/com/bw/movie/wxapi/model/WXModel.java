package com.bw.movie.wxapi.model;

import com.bw.movie.util.OkHttpUtil;
import com.bw.movie.wxapi.bean.WXUser;
import com.bw.movie.wxapi.service.WXService;

import io.reactivex.Observable;

/**
 * date:2018/12/27    11:19
 * author:Therefore(Lenovo)
 * fileName:WXModel
 */
public class WXModel {
    public Observable<WXUser> postWX(String code){
        return OkHttpUtil.get().createa(WXService.class).postWX(code);
    }
}
