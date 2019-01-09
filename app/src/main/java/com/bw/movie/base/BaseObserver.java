package com.bw.movie.base;

import com.bw.movie.MyApp;
import com.bw.movie.util.HttpCallBack;
import com.bw.movie.util.NewThread;
import com.bw.movie.util.ToastUtil;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/*
 *▶ 作者: ash ◀
 *@date:2019/1/9 20:25
 *(◕ᴗ◕✿)
 *
 *页面功能-1： 【】 ✔
 *页面功能-2： 【】 ✔
 *页面功能-3： 【】 ✔
 *页面功能-4： 【】 ✔
 *页面功能-5： 【】 ✔
 *页面功能-6： 【】 ✔
 *
 */
public class BaseObserver<T > implements Observer<T> {

    private HttpCallBack<T> mCallback;

    public BaseObserver(HttpCallBack<T> httpCallBack) {
        this.mCallback = httpCallBack;

    }


    //优先执行此方法
    @Override
    public void onSubscribe(Disposable d) {
        //如果没有网络!
        if (!NewThread.getmNewThread().isNetWork(MyApp.context)) {
            try {
                throw new Exception("网络错误,请检查网络!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //成功回调方法
    /*
    *
    * <T> 参数 为 bean 类
    *
    * */
    @Override
    public void onNext(T t) {
        mCallback.onSuccess(t);
    }

    //失败回调方法
    @Override
    public void onError(Throwable e) {
        ToastUtil.Toast(e.getMessage());
    }


    //数据成功完成后会调用该方法
    @Override
    public void onComplete() {

    }

}
