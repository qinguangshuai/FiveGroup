package com.bw.movie.film.details.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.film.details.hot.adapter.HotAdapter;
import com.bw.movie.film.show.hot.bean.HotPlayBean;
import com.bw.movie.film.show.hot.presenter.HotPresenter;
import com.bw.movie.film.show.hot.view.HotPlayView;
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
 *      热门电影
 */
public class HotFragment extends BaseFragment {
    //吐司工具类
    private ToastUtil toast = new ToastUtil();
    //适配器
    private HotAdapter hotAdapter = new HotAdapter();

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
        mRecyclerViewDetailsfragment.setAdapter(hotAdapter);
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
        new HotPresenter(new HotPlayView<HotPlayBean>() {
            @Override
            public void onDataSuccess(HotPlayBean hotPlayBean) {
                List<HotPlayBean.ResultBean> result = hotPlayBean.getResult();
                hotAdapter.setHotResult(result);
                hotAdapter.notifyDataSetChanged();
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
        }).getHotPlayBeanObservable(1, 10);
    }


}



