package com.bw.movie.cinema.search.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.cinema.search.bean.SearchBean;
import com.bw.movie.cinema.search.model.SearchModel;
import com.bw.movie.net.HttpCallBack;

/*
 *影院搜索页面(presenter)
 */
public class SearchPresenter extends BasePresenter {
    private SearchModel searchModel;

    public SearchPresenter(IBaseView iBaseView) {
        super(iBaseView);
        searchModel = new SearchModel();
    }

    public void getSreach(int page, int count, String cinemaName) {
        searchModel.getSearch(page, count, cinemaName, new HttpCallBack<SearchBean>() {
            @Override
            public void onSuccess(SearchBean name) {
                getiBaseView().onDataSuccess(name);
            }

            @Override
            public void onFailer(String result) {
                getiBaseView().onDataFailer(result);
            }
        });
    }
}
