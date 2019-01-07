package com.bw.movie.film.details.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.film.details.bean.DetailBean;
import com.bw.movie.film.details.callback.DetailCallBack;
import com.bw.movie.film.details.model.DetailModel;

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
    public void getDetailBeanObservable(int id){
        detailModel.getDetailBeanObservable(id, new DetailCallBack() {
            @Override
            public void success(DetailBean detailBean) {
                getiBaseView().onDataSuccess(detailBean);
            }

            @Override
            public void error(String message) {
                getiBaseView().onDataFailer(message);
            }
        });
    }

}
