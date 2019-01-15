package com.bw.movie.my.mysound;

import com.bw.movie.base.BaseModel;
import com.bw.movie.base.BaseObserver;
import com.bw.movie.net.HttpCallBack;
import com.bw.movie.net.OkHttpUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * date:2018/12/30    8:34
 * author:Therefore(Lenovo)
 * fileName:MySoundModel
 */
public class UpdateSoundModel extends BaseModel {
    public void getSound(int id, final HttpCallBack<UpdateSoundUser> httpCallBack) {
        OkHttpUtil.get().createa(UpdateSoundService.class).getUpdate(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new BaseObserver<UpdateSoundUser>(httpCallBack));
    }
}
