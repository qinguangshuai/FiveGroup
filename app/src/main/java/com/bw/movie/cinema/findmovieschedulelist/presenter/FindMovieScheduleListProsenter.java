package com.bw.movie.cinema.findmovieschedulelist.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.cinema.findmovieschedulelist.bean.FindMovieScheduleListBean;
import com.bw.movie.cinema.findmovieschedulelist.model.FindMovieScheduleListModel;

/**
 * date:2018/12/27    18:56
 * author:张文龙(张文龙)
 * fileName:FindMovieScheduleListProsenter
 */
public class  FindMovieScheduleListProsenter extends BasePresenter {


     private FindMovieScheduleListModel findMovieScheduleListModel;
    public FindMovieScheduleListProsenter(IBaseView iBaseView) {
        super(iBaseView);
        findMovieScheduleListModel = new FindMovieScheduleListModel();
    }
    public void getFindMovieScheduleList(int cinemasId, int movieId){
        findMovieScheduleListModel.getFindMovieScheduleList(cinemasId, movieId, new FindMovieScheduleListModel.isFindMovieScheduleList() {
            @Override
            public void getFindMovieSchedule(FindMovieScheduleListBean findMovieScheduleListBean) {
                getiBaseView().onDataSuccess(findMovieScheduleListBean);
            }
        });
    }
}
