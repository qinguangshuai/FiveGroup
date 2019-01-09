package com.bw.movie.base;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.bumptech.glide.load.model.Model;
import com.bw.movie.login.LoginActivity;
import com.bw.movie.util.NewThread;
import com.bw.movie.util.OkHttpUtil;
import com.bw.movie.util.SpUtil;
import com.bw.movie.util.ToastUtil;

import retrofit2.Retrofit;

/*
*  baseModel的封装
* */
public abstract class BaseModel<T extends Model> {


    public final Retrofit retrofit;

    public BaseModel(){
        retrofit = OkHttpUtil.retrofit;
    }
}