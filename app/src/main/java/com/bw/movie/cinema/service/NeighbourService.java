package com.bw.movie.cinema.service;



import com.bw.movie.cinema.bean.neightbourbean.NeightbourBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/*
* NeighbourService
* */
public interface NeighbourService {
  @GET("cinema/v1/findRecommendCinemas")
  Observable<NeightbourBean> getNeightbour(@Query("page") int page, @Query("count") int count);


}
