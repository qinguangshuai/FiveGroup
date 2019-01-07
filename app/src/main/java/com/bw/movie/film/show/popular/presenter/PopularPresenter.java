package com.bw.movie.film.show.popular.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.film.show.popular.bean.PopularBean;
import com.bw.movie.film.show.popular.callback.PopularCallBack;
import com.bw.movie.film.show.popular.model.PopularModel;

/*
 *作者:ash
 *TODO:
 *    P层
 */
public class PopularPresenter extends BasePresenter {


    private PopularModel popularModel;

    public PopularPresenter(IBaseView iBaseView) {
        super(iBaseView);
        popularModel = new PopularModel();
    }



    //热门电影请求回调
    public void getPopularBeanObservable(int page, int count){
        popularModel.getPopularBeanObservable(page, count, new PopularCallBack() {
            @Override
            public void success(PopularBean popularBean) {
                getiBaseView().onDataSuccess(popularBean);
            }

            @Override
            public void error(String message) {
                getiBaseView().onDataFailer(message);
            }
        });
    }






}
