package com.bw.movie.my.attention.service;

import com.bw.movie.my.attention.bean.MyAttFilmUser;
import com.bw.movie.util.UrlUtil;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * date:2018/12/28    15:11
 * author:Therefore(Lenovo)
 * fileName:AttCinemaService
 */
public interface AttFilmService {
    @GET(UrlUtil.FINDMOVIEPAGELIST)
    Observable<MyAttFilmUser> getFilm(@Query("page") int page);
}
