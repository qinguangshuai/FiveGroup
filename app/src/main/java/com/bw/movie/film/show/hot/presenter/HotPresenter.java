package com.bw.movie.film.show.hot.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.film.show.hot.bean.HotPlayBean;
import com.bw.movie.film.show.hot.callback.HotPlayCallBack;
import com.bw.movie.film.show.hot.model.HotModel;
import com.bw.movie.util.HttpCallBack;

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
        hotModel.getHotPlayBeanObservable(page, count, new HttpCallBack<HotPlayBean>() {
            @Override
            public void onSuccess(HotPlayBean name) {
                getiBaseView().onDataSuccess(name);
            }

            @Override
            public void onFailer(String result) {
                getiBaseView().onDataFailer(result);
            }
        });
    }

}
