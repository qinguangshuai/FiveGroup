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
import com.bw.movie.film.show.playing.adapter.PlayAdapter;
import com.bw.movie.film.show.playing.playing.PlayingBean;
import com.bw.movie.film.show.playing.presenter.PlayingPresenter;
import com.bw.movie.film.show.playing.view.PlayingView;
import com.bw.movie.util.RecyclerViewScrollUtil;
import com.bw.movie.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/*
 *作者:ash
 *TODO:
 *      正在上映
 */
public class PlayingFragment extends BaseFragment {
    //判空工具类
    //吐司工具类
    //适配器
    private PlayAdapter mPlayAdapter = new PlayAdapter();
    private ScrollWindow mScrollWindow = new ScrollWindow(getActivity());
    @BindView(R.id.swipe_detailsfragment)
    SwipeRefreshLayout mSwipeDetailsfragment;
    @BindView(R.id.RecyclerView_detailsfragment)
    RecyclerView mRecyclerViewDetailsfragment;
    Unbinder unbinder;

    @Override
    public void initView() {
        unbinder = ButterKnife.bind(this, rootView);
        showloading();
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
        mRecyclerViewDetailsfragment.setAdapter(mPlayAdapter);
        mRecyclerViewDetailsfragment.setLayoutManager(linearLayoutManager);


        mSwipeDetailsfragment.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPlayingBeanObservable(1, 10, true);
            }
        });

        RecyclerViewScrollUtil.Scroll(mRecyclerViewDetailsfragment, true, new RecyclerViewScrollUtil.onEvent() {
            @Override
            public void info() {
                mScrollWindow.showPop(mRecyclerViewDetailsfragment);
                getPlayingBeanObservable(1, 10, true);
            }
        });
    }

    //请求正在上映 || 即将上映  回调数据
    public void getPlayingBeanObservable(int page, int count, final boolean isLoad) {
        new PlayingPresenter(new PlayingView<PlayingBean>() {
            @Override
            public void onDataSuccess(PlayingBean playingBean) {

                mSwipeDetailsfragment.setRefreshing(false);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mScrollWindow.dismissPop();
                    }
                }, 1000);
            }

            @Override
            public void onDataFailer(String msg) {
                showContent();
                mSwipeDetailsfragment.setRefreshing(false);
                showEmpty();
            }

            @Override
            public void onShowLoading() {

            }

            @Override
            public void onHideLoading() {

            }
        }).getPlayingBeanObservable(page, count);
    }


    //set数据
    public void setData() {
        EventBus.getDefault().post(new JumpLgoinEvent(Constant.GETFAILER));
        new PlayingPresenter(new PlayingView<PlayingBean>() {
            @Override
            public void onDataSuccess(PlayingBean playingBean) {
                showContent();
                List<PlayingBean.ResultBean> result = playingBean.getResult();
                mPlayAdapter.setPlayResult(playingBean.getResult());
                mPlayAdapter.notifyDataSetChanged();
                EventBus.getDefault().post(new JumpLgoinEvent(Constant.GETCONNECT));
            }

            @Override
            public void onDataFailer(String msg) {
                EventBus.getDefault().post(new JumpLgoinEvent(Constant.GETNET));
                showContent();
                showEmpty();
            }

            @Override
            public void onShowLoading() {

            }

            @Override
            public void onHideLoading() {

            }
        }).getPlayingBeanObservable(1, 10);
    }

}


