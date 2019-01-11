package com.bw.movie.film.show.playing.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.film.show.playing.playing.PlayingBean;
import com.bw.movie.film.show.playing.model.PlayingModel;
import com.bw.movie.util.HttpCallBack;

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
        playingModel.getPlayingBeanObservable(page, count, new HttpCallBack<PlayingBean>() {
            @Override
            public void onSuccess(PlayingBean name) {
                getiBaseView().onDataSuccess(name);
            }

            @Override
            public void onFailer(String result) {

            }
        });
    }

}
