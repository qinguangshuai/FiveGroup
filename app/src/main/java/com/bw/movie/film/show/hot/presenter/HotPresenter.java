package com.bw.movie.film.show.hot.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.film.show.hot.bean.HotPlayBean;
import com.bw.movie.film.show.hot.callback.HotPlayCallBack;
import com.bw.movie.film.show.hot.model.HotModel;

/*
 *作者:ash
 *TODO:
 *   热门电影  P层
 */
public class HotPresenter extends BasePresenter {


    private HotModel hotModel;

    public HotPresenter(IBaseView iBaseView) {
        super(iBaseView);
        hotModel = new HotModel();
    }


    //正在热映 请求回调
    public void getHotPlayBeanObservable(int page, int count){
        hotModel.getHotPlayBeanObservable(page, count, new HotPlayCallBack() {
            @Override
            public void success(HotPlayBean hotPlayBean) {
                getiBaseView().onDataSuccess(hotPlayBean);
            }

            @Override
            public void error(String message) {
                getiBaseView().onDataFailer(message);
            }
        });
    }





}
