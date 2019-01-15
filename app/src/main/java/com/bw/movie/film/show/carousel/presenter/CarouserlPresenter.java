package com.bw.movie.film.show.carousel.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.film.show.carousel.bean.CarouselBean;
import com.bw.movie.film.show.carousel.model.CarouselModel;
import com.bw.movie.net.HttpCallBack;

/*
 *作者:ash
 *TODO:
 *    P层
 */
public class CarouserlPresenter extends BasePresenter {

    private CarouselModel mCarouselModel;

    public CarouserlPresenter(IBaseView iBaseView) {
        super(iBaseView);
        mCarouselModel = new CarouselModel();
    }

    //轮播图请求回调
    public void getCarouselBeanObservable(int page, int count) {
        mCarouselModel.getCarouselBeanObservable(page, count, new HttpCallBack<CarouselBean>() {
            @Override
            public void onSuccess(CarouselBean name) {
                getiBaseView().onDataSuccess(name);
            }

            @Override
            public void onFailer(String result) {
                getiBaseView().onDataFailer(result);
            }
        });
    }

}
