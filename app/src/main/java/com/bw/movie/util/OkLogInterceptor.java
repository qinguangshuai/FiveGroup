package com.bw.movie.util;

import android.util.Log;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * date:2018/12/25    17:50
 * author:Therefore(Lenovo)
 * fileName:OkLogInterceptor
 */
public class OkLogInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String method = request.method();
        HttpUrl url = request.url();
        LogUtil.i("本次请求"+url.toString()+"method"+method);

        Headers headers = request.headers();
        Set<String> names = headers.names();
        Iterator<String> iterator = names.iterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            String s = headers.get(next);
            LogUtil.d(next+":"+s);
        }
        return chain.proceed(request);
    }
}
