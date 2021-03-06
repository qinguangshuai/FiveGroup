package com.bw.movie.my.ticket.fragment;


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
import com.bw.movie.my.ticket.adapter.TicketInforAdapter;
import com.bw.movie.my.ticket.bean.ResultBean;
import com.bw.movie.my.ticket.bean.TicketFoemationEntity;
import com.bw.movie.my.ticket.prosenter.TicketformationPresenter;
import com.bw.movie.util.RecyclerViewScrollUtil;
import com.bw.movie.util.ToastUtil;
import com.bw.movie.util.WeiXinUtil;
import com.bw.movie.wxapi.bean.OrderSuccessBean;
import com.bw.movie.wxapi.presenter.OrderSuccessPresenter;
import com.bw.movie.wxapi.view.OrderSuccessView;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 已付款
 * */

public class TicketFragmentTwo extends BaseFragment implements IBaseView<TicketFoemationEntity> {


    Unbinder unbinder;
    int page = 1;
    @BindView(R.id.ticket_twoRecycler)
    RecyclerView mRecyclerView;
    @BindView(R.id.ticketSwipeRefreshLayout2)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private TicketformationPresenter mTicketformationPresenter;
    private List<ResultBean> list;
    private ScrollWindow mScrollWindow = new ScrollWindow(getActivity());

    @Override
    public void initView() {
        unbinder = ButterKnife.bind(this, rootView);
        BaseEvent.register(this);
        showloading();
    }

    @Override
    public void initListener() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mTicketformationPresenter.getTicet(page, 5,2);
            }
        });

        RecyclerViewScrollUtil.Scroll(mRecyclerView, true, new RecyclerViewScrollUtil.onEvent() {
            @Override
            public void info() {
                mScrollWindow.showPop(mRecyclerView);
                mTicketformationPresenter.getTicet(page++, 5,2);
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
        mTicketformationPresenter.getTicet(page, 5,2);
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
        showContent();
        list = ticketFoemationEntity.getResult();
        if (list != null && list.size() > 0) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            mRecyclerView.setLayoutManager(linearLayoutManager);
            TicketInforAdapter attFilmAdapter = new TicketInforAdapter(list, getContext());
            attFilmAdapter.setHttpClick(new TicketInforAdapter.HttpClick() {
                @Override
                public void click(View view, int position) {
                    new OrderSuccessPresenter(new OrderSuccessView<OrderSuccessBean>() {
                        @Override
                        public void onDataSuccess(OrderSuccessBean orderSuccessBean) {
                            WeiXinUtil.weiXinPay(orderSuccessBean);

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
                    }).getOeder(1, list.get(position).getOrderId());
                }
            });
            mRecyclerView.setAdapter(attFilmAdapter);
            mSwipeRefreshLayout.setRefreshing(false);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mScrollWindow.dismissPop();
                }
            }, 1000);
        } else {
            ToastUtil.Toast("sorry,数据暂无更新");
        }
    }

    @Override
    public void onDataFailer(String msg) {
        showContent();
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
