package com.bw.movie.film.synopsis.model;

import com.bw.movie.film.synopsis.bean.CommentBean;
import com.bw.movie.film.synopsis.bean.InputcommentsBean;
import com.bw.movie.film.synopsis.bean.PraiseBean;
import com.bw.movie.film.synopsis.service.SynopsisService;
import com.bw.movie.util.HttpCallBack;
import com.bw.movie.util.OkHttpUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/*
 *作者:ash
 *TODO:
 *   model 层
 */
public class SynopsisModel {

    //显示评论
    public void getCommentBeanObservable(int id, int page, int count, final HttpCallBack<CommentBean> httpCallBack) {
        OkHttpUtil
                .get()
                .createa(SynopsisService.class)
                .getCommentBeanObservable(id, page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CommentBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CommentBean commentBean) {
                        httpCallBack.onSuccess(commentBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        httpCallBack.onFailer("失败");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    //点赞
    public void getPraiseBeanObservable(int id, final HttpCallBack<PraiseBean> httpCallBack) {
        OkHttpUtil
                .get()
                .createa(SynopsisService.class)
                .getPraiseBeanObservable(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<PraiseBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PraiseBean praiseBean) {
                        httpCallBack.onSuccess(praiseBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        httpCallBack.onFailer("失败");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //添加用户对评论的回复
    public void getInputcomments(int commentId, String replyContent, final HttpCallBack<InputcommentsBean> inputcommentsBeanHttpCallBack) {
        OkHttpUtil
                .get()
                .createa(SynopsisService.class)
                .getInputcomments(commentId, replyContent)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<InputcommentsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(InputcommentsBean inputcommentsBean) {
                        inputcommentsBeanHttpCallBack.onSuccess(inputcommentsBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        inputcommentsBeanHttpCallBack.onFailer("失败");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}


