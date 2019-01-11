package com.bw.movie.my.ticket.fragment;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.my.ticket.adapter.TicketInforAdapter;
import com.bw.movie.my.ticket.bean.ResultBean;
import com.bw.movie.my.ticket.bean.TicketFoemationEntity;
import com.bw.movie.my.ticket.prosenter.TicketformationPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class TicketFragmentTwo extends BaseFragment implements IBaseView<TicketFoemationEntity> {

    @BindView(R.id.ticket_oneRecycler)
    RecyclerView ticketOneRecycler;
    Unbinder unbinder;
    int page = 1;
    private TicketformationPresenter mTicketformationPresenter;
    private List<ResultBean> list;

    @Override
    public void initView() {
        unbinder = ButterKnife.bind(this, rootView);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        mTicketformationPresenter.getTicet(page,5);
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
        list = ticketFoemationEntity.getResult();
        if (list !=null && list.size()>0){
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            ticketOneRecycler.setLayoutManager(linearLayoutManager);

            TicketInforAdapter attFilmAdapter = new TicketInforAdapter(list,getContext());
            ticketOneRecycler.setAdapter(attFilmAdapter);
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
