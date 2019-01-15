package com.bw.movie.base;

import com.bw.movie.net.OkHttpUtil;
import retrofit2.Retrofit;

/*
*  baseModel的封装+
* */
public class BaseModel {
    //retrofit请求数据的管理类
    private Retrofit retrofit;
    public BaseModel(){
         retrofit = OkHttpUtil.retrofit;
    }

}
