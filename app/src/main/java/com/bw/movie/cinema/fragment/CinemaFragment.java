package com.bw.movie.cinema.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bw.movie.MainActivity;
import com.bw.movie.R;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.cinema.bean.AddressUser;
import com.bw.movie.custom.CustomViewpager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 影院页面
 */
public class CinemaFragment extends BaseFragment {
    //推荐影院
    @BindView(R.id.recommendcinema)
    RadioButton recommendcinema;
    //附近影院
    @BindView(R.id.neighbouringcinema)
    RadioButton neighbouringcinema;
    @BindView(R.id.rg_cinema)
    RadioGroup rgCinema;
    @BindView(R.id.vp_cinema)
    CustomViewpager vpCinema;
    Unbinder unbinder;
    @BindView(R.id.zuoBiaoImage)
    ImageView zuoBiaoImage;
    @BindView(R.id.zuoBiaoText)
    TextView zuoBiaoText;
    Unbinder unbinder1;
    private int a = 0;

    //初始化控件
    @Override
    public void initView() {
        unbinder = ButterKnife.bind(this, rootView);
        rgCinema.check(R.id.recommendcinema);
        EventBus.getDefault().register(this);
    }

    //初始化监听
    @Override
    public void initListener() {
        final ArrayList<Fragment> list = new ArrayList<>();
        list.add(new NeighbouringFragment());
        list.add(new RecommendFragment());
        list.add(new RecommendFragment());
//        list.add(new NeighbouringFragment());
        vpCinema.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return list.get(i);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        rgCinema.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.recommendcinema:
                        vpCinema.setCurrentItem(0);
                        break;
                    case R.id.neighbouringcinema:
                        vpCinema.setCurrentItem(1);
                        break;
                }
            }
        });
    }

    //逻辑处理
    @Override
    public void initData() {

    }

    //初始化布局
    @Override
    public int initLayoutId() {
        return R.layout.fragment_cinema;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick(R.id.zuoBiaoImage)
    public void onViewClicked() {
        zuoBiaoImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),MainActivity.class));
            }
        });
    }

    @Subscribe
    public void setAddress(AddressUser address){
        a++;
        if (a==1){
            zuoBiaoText.setText(address.getCity()+"  "+address.getCid());
        }else {
            return;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
