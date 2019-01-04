package com.bw.movie.film.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.film.adapter.AffililaterdAdapter;
import com.bw.movie.film.bean.CinemaBean;
import com.bw.movie.film.p.FilmProsenter;
import com.bw.movie.film.v.CinemaView;
import com.bw.movie.util.EmptyUtil;
import com.bw.movie.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AffiliatedTheaterActivity extends BaseActivity {


    @BindView(R.id.title_affiliated)
    TextView mTitleAffiliated;
    @BindView(R.id.recy_affiliated)
    RecyclerView mRecyAffiliated;
    private int id;
    private EmptyUtil emptyUtil = new EmptyUtil();
    private ToastUtil toast = new ToastUtil();
    private String name;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        id = getIntent().getIntExtra("id", -1);
        name = getIntent().getStringExtra("name");
        mTitleAffiliated.setText(name);
        getData(id);
        setmRecyAffiliated();
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public int initLayoutId() {
        return R.layout.activity_affiliated_theater;
    }

    @Override
    public void initVariable() {

    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }


    //setRecyclerView 数据
    public void setmRecyAffiliated() {

    }

    //请求数据
    public void getData(int id) {
        new FilmProsenter(new CinemaView<CinemaBean>() {
            @Override
            public void onDataSuccess(CinemaBean cinemaBean) {
                setAdapter(cinemaBean);
            }

            @Override
            public void onDataFailer(String msg) {
                new ToastUtil().Toast(msg);
            }

            @Override
            public void onShowLoading() {

            }

            @Override
            public void onHideLoading() {

            }
        }).getCinemaBeanObservable(id);
    }


    //adapter
    public void setAdapter(final CinemaBean cinemaBean) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyAffiliated.setLayoutManager(linearLayoutManager);
        AffililaterdAdapter affililaterdAdapter = new AffililaterdAdapter();
        affililaterdAdapter.setCinemaBean(cinemaBean);
        mRecyAffiliated.setAdapter(affililaterdAdapter);

    }



}
