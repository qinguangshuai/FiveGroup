package com.bw.movie.my.updatehaed.service;



import com.bw.movie.my.updatehaed.bean.UpdateHeadEntity;
import com.bw.movie.util.UrlUtil;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
/*
*  修改头像service接口
* */
public interface UpdateHeadService {
    @POST(UrlUtil.UPDATEHEADPIC)
    @Multipart
    Observable<UpdateHeadEntity> getHead(@Part MultipartBody.Part image);
}
