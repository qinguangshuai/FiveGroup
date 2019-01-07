package com.bw.movie.film.synopsis.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.film.synopsis.bean.CommentBean;
import com.bw.movie.film.synopsis.bean.InputcommentsBean;
import com.bw.movie.film.synopsis.bean.PraiseBean;
import com.bw.movie.film.synopsis.callback.CommentCallBack;
import com.bw.movie.film.synopsis.callback.InputcommentsCallBack;
import com.bw.movie.film.synopsis.callback.PraiseCallBack;
import com.bw.movie.film.synopsis.model.SynopsisModel;

/*
 *作者:ash
 *TODO:
 *    P层
 */
public class SynopsisPresenter extends BasePresenter {


    private SynopsisModel synopsisModel;

    public SynopsisPresenter(IBaseView iBaseView) {
        super(iBaseView);
        synopsisModel = new SynopsisModel();
    }


    //评论
    public void getCommentBeanObservable(int id, int page, int count) {
        synopsisModel.getCommentBeanObservable(id, page, count, new CommentCallBack() {
            @Override
            public void success(CommentBean commentBean) {
                getiBaseView().onDataSuccess(commentBean);
            }

            @Override
            public void error(String message) {
                getiBaseView().onDataFailer(message);
            }
        });
    }

    //添加用户对评论的回复
    public void getInputcomments(int commentId, String replyContent) {
        synopsisModel.getInputcomments(commentId, replyContent, new InputcommentsCallBack() {
            @Override
            public void isInputcomments(InputcommentsBean inputcommentsBean) {
                getiBaseView().onDataSuccess(inputcommentsBean);
            }
        });
    }


    //点赞
    public void getPraiseBeanObservable(int id) {
        synopsisModel.getPraiseBeanObservable(id, new PraiseCallBack() {
            @Override
            public void success(PraiseBean praiseBean) {
                getiBaseView().onDataSuccess(praiseBean);
            }

            @Override
            public void error(String message) {
                getiBaseView().onDataFailer(message);
            }
        });
    }

}
