package com.bw.movie.cinema.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bw.movie.R;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.cinema.adapter.NeightbourAdapder;
import com.bw.movie.cinema.bean.NeightbourBean;
import com.bw.movie.cinema.prosenter.NeightbourPresenter;
import com.bw.movie.cinema.view.NeightbourView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;




/**
 * 推荐影院
 */
public class NeighbouringFragment extends BaseFragment implements NeightbourView<NeightbourBean> {
    @BindView(R.id.recy_neightbor)
    RecyclerView recyNeightbor;
    Unbinder unbinder;

    @Override
    public void initView() {
        unbinder = ButterKnife.bind(this, rootView);
        NeightbourPresenter neightbourPresenter = new NeightbourPresenter(this);
        neightbourPresenter.getNeightbour(1, 10);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public int initLayoutId() {
        return R.layout.fragment_neighbouring;
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public void initVarisble() {

    }


    @Override
    public void onDataSuccess(NeightbourBean neightbourBean) {
        List<NeightbourBean.ResultBean.NearbyCinemaListBean> nearbyCinemaList = neightbourBean.getResult().getNearbyCinemaList();
        NeightbourAdapder neightbourAdapder = new NeightbourAdapder(nearbyCinemaList,getContext());
        recyNeightbor.setAdapter(neightbourAdapder);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyNeightbor.setLayoutManager(linearLayoutManager);
        neightbourAdapder.setGetListener(new NeightbourAdapder.getListener() {
            @Override
            public void getList(View view, int position) {

            }
        });

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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
