package com.bw.movie.cinema.fragment;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.bw.movie.Constant;
import com.bw.movie.MapActivity;
import com.bw.movie.MyApp;
import com.bw.movie.R;
import com.bw.movie.base.BaseEvent;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.cinema.activity.ParticularsActivity;
import com.bw.movie.cinema.adapter.RecommendErrorAdapder;
import com.bw.movie.cinema.cannelfollow.presenter.CannelFollowPresenter;
import com.bw.movie.cinema.cannelfollow.view.CannelFollowView;
import com.bw.movie.cinema.event.GreatEvent;
import com.bw.movie.cinema.follow.bean.FollowBean;
import com.bw.movie.cinema.follow.presenter.FollowProsenter;
import com.bw.movie.cinema.follow.view.FollowView;
import com.bw.movie.cinema.recommend.RecommendEvent;
import com.bw.movie.cinema.recommend.adapder.RecommendAdapder;
import com.bw.movie.cinema.recommend.bean.RecommendBean;
import com.bw.movie.cinema.recommend.bean.ResultBean;
import com.bw.movie.cinema.recommend.presenter.RecommendPresenter;
import com.bw.movie.cinema.recommend.view.RecommentView;
import com.bw.movie.greenbean.DaoSession;
import com.bw.movie.greenbean.GreenDaoBean;
import com.bw.movie.greenbean.GreenDaoBeanDao;
import com.bw.movie.util.ToastUtil;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.greendao.query.Query;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 附近影院
 */

public class RecommendFragment extends BaseFragment implements RecommentView<RecommendBean> {

    @BindView(R.id.recy_recommend)
    RecyclerView recyRecommend;
    @BindView(R.id.swiperecomment)
    SwipeRefreshLayout swipeRefreshLayout;
    Unbinder unbinder;
    private RecommendPresenter mRecommendPresenter;
    private Query<GreenDaoBean> userQuery;
    private GreenDaoBeanDao greenDaoBeanDao;
    private int page = 1;


    @Override
    public void initView() {
        unbinder = ButterKnife.bind(this, rootView);



        mRecommendPresenter = new RecommendPresenter(this);

        if (!EventBus.getDefault().isRegistered(this)) {
           BaseEvent.register(this);
        }

        DaoSession daoSession = ((MyApp) getActivity().getApplication()).getDaoSession();
        greenDaoBeanDao = daoSession.getGreenDaoBeanDao();
        userQuery = greenDaoBeanDao.queryBuilder().orderAsc(GreenDaoBeanDao.Properties.MId).build();


        showloading();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {


                mRecommendPresenter.getRecommend(MapActivity.latitude + "", MapActivity.longitude1 + "", 1, 10);


            }
        });
    }

    @Subscribe
    public void getlongitude(final RecommendEvent recommendEvent) {

        mRecommendPresenter.getRecommend(recommendEvent.getLongitude(), recommendEvent.getLatitude(), 1, 10);

    }


    //点赞
    @Subscribe
    public void great(final GreatEvent greatEvent) {
        if (greatEvent.isB()) {
            new FollowProsenter(new FollowView<FollowBean>() {
                @Override
                public void onDataSuccess(FollowBean followBean) {

                    if (followBean.getMessage().equals("关注成功")) {
                        ToastUtil.Toast(followBean.getMessage());
                        greatEvent.getCheckBox().setButtonDrawable(R.mipmap.com_icon_collection_selected_hdpi);

                    }

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
            }).getFollow(greatEvent.getId());
        } else new CannelFollowPresenter(new CannelFollowView<FollowBean>() {
            @Override
            public void onDataSuccess(FollowBean followBean) {
                if (followBean.getMessage().equals("取消关注成功")) {
                    ToastUtil.Toast(followBean.getMessage());
                    greatEvent.getCheckBox().setButtonDrawable(R.mipmap.com_icon_collection_default_hdpi);

                }

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
        }).getCannelFollow(greatEvent.getId());
    }


    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        mRecommendPresenter.getRecommend(MapActivity.latitude + "", MapActivity.longitude1 + "", page++, 10);

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
        BaseEvent.unregister(this);
    }

    @Override
    public void onDataSuccess(RecommendBean recommendBean) {
        showContent();
        swipeRefreshLayout.setRefreshing(false);
        final List<ResultBean> nearbyCinemaList = recommendBean.getResult();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyRecommend.setLayoutManager(linearLayoutManager);
        RecommendAdapder recommendAdapder = new RecommendAdapder(nearbyCinemaList, getActivity());
        recyRecommend.setAdapter(recommendAdapder);
        recommendAdapder.setGetListener(new RecommendAdapder.getListener() {
            @Override

            public void getList(View view, int position) {
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

        for (int i = 0; i < nearbyCinemaList.size(); i++) {
            GreenDaoBean greenDaoBean = new GreenDaoBean(nearbyCinemaList.get(i).getName(), nearbyCinemaList.get(i).getAddress(), nearbyCinemaList.get(i).getLogo());
            greenDaoBeanDao.insert(greenDaoBean);
        }
    }

    @Override
    public void onDataFailer(String msg) {
        showContent();
        swipeRefreshLayout.setRefreshing(false);
        List<GreenDaoBean> users = queryList();
        if (users.size()>0 && users!=null){
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            recyRecommend.setLayoutManager(linearLayoutManager);
            RecommendErrorAdapder recommendErrorAdapder = new RecommendErrorAdapder(users, getActivity());
            recyRecommend.setAdapter(recommendErrorAdapder);
            recommendErrorAdapder.setiErrorList(new RecommendErrorAdapder.IErrorList() {
                @Override
                public void getIErrorList(View view, int position) {
                    ToastUtil.Toast("请检查网络");
                }
            });

        }else{
            showEmpty();
        }




    }

    private List<GreenDaoBean> queryList() {
        List<GreenDaoBean> users = userQuery.list();
        return users;
    }

    @Override
    public void onShowLoading() {

    }

    @Override
    public void onHideLoading() {

    }
}
