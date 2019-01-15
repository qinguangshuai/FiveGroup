package com.bw.movie.cinema.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bw.movie.Constant;
import com.bw.movie.R;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.cinema.activity.ParticularsActivity;
import com.bw.movie.cinema.adapter.NeightbourAdapder;
import com.bw.movie.cinema.bean.neightbourbean.NeightBourResultBean;
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

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * 推荐影院
 */
public class NeighbouringFragment extends BaseFragment implements NeightbourView<NeightbourBean> {
    @BindView(R.id.recy_neightbor)
    RecyclerView recyNeightbor;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipeRefreshLayout;
    Unbinder unbinder;
    @BindView(R.id.ying)
    ImageView ying;
    Unbinder unbinder1;
    private NeightbourPresenter mNeightbourPresenter;

    @Override
    public void initView() {
        unbinder = ButterKnife.bind(this, rootView);
        mNeightbourPresenter = new NeightbourPresenter(this);
        mNeightbourPresenter.getNeightbour(1, 10);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        showloading();
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

                showloading();
                mNeightbourPresenter.getNeightbour(1, 10);


            }
        });
    }

    @Override
    public void initData() {

    }

    @Subscribe
    public void getNeighbour(final NeighbourEvent neighbourEvent) {
        if (neighbourEvent.isChecked()) {
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
        } else {

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
    public int initLayoutId() {
        return R.layout.fragment_neighbouring;
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public void initVarisble() {

    }

    @Override
    public void onDataSuccess(NeightbourBean neightbourBean) {
        showContent();
        swipeRefreshLayout.setRefreshing(false);
        final List<NeightBourResultBean> nearbyCinemaList = neightbourBean.getResult();
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyNeightbor.setLayoutManager(linearLayoutManager);
        NeightbourAdapder neightbourAdapder = new NeightbourAdapder(nearbyCinemaList, getContext());
        recyNeightbor.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                ying.setVisibility(View.VISIBLE);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    int childCount = linearLayoutManager.getChildCount();
                    int itemCount = linearLayoutManager.getItemCount();
                    int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
                    if ((childCount + firstVisibleItemPosition) >= itemCount) {
                        ying.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                recyNeightbor.scrollToPosition(0);
                                ying.setVisibility(View.GONE);
                            }
                        });
                    }
                }
            }
        });
        recyNeightbor.setAdapter(neightbourAdapder);

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
        showEmpty();
    }

    @Override
    public void onShowLoading() {

    }

    @Override
    public void onHideLoading() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }
}
