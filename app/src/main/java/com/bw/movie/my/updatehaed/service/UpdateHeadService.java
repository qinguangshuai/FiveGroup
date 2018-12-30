package com.bw.movie.my.updatehaed.service;

import android.database.Observable;

import com.bw.movie.my.updatehaed.bean.UpdateHeadEntity;

import okhttp3.MultipartBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface UpdateHeadService {
    @POST("user/v1/verify/uploadHeadPic")
    @Multipart
    Observable<UpdateHeadEntity> getHead(@Part MultipartBody image);
}
