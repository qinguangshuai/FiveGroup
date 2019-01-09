package com.bw.movie.util;

import android.content.Context;

import com.bw.movie.MyApp;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * date:2018/12/25    17:47
 * author:Therefore(Lenovo)
 * fileName:OKHeaderInterceptor
 */
public class OKHeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();
        builder.addHeader("version","version1.0");
        builder.addHeader("sessionId",MyApp.sContext.getSharedPreferences("login",Context.MODE_PRIVATE).getString("sessionId",""));
        builder.addHeader("ak",MyApp.sContext.getSharedPreferences("login",Context.MODE_PRIVATE).getString("ak","0110010010000"));
        builder.addHeader("userId",MyApp.sContext.getSharedPreferences("login",Context.MODE_PRIVATE).getInt("userId",-1)+"");
        request = builder.build();
        return chain.proceed(request);
    }
}
