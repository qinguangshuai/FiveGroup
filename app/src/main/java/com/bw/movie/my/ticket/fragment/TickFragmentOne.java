package com.bw.movie.my.ticket.fragment;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.BaseEvent;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.cinema.SeatSelectionActivity.activity.SeatSelectionActivity;
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
 * 待付款
 */

public class TickFragmentOne extends BaseFragment implements IBaseView<TicketFoemationEntity> {

    private ScrollWindow mScrollWindow = new ScrollWindow(getActivity());
    @BindView(R.id.ticket_oneRecycler)
    RecyclerView ticketOneRecycler;
    Unbinder unbinder;
    @BindView(R.id.ticketSwipeRefreshLayout)
    SwipeRefreshLayout ticketSwipeRefreshLayout;
    private TicketformationPresenter mTicketformationPresenter;
    int page = 1;
    private List<ResultBean> list;

    @Override
    public void initView() {
        unbinder = ButterKnife.bind(this, rootView);
        BaseEvent.register(this);
        showloading();
    }

    @Override
    public void initListener() {
        ticketSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mTicketformationPresenter.getTicet(page++,5,1);
            }
        });

        RecyclerViewScrollUtil.Scroll(ticketOneRecycler, true, new RecyclerViewScrollUtil.onEvent() {
            @Override
            public void info() {
                mScrollWindow.showPop(ticketOneRecycler);
                mTicketformationPresenter.getTicet(page++,5,1);
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
        mTicketformationPresenter.getTicet(page, 5,1);
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
        BaseEvent.unregister(this);
    }

    @Override
    public void onDataSuccess(final TicketFoemationEntity ticketFoemationEntity) {
        showContent();
        list = ticketFoemationEntity.getResult();
        if (list != null && list.size() > 0) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            ticketOneRecycler.setLayoutManager(linearLayoutManager);

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
            ticketOneRecycler.setAdapter(attFilmAdapter);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mScrollWindow.dismissPop();
                }
            },1000);
            ticketSwipeRefreshLayout.setRefreshing(false);
        } else {
           ToastUtil.Toast("sorry,数据为空");
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

}
