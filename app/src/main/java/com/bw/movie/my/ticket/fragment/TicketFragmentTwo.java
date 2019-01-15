package com.bw.movie.my.ticket.fragment;


import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.base.BaseEvent;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.cinema.fragment.ChuanUser;
import com.bw.movie.error.AppManager;
import com.bw.movie.film.popwindow.ScrollWindow;
import com.bw.movie.login.LoginActivity;
import com.bw.movie.my.ticket.adapter.TicketInforAdapter;
import com.bw.movie.my.ticket.bean.ResultBean;
import com.bw.movie.my.ticket.bean.TicketFoemationEntity;
import com.bw.movie.my.ticket.prosenter.TicketformationPresenter;
import com.bw.movie.util.RecyclerViewScrollUtil;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class TicketFragmentTwo extends BaseFragment implements IBaseView<TicketFoemationEntity> {

    Unbinder unbinder;
    int page = 1;
    @BindView(R.id.ticket_twoRecycler)
    RecyclerView mRecyclerView;
    @BindView(R.id.ticketSwipeRefreshLayout2)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private TicketformationPresenter mTicketformationPresenter;
    private List<ResultBean> mList;
    private ScrollWindow mScrollWindow = new ScrollWindow(getActivity());

    @Override
    public void initView() {
        unbinder = ButterKnife.bind(this, rootView);
        BaseEvent.register(this);
    }

    @Override
    public void initListener() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mTicketformationPresenter.getTicet(page, 5);
            }
        });

        RecyclerViewScrollUtil.Scroll(mRecyclerView, true, new RecyclerViewScrollUtil.onEvent() {
            @Override
            public void info() {
                mScrollWindow.showPop(mRecyclerView);
                mTicketformationPresenter.getTicet(page++, 5);
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
        mTicketformationPresenter.getTicet(page, 5);
    }

    @Override
    public int initLayoutId() {
        return R.layout.fragment_ticket_fragment_two;
    }

    @Override
    public BasePresenter initPresenter() {
        mTicketformationPresenter = new TicketformationPresenter(this);
        return mTicketformationPresenter;
    }

    @Override
    public void initVarisble() {

    }

    @Override
    public void onDataSuccess(TicketFoemationEntity ticketFoemationEntity) {
        mList = ticketFoemationEntity.getResult();
        if (mList != null && mList.size() > 0) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            mRecyclerView.setLayoutManager(linearLayoutManager);

            TicketInforAdapter attFilmAdapter = new TicketInforAdapter(mList, getContext());
            mRecyclerView.setAdapter(attFilmAdapter);
            mSwipeRefreshLayout.setRefreshing(false);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mScrollWindow.dismissPop();
                }
            }, 1000);
        } else {
            showEmpty();
        }
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


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        BaseEvent.unregister(this);
    }
}
