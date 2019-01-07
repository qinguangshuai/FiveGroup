package com.bw.movie.my.attcinema.fragment;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bw.movie.R;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.my.attcinema.adapter.AttCinemaAdapter;
import com.bw.movie.my.attcinema.bean.AttCinemaUser;
import com.bw.movie.my.attcinema.presenter.AttCinemaPresenter;
import com.bw.movie.my.attcinema.view.AttCinemaView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 秦广帅
 * 查询用户关注的影院信息
 */
public class AttentioncinemaFragment extends BaseFragment implements AttCinemaView<AttCinemaUser> {

    @BindView(R.id.attenrecycle1)
    RecyclerView attenrecycle1;
    @BindView(R.id.attenimage1)
    ImageView attenimage1;
    Unbinder unbinder;
    private AttCinemaPresenter mAttCinemaPresenter;
    private List<AttCinemaUser.ResultBean> mList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        mAttCinemaPresenter = new AttCinemaPresenter(this);
        mAttCinemaPresenter.getCinema(1);
        return view;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {

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
        return null;
    }

    @Override
    public void initVarisble() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.attenimage1)
    public void onViewClicked() {
        getActivity().finish();
    }

    @Override
    public void onDataSuccess(AttCinemaUser attCinemaUser) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        attenrecycle1.setLayoutManager(linearLayoutManager);
        mList = attCinemaUser.getResult();
        AttCinemaAdapter attCinemaAdapter = new AttCinemaAdapter(getContext(),mList);
        attenrecycle1.setAdapter(attCinemaAdapter);
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
