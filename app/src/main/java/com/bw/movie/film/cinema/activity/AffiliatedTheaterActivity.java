package com.bw.movie.film.cinema.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bw.movie.Constant;
import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.cinema.activity.ParticularsActivity;
import com.bw.movie.cinema.adapter.NeightbourAdapder;
import com.bw.movie.cinema.bean.NeightbourBean;
import com.bw.movie.cinema.event.FollowEvent;
import com.bw.movie.cinema.prosenter.NeightbourPresenter;
import com.bw.movie.cinema.view.NeightbourView;
import com.bw.movie.util.EmptyUtil;
import com.bw.movie.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AffiliatedTheaterActivity extends BaseActivity implements NeightbourView<NeightbourBean> {


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
        // getData();
        NeightbourPresenter neightbourPresenter = new NeightbourPresenter(this);
        neightbourPresenter.getNeightbour(1, 10);

        EventBus.getDefault().register(this);

        setmRecyAffiliated();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void getFollowId(FollowEvent followEvent) {
        if (followEvent.getId() == Constant.FOLLOWID) {
            NeightbourPresenter neightbourPresenter = new NeightbourPresenter(this);
            neightbourPresenter.getNeightbour(1, 10);
        }
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

   /* //请求数据
    public void getData() {

      new NeightbourPresenter(new NeightbourView<NeightbourBean>() {


          @Override
          public void onDataSuccess(NeightbourBean neightbourBean) {


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
      }).getNeightbour(1,10);*/
//    }


    @Override
    public void onDataSuccess(NeightbourBean neightbourBean) {
        final List<NeightbourBean.ResultBean.NearbyCinemaListBean> nearbyCinemaList = neightbourBean.getResult().getNearbyCinemaList();
        NeightbourAdapder neightbourAdapder = new NeightbourAdapder(nearbyCinemaList, AffiliatedTheaterActivity.this);
        mRecyAffiliated.setAdapter(neightbourAdapder);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AffiliatedTheaterActivity.this);
        mRecyAffiliated.setLayoutManager(linearLayoutManager);

        neightbourAdapder.setGetListener(new NeightbourAdapder.getListener() {
            @Override
            public void getList(View view, int position) {
                //跳转到ParticularsActivity页面
                Intent intent = new Intent(AffiliatedTheaterActivity.this, ParticularsActivity.class);
                //获取推荐的logo的
                String logo = nearbyCinemaList.get(position).getLogo();
                //获取推荐姓名
                String name = nearbyCinemaList.get(position).getName();
                //获取推荐的地址
                String address = nearbyCinemaList.get(position).getAddress();
                int id = nearbyCinemaList.get(position).getId();
                intent.putExtra(Constant.TUIJIANID, id + "");
                intent.putExtra(Constant.LOGO, logo);
                intent.putExtra(Constant.NAME, name);
                intent.putExtra(Constant.ADDRESS, address);
                startActivity(intent);
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
}
