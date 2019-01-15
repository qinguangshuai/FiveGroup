package com.bw.movie.cinema.Particulars;

import com.bw.movie.util.UrlUtil;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 *影院内容轮播接口
 */
public interface ParticularsService {
    @GET(UrlUtil.PARTICULARS)
    Observable<ParticularsBean> getParticulars(@Query("page") int page, @Query("count") int count);
}
