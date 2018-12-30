package com.bw.movie.my.attention.fragment;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bw.movie.R;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.my.attention.adapter.AttFilmAdapter;
import com.bw.movie.my.attention.bean.MyAttFilmUser;
import com.bw.movie.my.attention.presenter.AttFilmPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class AttentionFilmFragment extends BaseFragment implements IBaseView<MyAttFilmUser> {

    @BindView(R.id.attFilmRb1)
    RadioButton attFilmRb1;
    @BindView(R.id.attFilmRb2)
    RadioButton attFilmRb2;
    @BindView(R.id.attFilmRg)
    RadioGroup attFilmRg;
    @BindView(R.id.attenrecycle2)
    RecyclerView attenrecycle2;
    @BindView(R.id.attenimage1)
    ImageView attenimage1;
    Unbinder unbinder;
    private AttFilmPresenter mAttFilmPresenter;
    private List<MyAttFilmUser.ResultBean> mList;

    @Override
    public void initView() {
        unbinder = ButterKnife.bind(this, rootView);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        attFilmRb1.setText("1");
        String trim = attFilmRb1.getText().toString().trim();
        Integer value = Integer.valueOf(trim);
        mAttFilmPresenter.getFilm(value);
    }

    @Override
    public int initLayoutId() {
        return R.layout.fragment_attention_film;
    }

    @Override
    public BasePresenter initPresenter() {
        mAttFilmPresenter = new AttFilmPresenter(this);
        return mAttFilmPresenter;
    }

    @Override
    public void initVarisble() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.attFilmRb1, R.id.attFilmRb2, R.id.attenimage1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.attFilmRb1:
                attFilmRb1.setText("1");
                String trim = attFilmRb1.getText().toString().trim();
                Integer value = Integer.valueOf(trim);
                mAttFilmPresenter.getFilm(value);
                break;
            case R.id.attFilmRb2:
                attFilmRb2.setText("2");
                String trim1 = attFilmRb2.getText().toString().trim();
                Integer value1 = Integer.valueOf(trim1);
                mAttFilmPresenter.getFilm(value1);
                break;
            case R.id.attenimage1:
                getActivity().finish();
                break;
        }
    }

    @Override
    public void onDataSuccess(MyAttFilmUser myAttFilmUser) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        attenrecycle2.setLayoutManager(linearLayoutManager);
        mList = myAttFilmUser.getResult();
        AttFilmAdapter attFilmAdapter = new AttFilmAdapter(getContext(),mList);
        attenrecycle2.setAdapter(attFilmAdapter);
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
