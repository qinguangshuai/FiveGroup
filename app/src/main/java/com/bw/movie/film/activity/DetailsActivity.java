package com.bw.movie.film.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.custom.CustomViewpager;
import com.bw.movie.film.event.JumpForThreeActivityBean;
import com.bw.movie.film.fragment.HotFragment;
import com.bw.movie.film.fragment.PlayingFragment;
import com.bw.movie.film.fragment.PopularFragment;
import com.bw.movie.util.EmptyUtil;
import com.bw.movie.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/*-----------
 *🖐todo:说明: 影片详情
 *  点击 进入详情
 */
public class DetailsActivity extends BaseActivity {

    @BindView(R.id.rbtn_hot)
    RadioButton rbtnHot;
    @BindView(R.id.rbtn_paly)
    RadioButton rbtnPaly;
    @BindView(R.id.rbtn_sugu)
    RadioButton rbtnSugu;
    @BindView(R.id.rg_datails)
    RadioGroup mRgDatails;
    @BindView(R.id.vp_datails)
    CustomViewpager mVpDatails;

    private ToastUtil toast = new ToastUtil();
    //viewpager 适配器
    private MyAdapter myAdapter = new MyAdapter(getSupportFragmentManager());
    private HotFragment hotFragment;
    private PlayingFragment playingFragment;
    private PopularFragment popularFragment;
    private Intent intent;
    private int index;

    @Override
    public void initView() {
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
        intent = getIntent();
        index = intent.getIntExtra("index", -1);
        //创建3个 fragment 对象 实例化
        hotFragment = new HotFragment();
        playingFragment = new PlayingFragment();
        popularFragment = new PopularFragment();
        setViewPagerData();
        //获取index的值


    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public int initLayoutId() {
        return R.layout.activity_details;
    }

    @Override
    public void initVariable() {

    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }


    //跳转事件
    @Subscribe
    public void jump(JumpForThreeActivityBean jumpForThreeActivityBean){
        Intent intent = new Intent(this,SynopsisActivity.class);
        intent.putExtra("详情id",jumpForThreeActivityBean.getId());
        startActivity(intent);
    }



    //viewpager 操作
    public void setViewPagerData() {
        ArrayList<Fragment> list = new ArrayList<Fragment>();
        //三次 add 方法
        list.add(hotFragment);
        list.add(popularFragment);
        list.add(playingFragment);
        //加入适配器
        myAdapter.setList(list);
        //加入数据
        mVpDatails.setAdapter(myAdapter);
        //拖住数据
        mVpDatails.setOffscreenPageLimit(2);
        //默认选中第一个
        mRgDatails.check(R.id.rbtn_hot);
        //监听 点击监听
        mRgDatails.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbtn_hot:
                        mVpDatails.setCurrentItem(0);
                        break;
                    case R.id.rbtn_paly:
                        mVpDatails.setCurrentItem(1);
                        break;
                    case R.id.rbtn_sugu:
                        mVpDatails.setCurrentItem(2);
                        break;
                }
            }
        });


        //监听
        mVpDatails.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                switch (i) {
                    case 0:
                        mRgDatails.check(R.id.rbtn_hot);
                        break;
                    case 1:
                        mRgDatails.check(R.id.rbtn_paly);
                        break;
                    case 2:
                        mRgDatails.check(R.id.rbtn_sugu);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });


        //选中页面
        mVpDatails.setCurrentItem(index);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}


/*-----------
 *🖐说明:
 *   viewpager 适配器
 */
class MyAdapter extends FragmentPagerAdapter {

    private EmptyUtil emptyUtil = new EmptyUtil();


    private ArrayList<Fragment> list = new ArrayList<>();

    public void setList(ArrayList<Fragment> list) {
        if (this.list != null && this.list.size() > 0) {
            this.list.clear();
        }
        this.list.addAll(list);
    }

    public MyAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return list.get(i);
    }

    @Override
    public int getCount() {
        if (emptyUtil.isNull(list) == false) {
            return list.size();
        }
        return 0;
    }
}