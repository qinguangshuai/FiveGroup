package com.bw.movie.my.ticket.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.my.ticket.adapter.TicketInforAdapter;
import com.bw.movie.my.ticket.bean.TicketFoemationEntity;
import com.bw.movie.my.ticket.prosenter.TicketformationPresenter;
import com.bw.movie.my.ticket.view.TicketformationView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Ticket_nformationActivity extends BaseActivity<TicketformationPresenter> implements TicketformationView<TicketFoemationEntity> {


    @BindView(R.id.ticketRecycler)
    RecyclerView mTicketRecycler;
    @BindView(R.id.my_ticket)
    ImageView myTicket;
    private TicketformationPresenter presenter;

    @Override
    public void initView() {
        presenter = new TicketformationPresenter(this);
        presenter.getTicet(1, 5);
        ButterKnife.bind(this);
    }

    @Override
    public void initListener() {

    }

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
    public void onDataSuccess(TicketFoemationEntity ticketFoemationEntity) {
        List<TicketFoemationEntity.ResultBean> result = ticketFoemationEntity.getResult();
        mTicketRecycler.setLayoutManager(new LinearLayoutManager(this));
        mTicketRecycler.setAdapter(new TicketInforAdapter(result, this));
        Toast.makeText(getApplicationContext(), result.toString(), Toast.LENGTH_LONG).show();
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
