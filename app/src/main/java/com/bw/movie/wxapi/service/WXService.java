package com.bw.movie.wxapi.service;

import com.bw.movie.util.UrlUtil;
import com.bw.movie.wxapi.bean.WXUser;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * date:2018/12/27    9:50
 * author:Therefore(Lenovo)
 * fileName:WXService
 */
public interface WXService {
    @POST(UrlUtil.WECHATLOGIN)
    @FormUrlEncoded
    Observable<WXUser> postWX(@Field("code") String code);
}
