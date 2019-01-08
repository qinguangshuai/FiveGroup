package com.bw.movie.util;

/**
 * date:2019/1/8    13:54
 * author:Therefore(Lenovo)
 * fileName:HttpCallBack
 */
public interface HttpCallBack<T> {
    //成功
    void onSuccess(T name);
    //失败
    void onFailer(String result);
}
