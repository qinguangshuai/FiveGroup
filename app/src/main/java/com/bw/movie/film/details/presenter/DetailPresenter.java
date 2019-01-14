package com.bw.movie.film.details.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.film.details.bean.DetailBean;
import com.bw.movie.film.details.model.DetailModel;
import com.bw.movie.net.HttpCallBack;

/*
 *作者:ash
 *TODO:
 *    P层
 */
public class DetailPresenter extends BasePresenter {

    private DetailModel detailModel;

    public DetailPresenter(IBaseView iBaseView) {
        super(iBaseView);
        detailModel = new DetailModel();
    }

    //Id查询详情
    public void getDetailBeanObservable(int id) {
        detailModel.getDetailBeanObservable(id, new HttpCallBack<DetailBean>() {
            @Override
            public void onSuccess(DetailBean name) {
                getiBaseView().onDataSuccess(name);
            }

            @Override
            public void onFailer(String result) {
                getiBaseView().onDataFailer(result);
            }
        });
    }

}
