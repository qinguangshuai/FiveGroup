package com.bw.movie.start;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.bw.movie.R;
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

public class StartActivity extends AppCompatActivity {

    private static ViewPager pager;
    private RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        initView();
        final ArrayList<Fragment> list = new ArrayList<>();
        list.add(new LeadOneFragment());
        list.add(new LeadTwoFragment());
        list.add(new LeadThreeFragment());
        list.add(new LeadFourFragment());

        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                rg.check(rg.getChildAt(i).getId());
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        //可有可无
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.startrb1:
                        pager.setCurrentItem(0);
                        break;
                    case R.id.startrb2:
                        pager.setCurrentItem(1);
                        break;
                    case R.id.startrb3:
                        pager.setCurrentItem(2);
                        break;
                    case R.id.startrb4:
                        pager.setCurrentItem(3);
                        break;
                }
            }
        });

        pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
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

    public static void Tiaozhuan2(){
        pager.setCurrentItem(1);
    }
    public static void Tiaozhuan3(){
        pager.setCurrentItem(2);
    }
    public static void Tiaozhuan4(){
        pager.setCurrentItem(3);
    }

    private void initView() {
        pager = findViewById(R.id.startpager);
        rg = findViewById(R.id.startrg);
    }
}
