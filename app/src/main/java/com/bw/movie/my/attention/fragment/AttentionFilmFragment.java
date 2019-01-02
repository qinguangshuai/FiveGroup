package com.bw.movie.my.attention.fragment;


import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bw.movie.R;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.my.attention.adapter.AttFilmAdapter;
import com.bw.movie.my.attention.bean.MyAttFilmUser;
import com.bw.movie.my.attention.presenter.AttFilmPresenter;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class AttentionFilmFragment extends BaseFragment implements IBaseView<MyAttFilmUser> {

    @BindView(R.id.attenrecycle2)
    XRecyclerView attenrecycle2;
    @BindView(R.id.attenimage1)
    ImageView attenimage1;
    Unbinder unbinder;
    private AttFilmPresenter mAttFilmPresenter;
    private List<MyAttFilmUser.ResultBean> mList;
    int page = 1;

    @Override
    public void initView() {
        unbinder = ButterKnife.bind(this, rootView);
        attenrecycle2.setRefreshProgressStyle(ProgressStyle.BallZigZag);
        attenrecycle2.setArrowImageView(R.mipmap.jiazai);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        mAttFilmPresenter.getFilm(page);
        attenrecycle2.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mAttFilmPresenter.getFilm(page++);
                        attenrecycle2.refreshComplete();
                    }
                },2000);
            }

            @Override
            public void onLoadMore() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mAttFilmPresenter.getFilm(page);
                        attenrecycle2.loadMoreComplete();
                    }
                },2000);
            }
        });
    }

    @Override
    public int initLayoutId() {
        return R.layout.fragment_attention_film;
    }

    @Override
    public BasePresenter initPresenter() {
        mAttFilmPresenter = new AttFilmPresenter(this);
        return mAttFilmPresenter;
    }

    @Override
    public void initVarisble() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDataSuccess(MyAttFilmUser myAttFilmUser) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        attenrecycle2.setLayoutManager(linearLayoutManager);
        mList = myAttFilmUser.getResult();
        AttFilmAdapter attFilmAdapter = new AttFilmAdapter(getContext(),mList);
        attenrecycle2.setAdapter(attFilmAdapter);
    }

    @Override
    public void onDataFailer(String msg) {

    }

    @Override
    public void onShowLoading() {

    }

    @Override
    public void onHideLoading() {

    }

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
}
