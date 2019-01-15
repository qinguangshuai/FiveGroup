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
import com.bw.movie.util.ToastUtil;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class TickFragmentOne extends BaseFragment implements IBaseView<TicketFoemationEntity> {

    private ScrollWindow mScrollWindow = new ScrollWindow(getActivity());
    @BindView(R.id.ticket_oneRecycler)
    RecyclerView ticketOneRecycler;
    Unbinder mUnbinder;
    @BindView(R.id.ticketSwipeRefreshLayout)
    SwipeRefreshLayout ticketSwipeRefreshLayout;
    private TicketformationPresenter mTicketformationPresenter;
    int page = 1;
    private List<ResultBean> mList;

    @Override
    public void initView() {
        mUnbinder = ButterKnife.bind(this, rootView);
        BaseEvent.register(this);
    }

    @Override
    public void initListener() {
        ticketSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                showloading();
                mTicketformationPresenter.getTicet(page++,5);
            }
        });

        RecyclerViewScrollUtil.Scroll(ticketOneRecycler, true, new RecyclerViewScrollUtil.onEvent() {
            @Override
            public void info() {
                showloading();
                mScrollWindow.showPop(ticketOneRecycler);
                mTicketformationPresenter.getTicet(page++,5);
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
        mUnbinder.unbind();
        BaseEvent.unregister(this);
    }

    @Override
    public void onDataSuccess(TicketFoemationEntity ticketFoemationEntity) {
        mList = ticketFoemationEntity.getResult();
        if (mList != null && mList.size() > 0) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            ticketOneRecycler.setLayoutManager(linearLayoutManager);

            TicketInforAdapter attFilmAdapter = new TicketInforAdapter(mList, getContext());
            ticketOneRecycler.setAdapter(attFilmAdapter);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mScrollWindow.dismissPop();
                }
            },1000);
            ticketSwipeRefreshLayout.setRefreshing(false);
        } else {
            showEmpty();
        }
    }

    @Override
    public void onDataFailer(String msg) {
        ToastUtil.Toast(msg);
        showEmpty();
    }

    @Override
    public void onShowLoading() {

    }

    @Override
    public void onHideLoading() {

    }

}
