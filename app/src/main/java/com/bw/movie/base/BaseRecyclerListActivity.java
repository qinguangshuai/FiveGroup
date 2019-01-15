package com.bw.movie.base;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.bw.movie.R;
import com.bw.movie.util.RecyclerViewScrollUtil;
import java.util.List;

public abstract class BaseRecyclerListActivity<T extends BaseRecyclerAdapter, E extends BasePresenter> extends BaseActivity<E> {

    private RecyclerView mRecyclerview;
    private SwipeRefreshLayout mSwiperefresh;

    private T adapder;
    private int page =1;
    @Override
    public void initView() {

        mRecyclerview = findViewById(R.id.recyclerview_base);
        mSwiperefresh = findViewById(R.id.swiperefresh_base);
        if (initLayoutManager() != null) {
            mRecyclerview.setLayoutManager(initLayoutManager());
        } else {
            mRecyclerview.setLayoutManager(setLinearLayout());
        }
        adapder = initAdapter();
        mRecyclerview.setAdapter(adapder);
    }



    public RecyclerView.LayoutManager initLayoutManager() {
        return null;
    }


    @Override
    public void initListener() {
        mSwiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapder.clearList();
                page = 1;
                getData(page);
            }
        });
        RecyclerViewScrollUtil.Scroll(mRecyclerview, true, new RecyclerViewScrollUtil.onEvent() {
            @Override
            public void info() {
                page++;
                getData(page);
            }
        });

    }


    private RecyclerView.LayoutManager setLinearLayout() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        return linearLayoutManager;
    }

    @Override
    public void initData() {
        getData(page);
    }
    protected abstract void getData(int page);

    protected abstract T initAdapter();
    @Override
    public int initLayoutId() {
        return R.layout.activity_base_recycler_list;
    }

    @Override
    public void initVariable() {
    }
    public void afterData(List list) {
        adapder.addList(list);
        mSwiperefresh.setRefreshing(false);
        if (adapder.getItemCount() == 0) {
            //展示空布局
        }
    }
    @Override
    public E initPresenter() {
        return null;
    }


}
