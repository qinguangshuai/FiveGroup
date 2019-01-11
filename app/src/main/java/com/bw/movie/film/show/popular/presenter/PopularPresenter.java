package com.bw.movie.film.show.popular.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.film.show.popular.bean.PopularBean;
import com.bw.movie.film.show.popular.callback.PopularCallBack;
import com.bw.movie.film.show.popular.model.PopularModel;
import com.bw.movie.util.HttpCallBack;

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
        popularModel.getPopularBeanObservable(page, count, new HttpCallBack<PopularBean>() {
            @Override
            public void onSuccess(PopularBean name) {
                getiBaseView().onDataSuccess(name);
            }

            @Override
            public void onFailer(String result) {
                getiBaseView().onDataFailer(result);
            }
        });
    }

}
