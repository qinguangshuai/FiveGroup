package com.bw.movie.film.show.playing.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.film.show.playing.playing.PlayingBean;
import com.bw.movie.film.show.playing.callback.PlayingCallBack;
import com.bw.movie.film.show.playing.model.PlayingModel;

/*
 *作者:ash
 *TODO:
 *    P层
 */
public class PlayingPresenter extends BasePresenter {


    private PlayingModel playingModel;

    public PlayingPresenter(IBaseView iBaseView) {
        super(iBaseView);
        playingModel = new PlayingModel();
    }

    //正在上映 || 即将上映 请求 回调
    public void getPlayingBeanObservable(int page, int count){
        playingModel.getPlayingBeanObservable(page, count, new PlayingCallBack() {
            @Override
            public void success(PlayingBean playingBean) {
                getiBaseView().onDataSuccess(playingBean);
            }

            @Override
            public void error(String message) {
                getiBaseView().onDataFailer(message);
            }
        });
    }

}
