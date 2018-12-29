package com.bw.movie.util;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * date:2018/12/25    17:40
 * author:Therefore(Lenovo)
 * fileName:OkHttpUtil
 */
/**
 * 网络请求
 * */
public class OkHttpUtil {

    private static OkHttpClient client;
    public static Retrofit retrofit;

    //单例模式
    private OkHttpUtil(){
        retrofit = new Retrofit.Builder()
                .baseUrl(UrlUtil.TOTAL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static void init(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //判断是否断网
        builder.retryOnConnectionFailure(true);
        //添加请求头
        builder.addInterceptor(new OKHeaderInterceptor());
        builder.addInterceptor(new OkLogInterceptor());
        builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        client = builder.build();
    }

    public static OkHttpUtil get(){
        return OkHttpUtilThis.mOkHttpUtil;
    }
    private static final class OkHttpUtilThis
    {
        private static  final OkHttpUtil mOkHttpUtil=new OkHttpUtil();
    }
    public <T>T createa(Class<T> tClass){
        return retrofit.create(tClass);
    }
}
