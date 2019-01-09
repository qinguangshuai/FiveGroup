package com.bw.movie.my.attention.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bw.movie.R;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.my.attention.adapter.AttFilmAdapter;
import com.bw.movie.my.attention.bean.MyAttFilmUser;
import com.bw.movie.my.attention.bean.ResultBean;
import com.bw.movie.my.attention.presenter.AttFilmPresenter;
import com.bw.movie.my.mysound.MySoundAdapter;
import com.bw.movie.util.RecyclerViewScrollUtil;
import com.bw.movie.wxapi.WXEntryActivity;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class AttentionFilmFragment extends BaseFragment implements IBaseView<MyAttFilmUser> {

    @BindView(R.id.attenrecycle2)
    RecyclerView mAttenrecycle2;
    Unbinder unbinder;
    @BindView(R.id.attenSwipeRefreshLayout2)
    SwipeRefreshLayout mSwipeRefreshLayout;
    Unbinder unbinder1;
    private AttFilmPresenter mAttFilmPresenter;
    int page = 1;
    private List<ResultBean> mList;

    @Override
    public void initView() {
        unbinder = ButterKnife.bind(this, rootView);

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        mAttFilmPresenter.getFilm(page);
        RecyclerViewScrollUtil.Refresh(mSwipeRefreshLayout, 2000, new RecyclerViewScrollUtil.onEvent() {
            @Override
            public void info() {
                mAttFilmPresenter.getFilm(page);
            }
        });

        RecyclerViewScrollUtil.Scroll(mAttenrecycle2, true, new RecyclerViewScrollUtil.onEvent() {
            @Override
            public void info() {
                mAttFilmPresenter.getFilm(page++);

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
        mAttenrecycle2.setLayoutManager(linearLayoutManager);
        mList = myAttFilmUser.getResult();
        AttFilmAdapter attFilmAdapter = new AttFilmAdapter(getContext(), mList);
        attFilmAdapter.setHttpClick(new AttFilmAdapter.HttpClick() {
            @Override
            public void getClick(View view, int position) {
                startActivity(new Intent(getActivity(),WXEntryActivity.class));
                getActivity().finish();
            }
        });
        mAttenrecycle2.setAdapter(attFilmAdapter);
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

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }
}
