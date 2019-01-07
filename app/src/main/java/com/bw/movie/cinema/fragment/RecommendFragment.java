package com.bw.movie.cinema.fragment;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bw.movie.Constant;
import com.bw.movie.R;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.cinema.activity.ParticularsActivity;
import com.bw.movie.cinema.adapter.NeightbourAdapder;
import com.bw.movie.cinema.bean.neightbourbean.NeightNearbyCinemaListBean;
import com.bw.movie.cinema.bean.neightbourbean.NeightbourBean;
import com.bw.movie.cinema.event.FollowEvent;
import com.bw.movie.cinema.prosenter.NeightbourPresenter;
import com.bw.movie.cinema.view.NeightbourView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 附近影院
 */

public class RecommendFragment extends BaseFragment implements NeightbourView<NeightbourBean> {
    @BindView(R.id.recy_recommend)
    RecyclerView recyRecommend;
    @BindView(R.id.swiperecomment)
    SwipeRefreshLayout swipeRefreshLayout;
    Unbinder unbinder;

    @Override
    public void initView() {
        unbinder = ButterKnife.bind(this, rootView);
        NeightbourPresenter neightbourPresenter = new NeightbourPresenter(this);
        neightbourPresenter.getNeightbour(1, 10);
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
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
       swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
           @Override
           public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },2000);
           }
       });
    }

    @Override
    public void initData() {

    }

    @Override
    public int initLayoutId() {
        return R.layout.fragment_recommend;

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
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onDataSuccess(NeightbourBean neightbourBean) {
        final List<NeightNearbyCinemaListBean> nearbyCinemaList = neightbourBean.getResult().getNearbyCinemaList();
        NeightbourAdapder neightbourAdapder = new NeightbourAdapder(nearbyCinemaList, getContext());
        recyRecommend.setAdapter(neightbourAdapder);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyRecommend.setLayoutManager(linearLayoutManager);
        neightbourAdapder.setGetListener(new NeightbourAdapder.getListener() {
            @Override
            public void getList(View view, int position) {
                //跳转到ParticularsActivity页面
                Intent intent = new Intent(getActivity(), ParticularsActivity.class);
                //获取推荐的logo的
                String logo = nearbyCinemaList.get(position).getLogo();
                //获取推荐姓名
                String name = nearbyCinemaList.get(position).getName();
                //获取推荐的地址
                String address = nearbyCinemaList.get(position).getAddress();
                int id = nearbyCinemaList.get(position).getId();
                intent.putExtra(Constant.TUIJIANID,id+"");
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
