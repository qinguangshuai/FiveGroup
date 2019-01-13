package com.bw.movie.film.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.Constant;
import com.bw.movie.R;
import com.bw.movie.base.BaseEvent;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.cinema.bean.AddressUser;
import com.bw.movie.cinema.fragment.ChuanUser;
import com.bw.movie.error.AppManager;
import com.bw.movie.film.adapter.RootAdapter;
import com.bw.movie.film.details.activity.DetailsActivity;
import com.bw.movie.film.details.bean.CancelFollowMovieBean;
import com.bw.movie.film.details.bean.FollowBean;
import com.bw.movie.film.details.presenter.CancelFollowPresenter;
import com.bw.movie.film.details.presenter.FollowPresenter;
import com.bw.movie.film.details.view.CancelFollowMovieView;
import com.bw.movie.film.details.view.FollowView;
import com.bw.movie.film.event.JumpEvent;
import com.bw.movie.film.event.NetEvent;
import com.bw.movie.film.event.PopularEvent;
import com.bw.movie.film.event.RefreshEvent;
import com.bw.movie.film.show.carousel.bean.CarouselBean;
import com.bw.movie.film.show.carousel.presenter.CarouserlPresenter;
import com.bw.movie.film.show.carousel.view.CarousemView;
import com.bw.movie.film.show.hot.bean.HotPlayBean;
import com.bw.movie.film.show.hot.presenter.HotPresenter;
import com.bw.movie.film.show.hot.view.HotPlayView;
import com.bw.movie.film.show.playing.playing.PlayingBean;
import com.bw.movie.film.show.playing.presenter.PlayingPresenter;
import com.bw.movie.film.show.playing.view.PlayingView;
import com.bw.movie.film.show.popular.bean.PopularBean;
import com.bw.movie.film.show.popular.presenter.PopularPresenter;
import com.bw.movie.film.show.popular.view.PopularmView;
import com.bw.movie.login.LoginActivity;
import com.bw.movie.util.ToastUtil;
import com.lljjcoder.citypickerview.widget.CityPicker;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/*
 * todo:影片页面  首页展示
 * 轮播图                  √
 * 自定义搜索框            √
 * 自定义分割线            √
 * 热门电影 具备加载功能   √
 * 正在热映 具备加载功能   √
 * 正在上映 具备加载功能   √
 */
public class FilmFragment extends BaseFragment {
    @BindView(R.id.file_carouse)
    ImageView fileCarouse;
    @BindView(R.id.file_text)
    TextView fileText;
    //根布局的adapter 适配器
    private RootAdapter mRootAdapter = new RootAdapter();
    //吐司工具类
    //判空工具类

    Unbinder unbinder;
    @BindView(R.id.RecyclerView_filefragment)
    RecyclerView mRecyclerViewFilefragment;
    private Intent intent;
    private CityPicker mCityPicker;

    //初始化控件
    @Override
    public void initView() {
        unbinder = ButterKnife.bind(this, rootView);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        setRootRecyclerViewData();
        getCarouselBeanObservable(1, 10);
        getPopularBeanObservable(1, 10, false);
        getHotPlayBeanObservable(1, 10, false);
        getPlayingBeanObservable(1, 10, false);
        //intent 传值 准备
        intent = new Intent(getActivity(), DetailsActivity.class);

        //滚轮文字的大小
        //滚轮文字的颜色
        //省份滚轮是否循环显示
        //城市滚轮是否循环显示
        //地区（县）滚轮是否循环显示
        //滚轮显示的item个数
        //滚轮item间距
        mCityPicker = new CityPicker.Builder(getActivity())
                .textSize(20)//滚轮文字的大小
                .title("城市选择")
                .backgroundPop(0xa0000000)
                .titleBackgroundColor("#0CB6CA")
                .titleTextColor("#000000")
                .backgroundPop(0xa0000000)
                .confirTextColor("#000000")
                .cancelTextColor("#000000")
                .province("xx省")
                .city("xx市")
                .district("xx区")
                .textColor(Color.parseColor("#000000"))//滚轮文字的颜色
                .provinceCyclic(true)//省份滚轮是否循环显示
                .cityCyclic(false)//城市滚轮是否循环显示
                .districtCyclic(false)//地区（县）滚轮是否循环显示
                .visibleItemsCount(7)//滚轮显示的item个数
                .itemPadding(10)//滚轮item间距
                .onlyShowProvinceAndCity(false)
                .build();
    }

    @Subscribe
    public void setAddress(AddressUser address) {
        fileText.setText(address.getCity() + "  " + address.getCid());
    }

    //点击事件
    @Override
    public void initListener() {
        fileCarouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCityPicker.show();
                mCityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
                    @Override
                    public void onSelected(String... strings) {
                        String province = strings[0];
                        //城市
                        String city = strings[1];
                        //区县
                        String cid = strings[2];
                        //邮编
                        String code = strings[3];
                        fileText.setText(city + " " + cid);
                        BaseEvent.post(new AddressUser(city, cid));
                    }

                    @Override
                    public void onCancel() {

                    }
                });
            }
        });
    }

    //加载数据
    @Override
    public void initData() {
    }

    @Override
    public int initLayoutId() {
        return R.layout.fragment_film;
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    //初始化变量
    @Override
    public void initVarisble() {

    }

    //请求回调 轮播图 数据
    public void getCarouselBeanObservable(int page, int count) {
        EventBus.getDefault().post(new NetEvent(0x0001));
        new CarouserlPresenter(new CarousemView<CarouselBean>() {
            @Override
            public void onDataSuccess(CarouselBean carouselBean) {
                mRootAdapter.setCarouselBean(carouselBean);
                EventBus.getDefault().post(new NetEvent(Constant.GETNET));
            }

            @Override
            public void onDataFailer(String msg) {
                ToastUtil.Toast(msg + "sorry");
                EventBus.getDefault().post(new NetEvent(0x0002));
            }

            @Override
            public void onShowLoading() {
            }

            @Override
            public void onHideLoading() {
            }
        }).getCarouselBeanObservable(page, count);
    }


    //加载更多
    @Subscribe
    public void popularload(PopularEvent popularEvent) {
        /*
         * 如果
         * a==1 代表是 热门电影
         * a==2 代表是 正上热映
         * a==3 代表是 正在上映
         * */
        int a = popularEvent.getA();
        if (a == 1) {
            int i = 1;
            i++;
            getPopularBeanObservable(i, 10, true);
        } else if (a == 2) {
            int i = 1;
            i++;
            getHotPlayBeanObservable(i, 10, true);
        } else if (a == 3) {
            int i = 1;
            i++;
            getPlayingBeanObservable(i, 10, true);
        }
    }


    //跳转
    @Subscribe
    public void Jump(JumpEvent jumpEvent) {
        int a = jumpEvent.getA();
        intent.putExtra("index", a);
        getActivity().startActivity(intent);
    }

    //刷新请求
    @Subscribe
    public void refresh(RefreshEvent refreshEvent) {
        if (refreshEvent.isB()) {
            follow(refreshEvent.getI());
        } else {
            canceFollow(refreshEvent.getI());
        }
    }

    //请求关注
    public void follow(int a) {
        new FollowPresenter(new FollowView<FollowBean>() {
            @Override
            public void onDataSuccess(FollowBean followBean) {
                ToastUtil.Toast("关注成功");
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
        }).getFollowBeanObservable(a);
    }

    @Subscribe
    public void getChuan(ChuanUser chuanUser) {
        Intent intent = new Intent(getActivity(),LoginActivity.class);
        getActivity().startActivity(intent);
        getActivity().finish();
    }

    //请求取消关注
    public void canceFollow(int a) {
        new CancelFollowPresenter(new CancelFollowMovieView<CancelFollowMovieBean>() {
            @Override
            public void onDataSuccess(CancelFollowMovieBean cancelFollowMovieBean) {
                ToastUtil.Toast("取消关注");
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
        }).getCancelFollowMovieBeanObservable(a);
    }

    //请求回调 热门电影数据  第三个布尔值的参数 决定 是否执行 add 方法
    public void getPopularBeanObservable(int page, int count, final boolean isLoad) {
        new PopularPresenter(new PopularmView<PopularBean>() {
            @Override
            public void onDataSuccess(PopularBean popularBean) {
                if (isLoad) {
                    mRootAdapter.addResult(popularBean.getResult());
                } else {
                    mRootAdapter.setResult(popularBean.getResult());
                }

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
        }).getPopularBeanObservable(page, count);
    }


    //请求正在热映 回调数据
    public void getHotPlayBeanObservable(int page, int count, final boolean isLoad) {
        new HotPresenter(new HotPlayView<HotPlayBean>() {
            @Override
            public void onDataSuccess(HotPlayBean hotPlayBean) {
                List<HotPlayBean.ResultBean> result = hotPlayBean.getResult();
                if (isLoad) {
                    mRootAdapter.addHotResult(result);
                } else {
                    mRootAdapter.setHotResult(result);
                }
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
        }).getHotPlayBeanObservable(page, count);
    }


    //请求正在上映 || 即将上映  回调数据
    public void getPlayingBeanObservable(int page, int count, final boolean isLoad) {
        new PlayingPresenter(new PlayingView<PlayingBean>() {
            @Override
            public void onDataSuccess(PlayingBean playingBean) {
                List<PlayingBean.ResultBean> result = playingBean.getResult();

                if (isLoad) {
                    mRootAdapter.addPlayResult(result);
                } else {
                    mRootAdapter.setPlayResult(result);
                }

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
        }).getPlayingBeanObservable(page, count);
    }


    //根布局
    public void setRootRecyclerViewData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerViewFilefragment.setLayoutManager(linearLayoutManager);
        mRecyclerViewFilefragment.setAdapter(mRootAdapter);
    }


    //销毁
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }


}//----------|结束主函数结束|----------