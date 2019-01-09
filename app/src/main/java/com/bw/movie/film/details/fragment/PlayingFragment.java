package com.bw.movie.film.details.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.film.show.playing.adapter.PlayAdapter;
import com.bw.movie.film.show.playing.playing.PlayingBean;
import com.bw.movie.film.show.playing.presenter.PlayingPresenter;
import com.bw.movie.film.show.playing.view.PlayingView;
import com.bw.movie.util.EmptyUtil;
import com.bw.movie.util.RecyclerViewScrollUtil;
import com.bw.movie.util.ToastUtil;

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
    //吐司工具类
    private ToastUtil toast = new ToastUtil();
    //适配器
    private PlayAdapter mPlayAdapter = new PlayAdapter();

    @BindView(R.id.swipe_detailsfragment)
    SwipeRefreshLayout mSwipeDetailsfragment;
    @BindView(R.id.RecyclerView_detailsfragment)
    RecyclerView mRecyclerViewDetailsfragment;
    Unbinder unbinder;

    @Override
    public void initView() {
        unbinder = ButterKnife.bind(this, rootView);
        setRecyclerViewData();
        setData();
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

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
        RecyclerViewScrollUtil.Refresh(mSwipeDetailsfragment, 2000, new RecyclerViewScrollUtil.onEvent() {
            @Override
            public void info() {
                toast.Toast("刷新~");
            }
        });
        RecyclerViewScrollUtil.Scroll(mRecyclerViewDetailsfragment, true, new RecyclerViewScrollUtil.onEvent() {
            @Override
            public void info() {
                toast.Toast("加载.....没有更多了");
            }
        });
    }

    //set数据
    public void setData() {
        new PlayingPresenter(new PlayingView<PlayingBean>() {
            @Override
            public void onDataSuccess(PlayingBean playingBean) {
                List<PlayingBean.ResultBean> result = playingBean.getResult();
                mPlayAdapter.setPlayResult(playingBean.getResult());
                mPlayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onDataFailer(String msg) {
                toast.Toast(msg + "sorry");
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


