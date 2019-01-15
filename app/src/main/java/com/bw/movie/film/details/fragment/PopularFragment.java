package com.bw.movie.film.details.fragment;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bw.movie.Constant;
import com.bw.movie.R;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.film.event.JumpLgoinEvent;
import com.bw.movie.film.popwindow.ScrollWindow;
import com.bw.movie.film.show.popular.adapter.PopularPlayAdapter;
import com.bw.movie.film.show.popular.bean.PopularBean;
import com.bw.movie.film.show.popular.presenter.PopularPresenter;
import com.bw.movie.film.show.popular.view.PopularmView;
import com.bw.movie.util.RecyclerViewScrollUtil;
import com.bw.movie.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/*
 *作者:ash
 *TODO:
 *      正在热映
 */
public class PopularFragment extends BaseFragment {
    @BindView(R.id.swipe_detailsfragment)
    SwipeRefreshLayout mSwipeDetailsfragment;
    Unbinder unbinder1;
    //判空工具类
    //吐司工具类
    //适配器
    private PopularPlayAdapter mPopularPlayAdapter = new PopularPlayAdapter();
    private ScrollWindow mScrollWindow = new ScrollWindow(getActivity());


    @BindView(R.id.RecyclerView_detailsfragment)
    RecyclerView mRecyclerViewDetailsfragment;
    Unbinder unbinder;

    @Override
    public void initView() {
        unbinder = ButterKnife.bind(this, rootView);

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        setRecyclerViewData();
        setData();
    }

    @Override
    public int initLayoutId() {
        return R.layout.detailsfragment;
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

    //set recyclerview 数据
    public void setRecyclerViewData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerViewDetailsfragment.setAdapter(mPopularPlayAdapter);
        mRecyclerViewDetailsfragment.setLayoutManager(linearLayoutManager);

        mSwipeDetailsfragment.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPopularBeanObservable(1,10,true);
            }
        });


        RecyclerViewScrollUtil.Scroll(mRecyclerViewDetailsfragment, true, new RecyclerViewScrollUtil.onEvent() {
            @Override
            public void info() {
                mScrollWindow.showPop(mRecyclerViewDetailsfragment);
                getPopularBeanObservable(1,10,true);
            }
        });
    }



    //请求回调 热门电影数据  第三个布尔值的参数 决定 是否执行 add 方法
    public void getPopularBeanObservable(int page, int count, final boolean isLoad) {
        new PopularPresenter(new PopularmView<PopularBean>() {
            @Override
            public void onDataSuccess(PopularBean popularBean) {
                mSwipeDetailsfragment.setRefreshing(false);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mScrollWindow.dismissPop();
                    }
                },1000);
            }

            @Override
            public void onDataFailer(String msg) {
                mSwipeDetailsfragment.setRefreshing(false);
                showEmpty();
                ToastUtil.Toast(msg + "sorry");
            }

            @Override
            public void onShowLoading() {

            }

            @Override
            public void onHideLoading() {

            }
        }).getPopularBeanObservable(page, count);
    }



    //set数据
    public void setData() {
        showContent();
        EventBus.getDefault().post(new JumpLgoinEvent(Constant.GETFAILER));
        new PopularPresenter(new PopularmView<PopularBean>() {
            @Override
            public void onDataSuccess(PopularBean popularBean) {
                mPopularPlayAdapter.setResult(popularBean.getResult());
                mPopularPlayAdapter.notifyDataSetChanged();
                EventBus.getDefault().post(new JumpLgoinEvent(Constant.GETCONNECT));
            }

            @Override
            public void onDataFailer(String msg) {
                EventBus.getDefault().post(new JumpLgoinEvent(Constant.GETNET));

                showContent();
                showEmpty();
                ToastUtil.Toast(msg + "sorry");
            }

            @Override

            public void onShowLoading() {

            }

            @Override
            public void onHideLoading() {

            }
        }).getPopularBeanObservable(1, 10);
    }



}


/*-----------
 *🖐说明:
 *  适配器
 */

