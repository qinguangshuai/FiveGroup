package com.bw.movie.my.attcinema.fragment;


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
import com.bw.movie.my.attcinema.adapter.AttCinemaAdapter;
import com.bw.movie.my.attcinema.bean.AttCinemaUser;
import com.bw.movie.my.attcinema.bean.ResultBean;
import com.bw.movie.my.attcinema.presenter.AttCinemaPresenter;
import com.bw.movie.util.RecyclerViewScrollUtil;
import com.bw.movie.util.ToastUtil;
import com.bw.movie.wxapi.WXEntryActivity;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 秦广帅
 * 查询用户关注的影院信息
 */
public class AttentioncinemaFragment extends BaseFragment implements IBaseView<AttCinemaUser> {

    @BindView(R.id.attenrecycle1)
    RecyclerView mAttenrecycle1;
    @BindView(R.id.attenSwipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    Unbinder unbinder;
    private AttCinemaPresenter mAttCinemaPresenter;
    int page = 1;
    private List<ResultBean> mList;

    @Override
    public void initView() {
        unbinder = ButterKnife.bind(this, rootView);
        showloading();
    }

    @Override
    public void initListener() {
        mAttCinemaPresenter.getCinema(page);

//        RecyclerViewScrollUtil.Refresh(mSwipeRefreshLayout, 2000, new RecyclerViewScrollUtil.onEvent() {
//            @Override
//            public void info() {
//             showloading();
//                mAttCinemaPresenter.getCinema(page);
//
//            }
//        });
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                showloading();
              mAttCinemaPresenter.getCinema(page);
            }
        });
        RecyclerViewScrollUtil.Scroll(mAttenrecycle1, true, new RecyclerViewScrollUtil.onEvent() {
            @Override
            public void info() {
                showloading();
                mAttCinemaPresenter.getCinema(page++);

            }
        });

    }

    @Override
    public void initData() {

    }

    @Override
    public int initLayoutId() {
        return R.layout.fragment_attentioncinema;
    }

    @Override
    public BasePresenter initPresenter() {
        mAttCinemaPresenter = new AttCinemaPresenter(this);
        return mAttCinemaPresenter;
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
    public void onDataSuccess(AttCinemaUser attCinemaUser) {
        showContent();
        mSwipeRefreshLayout.setRefreshing(false);
        mList = attCinemaUser.getResult();
        if (mList!=null && mList.size()>0){
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            mAttenrecycle1.setLayoutManager(linearLayoutManager);
            AttCinemaAdapter attCinemaAdapter = new AttCinemaAdapter(getContext(), mList);
            attCinemaAdapter.setHttpClick(new AttCinemaAdapter.HttpClick() {
                @Override
                public void getClick(View view, int position) {
                    startActivity(new Intent(getActivity(), WXEntryActivity.class));
                    getActivity().finish();
                }
            });
            mAttenrecycle1.setAdapter(attCinemaAdapter);
        }else{
            showEmpty();
        }

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
}
