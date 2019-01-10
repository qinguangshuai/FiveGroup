package com.bw.movie.base;

import android.os.NetworkOnMainThreadException;
import android.view.InflateException;

import com.bw.movie.MyApp;
import com.bw.movie.util.HttpCallBack;
import com.bw.movie.util.NewThread;
import com.bw.movie.util.ToastUtil;
import com.google.gson.JsonIOException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;

import javax.net.ssl.SSLHandshakeException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

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
public class BaseObserver<T> implements Observer<T> {

    private HttpCallBack<T> mCallback;
    private int errorCode = -1111;
    private String errorMsg = "未知的错误！";

    public BaseObserver(HttpCallBack<T> httpCallBack) {
        this.mCallback = httpCallBack;

    }

    //优先执行此方法
    @Override
    public void onSubscribe(Disposable d) {
        //如果没有网络!
        if (!NewThread.getmNewThread().isNetWork(MyApp.sContext)) {
            ToastUtil.Toast("网络错误,请检查网络!");
            /*try {
                throw new Exception("网络错误,请检查网络!");
            } catch (Exception e) {
                e.printStackTrace();
            }*/
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
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            errorCode = httpException.code();
            errorMsg = httpException.getMessage();
            mCallback.onFailer(errorMsg);
        } else if (e instanceof SocketTimeoutException) {
            errorCode = ERROR.TIMEOUT_ERROR;
            errorMsg = "服务器响应超时";
            mCallback.onFailer(errorMsg);
        } else if (e instanceof ConnectException) {
            errorCode = ERROR.TIMEOUT_ERROR;
            errorMsg = "网络连接异常，请检查网络";
            mCallback.onFailer(errorMsg);
        } else if (e instanceof UnknownHostException) {
            errorCode = ERROR.TIMEOUT_ERROR;
            errorMsg = "无法解析主机，请检查网络连接";
            mCallback.onFailer(errorMsg);
        } else if (e instanceof UnknownServiceException) {
            errorCode = ERROR.UNKNOWN;
            errorMsg = "未知的服务器错误";
            mCallback.onFailer(errorMsg);
        } else if (e instanceof IOException) {   //飞行模式等
            errorCode = ERROR.TIMEOUT_ERROR;
            errorMsg = "读取网络数据失败";
            mCallback.onFailer(errorMsg);
        } else if (e instanceof NetworkOnMainThreadException) {
            //主线程不能网络请求，这个很容易发现
            errorCode = ERROR.TIMEOUT_ERROR;
            errorMsg = "主线程不能网络请求";
            mCallback.onFailer(errorMsg);
        } else if (e instanceof NullPointerException){
            errorCode = ERROR.NULL_POINTER_EXCEPTION;
            errorMsg = "空指针错误" + e.toString();
            mCallback.onFailer(errorMsg);
        } else if (e instanceof RuntimeException) {
            //很多的错误都是extends RuntimeException
            errorCode = ERROR.TIMEOUT_ERROR;
            errorMsg = "运行时错误" + e.toString();
            mCallback.onFailer(errorMsg);
        }else if (e instanceof SSLHandshakeException) {
            //很多的错误都是extends RuntimeException
            errorCode = ERROR.SSL_ERROR;
            errorMsg = "证书出错";
            mCallback.onFailer(errorMsg);
        }else if (e instanceof ClassCastException) {
            //很多的错误都是extends RuntimeException
            errorCode = ERROR.CAST_ERROR;
            errorMsg = "类转换错误";
            mCallback.onFailer(errorMsg);
        }else if (e instanceof InflateException) {
            //很多的错误都是extends RuntimeException
            errorCode = ERROR.PARSE_ERROR;
            errorMsg = "解析错误";
            mCallback.onFailer(errorMsg);
        } else if (e instanceof IllegalAccessException) {
            //很多的错误都是extends RuntimeException
            errorCode = ERROR.ILLEGAL_STATE_ERROR;
            errorMsg = "非法数据异常";
            mCallback.onFailer(errorMsg);
        }  else {
            errorCode = ERROR.UNKNOWN;
            errorMsg = "未知错误";
            mCallback.onFailer(errorMsg);
        }
        ToastUtil.Toast(errorMsg);
    }

    //数据成功完成后会调用该方法
    @Override
    public void onComplete() {

    }

    //异常信息
    public static class ERROR {
        //未知错误
        public static final int UNKNOWN = 1000;
        //连接超时
        public static final int TIMEOUT_ERROR = 1001;
        //空指针错误
        public static final int NULL_POINTER_EXCEPTION = 1002;

        //证书出错
        public static final int SSL_ERROR = 1003;

        //类转换错误
        public static final int CAST_ERROR = 1004;

        //解析错误
        public static final int PARSE_ERROR = 1005;

        //非法数据异常
        public static final int ILLEGAL_STATE_ERROR = 1006;

    }
}
