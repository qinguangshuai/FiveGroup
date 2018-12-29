package com.bw.movie.cinema.fragment;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.bw.movie.R;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.custom.CustomViewpager;

import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
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

    //初始化控件
    @Override
    public void initView() {
        unbinder = ButterKnife.bind(this, rootView);
        rgCinema.check(R.id.recommendcinema);
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
}
