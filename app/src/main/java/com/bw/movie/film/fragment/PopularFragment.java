package com.bw.movie.film.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.film.adapter.PopularPlayAdapter;
import com.bw.movie.film.bean.PopularBean;
import com.bw.movie.film.p.FilmProsenter;
import com.bw.movie.film.v.PopularmView;
import com.bw.movie.util.EmptyUtil;
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
    //判空工具类
    private EmptyUtil emptyUtil = new EmptyUtil();
    //吐司工具类
    private ToastUtil toast = new ToastUtil();
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
    public void setRecyclerViewData(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerViewDetailsfragment.setAdapter(mPopularPlayAdapter);
        mRecyclerViewDetailsfragment.setLayoutManager(linearLayoutManager);
    }

    //set数据
    public void setData(){
        new FilmProsenter(new PopularmView<PopularBean>() {
            @Override
            public void onDataSuccess(PopularBean popularBean) {
                mPopularPlayAdapter.setResult(popularBean.getResult());
                mPopularPlayAdapter.notifyDataSetChanged();
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
        }).getPopularBeanObservable(1, 10);
    }



}


/*-----------
 *🖐说明:
 *  适配器
 */

