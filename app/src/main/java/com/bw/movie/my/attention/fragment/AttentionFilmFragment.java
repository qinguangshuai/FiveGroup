package com.bw.movie.my.attention.fragment;


import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bw.movie.R;
import com.bw.movie.base.BaseEvent;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.cinema.fragment.ChuanUser;
import com.bw.movie.film.popwindow.ScrollWindow;
import com.bw.movie.login.LoginActivity;
import com.bw.movie.my.attention.adapter.AttFilmAdapter;
import com.bw.movie.my.attention.bean.MyAttFilmUser;
import com.bw.movie.my.attention.bean.ResultBean;
import com.bw.movie.my.attention.presenter.AttFilmPresenter;
import com.bw.movie.util.RecyclerViewScrollUtil;
import com.bw.movie.wxapi.WXEntryActivity;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class AttentionFilmFragment extends BaseFragment implements IBaseView<MyAttFilmUser> {

    @BindView(R.id.attenrecycle2)
    RecyclerView mAttenrecycle2;
    Unbinder mUnbinder;
    @BindView(R.id.attenSwipeRefreshLayout2)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private AttFilmPresenter mAttFilmPresenter;
    int page = 1;
    private List<ResultBean> mList;
    private ScrollWindow mScrollWindow = new ScrollWindow(getActivity());
    @Override
    public void initView() {
        mUnbinder = ButterKnife.bind(this, rootView);
        showloading();
        BaseEvent.register(this);
    }

    @Override
    public void initListener() {

    }

    @Subscribe
    public void getChuan(ChuanUser chuanUser) {
        Intent intent = new Intent(getActivity(),LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
//        AppManager.getAppManager().finishAllActivity();
    }


    @Override
    public void initData() {
        mAttFilmPresenter.getFilm(page);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                showloading();
                mAttFilmPresenter.getFilm(page);
            }
        });
        RecyclerViewScrollUtil.Scroll(mAttenrecycle2, true, new RecyclerViewScrollUtil.onEvent() {
            @Override
            public void info() {
                showloading();
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
        mUnbinder.unbind();
        BaseEvent.unregister(this);
    }

    @Override
    public void onDataSuccess(MyAttFilmUser myAttFilmUser) {
        showContent();
        mSwipeRefreshLayout.setRefreshing(false);
        mList = myAttFilmUser.getResult();
        if (mList!=null && mList.size()>0){
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            mAttenrecycle2.setLayoutManager(linearLayoutManager);

            AttFilmAdapter attFilmAdapter = new AttFilmAdapter(getContext(), mList);
            attFilmAdapter.setHttpClick(new AttFilmAdapter.HttpClick() {
                @Override
                public void getClick(View view, int position) {
                    startActivity(new Intent(getActivity(),WXEntryActivity.class));
                    getActivity().finish();
                }
            });
            mAttenrecycle2.setAdapter(attFilmAdapter);
        }else{
            showEmpty();
        }

        mSwipeRefreshLayout.setRefreshing(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mScrollWindow.dismissPop();
            }
        },1000);
    }

    @Override
    public void onDataFailer(String msg) {
         showEmpty();
    }

    @Override
    public void onShowLoading() {

    }

    @Override
    public void onHideLoading() {

    }
}
