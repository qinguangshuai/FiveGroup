package com.bw.movie.my.updatehaed.model;

import android.os.Handler;

import com.bw.movie.base.BaseEvent;
import com.bw.movie.base.BaseObserver;
import com.bw.movie.cinema.fragment.ChuanUser;
import com.bw.movie.my.updatehaed.bean.UpdateHeadEntity;
import com.bw.movie.my.updatehaed.service.UpdateHeadService;
import com.bw.movie.net.HttpCallBack;
import com.bw.movie.net.OkHttpUtil;
import com.bw.movie.util.ToastUtil;

import java.io.File;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/*
 *  修改头像model层
 * */
public class UpdateHeadModel {

    public void getHead(File file, final HttpCallBack<UpdateHeadEntity> httpCallBack) {
        //设置Content-Type:multipart/form-data
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        //设置Content-Disposition:form-data; name="photo"; filename="xuezhiqian.png"
        MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), requestFile);

        OkHttpUtil.get().createa(UpdateHeadService.class)
                .getHead(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<UpdateHeadEntity>(httpCallBack){
                    @Override
                    public void onNext(UpdateHeadEntity updateHeadEntity) {
                        if (updateHeadEntity.getStatus().equals("9999")) {
                            ToastUtil.Toast("要想使用,请先登录");
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    BaseEvent.post(new ChuanUser());
                                }
                            }, 100);
                        } else {
                            super.onNext(updateHeadEntity);
                        }
                    }
                });
    }

}
