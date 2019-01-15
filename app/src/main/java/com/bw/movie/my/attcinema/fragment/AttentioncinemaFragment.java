package com.bw.movie.my.attcinema.fragment;


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
import com.bw.movie.error.AppManager;
import com.bw.movie.film.popwindow.ScrollWindow;
import com.bw.movie.login.LoginActivity;
import com.bw.movie.my.attcinema.adapter.AttCinemaAdapter;
import com.bw.movie.my.attcinema.bean.AttCinemaUser;
import com.bw.movie.my.attcinema.bean.ResultBean;
import com.bw.movie.my.attcinema.presenter.AttCinemaPresenter;
import com.bw.movie.util.RecyclerViewScrollUtil;
import com.bw.movie.wxapi.WXEntryActivity;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
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
    Unbinder mUnbinder;
    private AttCinemaPresenter mAttCinemaPresenter;
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
        mAttCinemaPresenter.getCinema(page);

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
                mScrollWindow.showPop(mAttenrecycle1);
                mAttCinemaPresenter.getCinema(page++);

            }
        });

    }

    @Subscribe
    public void getChuan(ChuanUser chuanUser) {
        Intent intent = new Intent(getActivity(),LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
        AppManager.getAppManager().finishAllActivity();
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
        mUnbinder.unbind();
        BaseEvent.unregister(this);
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
