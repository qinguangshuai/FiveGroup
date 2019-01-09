package com.bw.movie.film.details.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.film.show.popular.adapter.PopularPlayAdapter;
import com.bw.movie.film.show.popular.bean.PopularBean;
import com.bw.movie.film.show.popular.presenter.PopularPresenter;
import com.bw.movie.film.show.popular.view.PopularmView;
import com.bw.movie.util.EmptyUtil;
import com.bw.movie.util.RecyclerViewScrollUtil;
import com.bw.movie.util.ToastUtil;

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
        mRecyclerViewDetailsfragment.setAdapter(mPopularPlayAdapter);
        mRecyclerViewDetailsfragment.setLayoutManager(linearLayoutManager);
        RecyclerViewScrollUtil.Refresh(mSwipeDetailsfragment, 2000, new RecyclerViewScrollUtil.onEvent() {
            @Override
            public void info() {
                ToastUtil.Toast("刷新~");
            }
        });
        RecyclerViewScrollUtil.Scroll(mRecyclerViewDetailsfragment, true, new RecyclerViewScrollUtil.onEvent() {
            @Override
            public void info() {
                ToastUtil.Toast("加载.....没有更多了");
            }
        });
    }

    //set数据
    public void setData() {
        new PopularPresenter(new PopularmView<PopularBean>() {
            @Override
            public void onDataSuccess(PopularBean popularBean) {
                mPopularPlayAdapter.setResult(popularBean.getResult());
                mPopularPlayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onDataFailer(String msg) {
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

