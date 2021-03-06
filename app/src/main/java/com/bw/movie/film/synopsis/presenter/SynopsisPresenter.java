package com.bw.movie.film.synopsis.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.film.synopsis.bean.CommentBean;
import com.bw.movie.film.synopsis.bean.InputcommentsBean;
import com.bw.movie.film.synopsis.bean.PraiseBean;
import com.bw.movie.film.synopsis.model.SynopsisModel;
import com.bw.movie.net.HttpCallBack;

/*
 *作者:ash
 *TODO:
 *    P层
 */
public class SynopsisPresenter extends BasePresenter {


    private SynopsisModel mSynopsisModel;

    public SynopsisPresenter(IBaseView iBaseView) {
        super(iBaseView);
        mSynopsisModel = new SynopsisModel();
    }


    //评论
    public void getCommentBeanObservable(int id, int page, int count) {
        mSynopsisModel.getCommentBeanObservable(id, page, count, new HttpCallBack<CommentBean>() {
            @Override
            public void onSuccess(CommentBean name) {
                getiBaseView().onDataSuccess(name);
            }

            @Override
            public void onFailer(String result) {
                getiBaseView().onDataFailer(result);
            }
        });
    }

    //添加用户对评论的回复
    public void getInputcomments(int commentId, String replyContent) {
        mSynopsisModel.getInputcomments(commentId, replyContent, new HttpCallBack<InputcommentsBean>() {
            @Override
            public void onSuccess(InputcommentsBean name) {
                getiBaseView().onDataSuccess(name);
            }

            @Override
            public void onFailer(String result) {
                getiBaseView().onDataFailer(result);
            }
        });
    }


    //点赞
    public void getPraiseBeanObservable(int id) {
        mSynopsisModel.getPraiseBeanObservable(id, new HttpCallBack<PraiseBean>() {
            @Override
            public void onSuccess(PraiseBean name) {
                getiBaseView().onDataSuccess(name);
            }

            @Override
            public void onFailer(String result) {
                getiBaseView().onDataFailer(result);
            }
        });
    }

}
