package com.bw.movie.my.ticket.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BaseRecyclerAdapter;
import com.bw.movie.my.ticket.adapter.TicketInforAdapter;
import com.bw.movie.my.ticket.bean.ResultBean;
import com.bw.movie.my.ticket.bean.TicketFoemationEntity;
import com.bw.movie.my.ticket.prosenter.TicketformationPresenter;
import com.bw.movie.my.ticket.view.TicketformationView;
import com.bw.movie.util.WeiXinUtil;
import com.bw.movie.wxapi.bean.OrderSuccessBean;
import com.bw.movie.wxapi.presenter.OrderSuccessPresenter;
import com.bw.movie.wxapi.view.OrderSuccessView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Ticket_nformationActivity extends BaseActivity<TicketformationPresenter> implements TicketformationView<TicketFoemationEntity> {


    @BindView(R.id.ticketRecycler)
    XRecyclerView mTicketRecycler;
    @BindView(R.id.my_ticket)
    ImageView myTicket;
    private TicketformationPresenter presenter;
    private int page = 1;
    private List<ResultBean> result;

    @Override
    public void initView() {
        presenter = new TicketformationPresenter(this);
        presenter.getTicet(page, 5);
        ButterKnife.bind(this);
    }

    @Override
    public void initListener() {
        mTicketRecycler.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        presenter.getTicet(page, 5);
                        mTicketRecycler.refreshComplete();
                    }
                },2000);
            }

            @Override
            public void onLoadMore() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        presenter.getTicet(page++, 5);
                        mTicketRecycler.loadMoreComplete();
                    }
                },2000);
            }
        });
    }

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    public void initData() {

    }

    @Override
    public int initLayoutId() {
        return R.layout.activity_ticket_nformation;
    }

    @Override
    public void initVariable() {

    }

    @Override
    public TicketformationPresenter initPresenter() {
        return null;
    }


    @Override
    public void onDataSuccess(final TicketFoemationEntity ticketFoemationEntity) {
        result = ticketFoemationEntity.getResult();
        mTicketRecycler.setLayoutManager(new LinearLayoutManager(this));
        TicketInforAdapter inforAdapter = new TicketInforAdapter(result, this);
        inforAdapter.setHttpClick(new TicketInforAdapter.HttpClick() {
            @Override
            public void click(View view, int position) {
                new OrderSuccessPresenter(new OrderSuccessView<OrderSuccessBean>() {

                    @Override
                    public void onDataSuccess(OrderSuccessBean orderSuccessBean) {
                        Toast.makeText(Ticket_nformationActivity.this, orderSuccessBean.getMessage(), Toast.LENGTH_SHORT).show();
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
                }).getOeder(1,ticketFoemationEntity.getResult().get(0).getOrderId());
            }
        });
        mTicketRecycler.setAdapter(inforAdapter);
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


    @OnClick(R.id.ticketRecycler)
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.ticketRecycler:
                break;
        }
    }

    @OnClick(R.id.my_ticket)
    public void onViewClicked() {
        finish();
    }
}
