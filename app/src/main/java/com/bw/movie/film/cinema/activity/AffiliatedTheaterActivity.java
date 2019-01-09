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
import com.bw.movie.cinema.bean.neightbourbean.NeightBourResultBean;
import com.bw.movie.cinema.bean.neightbourbean.NeightNearbyCinemaListBean;
import com.bw.movie.cinema.bean.neightbourbean.NeightbourBean;
import com.bw.movie.cinema.cannelfollow.presenter.CannelFollowPresenter;
import com.bw.movie.cinema.cannelfollow.view.CannelFollowView;
import com.bw.movie.cinema.event.FollowEvent;
import com.bw.movie.cinema.event.NeighbourEvent;
import com.bw.movie.cinema.follow.bean.FollowBean;
import com.bw.movie.cinema.follow.presenter.FollowProsenter;
import com.bw.movie.cinema.follow.view.FollowView;
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
    private String name;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        id = getIntent().getIntExtra("id", -1);
        name = getIntent().getStringExtra("name");
        mTitleAffiliated.setText(name);
        NeightbourPresenter neightbourPresenter = new NeightbourPresenter(this);
        neightbourPresenter.getNeightbour(1, 10);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
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
  //点赞
    @Subscribe
    public void getAffliated(final NeighbourEvent neighbourEvent) {
        if (neighbourEvent.isChecked()){
            new FollowProsenter(new FollowView<FollowBean>() {
                @Override
                public void onDataSuccess(FollowBean followBean) {
                    neighbourEvent.getCheckBox().setButtonDrawable(R.mipmap.com_icon_collection_selected_hdpi);

                    EventBus.getDefault().post(new FollowEvent(Constant.FOLLOWID));
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
            }).getFollow(neighbourEvent.getId());
        }else{
            new CannelFollowPresenter(new CannelFollowView<FollowBean>() {
                @Override
                public void onDataSuccess(FollowBean followBean) {
                    neighbourEvent.getCheckBox().setButtonDrawable(R.mipmap.com_icon_collection_default_hdpi);

                    EventBus.getDefault().post(new FollowEvent(Constant.FOLLOWID));
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
            }).getCannelFollow(neighbourEvent.getId());
        }
    }

    @Override
    public void onDataSuccess(NeightbourBean neightbourBean) {
        final List<NeightBourResultBean> nearbyCinemaList = neightbourBean.getResult();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AffiliatedTheaterActivity.this);
        mRecyAffiliated.setLayoutManager(linearLayoutManager);
        NeightbourAdapder neightbourAdapder = new NeightbourAdapder(nearbyCinemaList, AffiliatedTheaterActivity.this);
        mRecyAffiliated.setAdapter(neightbourAdapder);


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
