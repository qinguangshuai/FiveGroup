package com.bw.movie.cinema.mdetails.service;

import com.bw.movie.cinema.mdetails.bean.MdetailsBean;
import com.bw.movie.util.UrlUtil;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * date:2018/12/28    18:34
 * author:张文龙(张文龙)
 * fileName:MdetailsService
 */
public interface MdetailsService {
    @GET(UrlUtil.CINEMAMDETAILS)
    Observable<MdetailsBean>getMdtails(@Query("cinemaId") int cinemaId);
}
