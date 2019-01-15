package com.bw.movie.my.attention.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.error.AppManager;
import com.bw.movie.my.attention.fragment.AttentionFilmFragment;
import com.bw.movie.my.attcinema.fragment.AttentioncinemaFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 秦广帅
 * 2018/12/28
 */

public class MyattentionActivity extends BaseActivity {

    @BindView(R.id.attenrb1)
    RadioButton attenrb1;
    @BindView(R.id.attenrb2)
    RadioButton attenrb2;
    @BindView(R.id.attenrg)
    RadioGroup attenrg;
    @BindView(R.id.attenfragment)
    FrameLayout attenfragment;
    @BindView(R.id.attenpager)
    ViewPager attenpager;
    @BindView(R.id.attenimage3)
    ImageView attenimage3;
    private FragmentManager mManager;
    private FragmentTransaction mTransaction;

    @Override
    public void initView() {
        ButterKnife.bind(this);
    }

    @Override
    public void initListener() {
        //开启事务
        mManager = getSupportFragmentManager();
        mTransaction = mManager.beginTransaction();
        final AttentioncinemaFragment fragment1 = new AttentioncinemaFragment();
        final AttentionFilmFragment fragment2 = new AttentionFilmFragment();

        //添加事务
        mTransaction.add(R.id.attenfragment, fragment1);
        mTransaction.add(R.id.attenfragment, fragment2);

        mTransaction.show(fragment1);
        mTransaction.hide(fragment2);

        //提交事务
        mTransaction.commit();

        attenrg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction beginTransaction = mManager.beginTransaction();
                switch (checkedId) {
                    case R.id.attenrb1:
                        beginTransaction.show(fragment1);
                        beginTransaction.hide(fragment2);
                        break;
                    case R.id.attenrb2:
                        beginTransaction.show(fragment2);
                        beginTransaction.hide(fragment1);
                        break;
                }
                beginTransaction.commit();
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public int initLayoutId() {
        return R.layout.activity_mycollect;
    }

    @Override
    public void initVariable() {

    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @OnClick(R.id.attenimage3)
    public void onViewClicked() {
        AppManager.getAppManager().finishActivity(this);
    }
}
