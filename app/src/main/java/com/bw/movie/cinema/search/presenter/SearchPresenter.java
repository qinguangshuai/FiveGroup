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
    private SearchModel mSearchModel;

    public SearchPresenter(IBaseView iBaseView) {
        super(iBaseView);
        mSearchModel = new SearchModel();
    }

    public void getSreach(int page, int count, String cinemaName) {
        mSearchModel.getSearch(page, count, cinemaName, new HttpCallBack<SearchBean>() {
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
