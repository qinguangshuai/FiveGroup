package com.bw.movie.start;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.RadioGroup;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.custom.CustomViewpager;
import com.bw.movie.start.fragment.LeadFourFragment;
import com.bw.movie.start.fragment.LeadOneFragment;
import com.bw.movie.start.fragment.LeadThreeFragment;
import com.bw.movie.start.fragment.LeadTwoFragment;

import java.util.ArrayList;

/**
 * date:2018/1/2    15:33
 * author:秦广帅(Lenovo)
 * fileName:AttCinemaAdapter
 * 启动页
 */

public class StartActivity extends BaseActivity {

    private static CustomViewpager sPager;
    private RadioGroup mRg;

    public static void Tiaozhuan2(){
        sPager.setCurrentItem(1);
    }
    public static void Tiaozhuan3(){
        sPager.setCurrentItem(2);
    }
    public static void Tiaozhuan4(){
        sPager.setCurrentItem(3);
    }

    public void initView() {
        sPager = findViewById(R.id.startpager);
        mRg = findViewById(R.id.startrg);
    }

    @Override
    public void initListener() {
        final ArrayList<Fragment> list = new ArrayList<>();
        list.add(new LeadOneFragment());
        list.add(new LeadTwoFragment());
        list.add(new LeadThreeFragment());
        list.add(new LeadFourFragment());

        sPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                mRg.check(mRg.getChildAt(i).getId());
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        //可有可无
        mRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.startrb1:
                        sPager.setCurrentItem(0);
                        break;
                    case R.id.startrb2:
                        sPager.setCurrentItem(1);
                        break;
                    case R.id.startrb3:
                        sPager.setCurrentItem(2);
                        break;
                    case R.id.startrb4:
                        sPager.setCurrentItem(3);
                        break;
                }
            }
        });

        sPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return list.get(i);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public int initLayoutId() {
        return R.layout.activity_start;
    }

    @Override
    public void initVariable() {

    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }
}
