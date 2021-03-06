package com.bw.movie.film.synopsis.model;

import android.os.Handler;
import com.bw.movie.base.BaseEvent;
import com.bw.movie.base.BaseModel;
import com.bw.movie.base.BaseObserver;
import com.bw.movie.cinema.fragment.ChuanUser;
import com.bw.movie.film.synopsis.bean.CommentBean;
import com.bw.movie.film.synopsis.bean.InputcommentsBean;
import com.bw.movie.film.synopsis.bean.PraiseBean;
import com.bw.movie.film.synopsis.service.SynopsisService;
import com.bw.movie.net.HttpCallBack;
import com.bw.movie.net.OkHttpUtil;
import com.bw.movie.util.ToastUtil;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/*
 *作者:ash
 *TODO:
 *   model 层
 */
public class SynopsisModel extends BaseModel {

    //显示评论
    public void getCommentBeanObservable(int id, int page, int count, final HttpCallBack<CommentBean> httpCallBack) {
        OkHttpUtil
                .get()
                .createa(SynopsisService.class)
                .getCommentBeanObservable(id, page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<CommentBean>(httpCallBack));
    }


    //点赞
    public void getPraiseBeanObservable(int id, final HttpCallBack<PraiseBean> httpCallBack) {
        OkHttpUtil
                .get()
                .createa(SynopsisService.class)
                .getPraiseBeanObservable(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new BaseObserver<PraiseBean>(httpCallBack){
                    @Override
                    public void onNext(PraiseBean praiseBean) {
                        if (praiseBean.getStatus().equals("9999")) {
                            ToastUtil.Toast("要想使用,请先登录");
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    BaseEvent.post(new ChuanUser());
                                }
                            }, 1000);
                        } else {
                            super.onNext(praiseBean);
                        }
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
                .subscribe(new BaseObserver<InputcommentsBean>(inputcommentsBeanHttpCallBack){
                    @Override
                    public void onNext(InputcommentsBean inputcommentsBean) {
                        if (inputcommentsBean.getStatus().equals("9999")) {
                            ToastUtil.Toast("要想使用,请先登录");
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    BaseEvent.post(new ChuanUser());
                                }
                            }, 100);
                        } else {
                            super.onNext(inputcommentsBean);
                        }
                    }
                });
    }

}


