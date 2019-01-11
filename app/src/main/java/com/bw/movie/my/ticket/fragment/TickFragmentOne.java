package com.bw.movie.my.ticket.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.film.popwindow.ScrollWindow;
import com.bw.movie.my.ticket.adapter.TicketInforAdapter;
import com.bw.movie.my.ticket.bean.ResultBean;
import com.bw.movie.my.ticket.bean.TicketFoemationEntity;
import com.bw.movie.my.ticket.prosenter.TicketformationPresenter;
import com.bw.movie.util.RecyclerViewScrollUtil;
import com.bw.movie.util.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class TickFragmentOne extends BaseFragment implements IBaseView<TicketFoemationEntity> {

    private ScrollWindow mScrollWindow = new ScrollWindow(getActivity());
    @BindView(R.id.ticket_oneRecycler)
    RecyclerView ticketOneRecycler;
    Unbinder unbinder;
    @BindView(R.id.ticketSwipeRefreshLayout)
    SwipeRefreshLayout ticketSwipeRefreshLayout;
    Unbinder unbinder1;
    private TicketformationPresenter mTicketformationPresenter;
    int page = 1;
    private List<ResultBean> list;

    @Override
    public void initView() {
        unbinder = ButterKnife.bind(this, rootView);
    }

    @Override
    public void initListener() {
        ticketSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mTicketformationPresenter.getTicet(page++,5);
            }
        });

        RecyclerViewScrollUtil.Scroll(ticketOneRecycler, true, new RecyclerViewScrollUtil.onEvent() {
            @Override
            public void info() {
                mScrollWindow.showPop(ticketOneRecycler);
                mTicketformationPresenter.getTicet(page++,5);
            }
        });

    }

    @Override
    public void initData() {
        mTicketformationPresenter.getTicet(page, 5);
    }

    @Override
    public int initLayoutId() {
        return R.layout.fragment_tick_fragment_one;
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDataSuccess(TicketFoemationEntity ticketFoemationEntity) {
        list = ticketFoemationEntity.getResult();
        if (list != null && list.size() > 0) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            ticketOneRecycler.setLayoutManager(linearLayoutManager);

            TicketInforAdapter attFilmAdapter = new TicketInforAdapter(list, getContext());
            ticketOneRecycler.setAdapter(attFilmAdapter);
        } else {
            showEmpty();
        }
    }

    @Override
    public void onDataFailer(String msg) {
        ToastUtil.Toast(msg);
    }

    @Override
    public void onShowLoading() {

    }

    @Override
    public void onHideLoading() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }
}
