package com.bw.movie.cinema.recommend.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.cinema.recommend.bean.RecommendBean;
import com.bw.movie.cinema.recommend.model.RecommendModel;
import com.bw.movie.net.HttpCallBack;

public class RecommendPresenter extends BasePresenter {
    private RecommendModel mRecommendModel;

    public RecommendPresenter(IBaseView iBaseView) {
        super(iBaseView);
        mRecommendModel =new RecommendModel();
    }
    public void getRecommend(String longitude, String latitude, int page, int count){
         mRecommendModel.getRecommned(longitude, latitude, page, count, new HttpCallBack<RecommendBean>() {
             @Override
             public void onSuccess(RecommendBean name) {
                 getiBaseView().onDataSuccess(name);
             }

             @Override
             public void onFailer(String result) {
              getiBaseView().onDataFailer(result);
             }
         });
    }
}
