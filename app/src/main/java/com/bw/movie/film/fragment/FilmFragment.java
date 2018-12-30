package com.bw.movie.film.fragment;


import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.custom.ScrollView;
import com.bw.movie.custom.SearchView;
import com.bw.movie.film.activity.DetailsActivity;
import com.bw.movie.film.bean.CarouselBean;
import com.bw.movie.film.bean.HotPlayBean;
import com.bw.movie.film.bean.PlayingBean;
import com.bw.movie.film.bean.PopularBean;
import com.bw.movie.film.event.JumpEvent;
import com.bw.movie.film.event.PopularEvent;
import com.bw.movie.film.p.FilmProsenter;
import com.bw.movie.film.v.CarousemView;

import org.greenrobot.eventbus.EventBus;

import com.bw.movie.film.v.HotPlayView;
import com.bw.movie.film.v.PlayingView;
import com.bw.movie.film.v.PopularmView;
import com.bw.movie.util.EmptyUtil;
import com.bw.movie.util.ToastUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import recycler.coverflow.CoverFlowLayoutManger;
import recycler.coverflow.RecyclerCoverFlow;

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
    //根布局的adapter 适配器
    private RootAdapter mRootAdapter = new RootAdapter();
    //吐司工具类
    private ToastUtil toast = new ToastUtil();

    Unbinder unbinder;
    @BindView(R.id.RecyclerView_filefragment)
    RecyclerView mRecyclerViewFilefragment;
    private Intent intent;

    //初始化控件
    @Override
    public void initView() {
        unbinder = ButterKnife.bind(this, rootView);
        EventBus.getDefault().register(this);
        setRootRecyclerViewData();
        getCarouselBeanObservable(1, 10);
        getPopularBeanObservable(1, 10, false);
        getHotPlayBeanObservable(1, 10, false);
        getPlayingBeanObservable(1, 10, false);
        //intent 传值 准备
        intent = new Intent(getActivity(), DetailsActivity.class);
    }

    //点击事件
    @Override
    public void initListener() {
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
        new FilmProsenter(new CarousemView<CarouselBean>() {
            @Override
            public void onDataSuccess(CarouselBean carouselBean) {
                mRootAdapter.setCarouselBean(carouselBean);

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


    //请求回调 热门电影数据  第三个布尔值的参数 决定 是否执行 add 方法
    public void getPopularBeanObservable(int page, int count, final boolean isLoad) {
        new FilmProsenter(new PopularmView<PopularBean>() {
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
                toast.Toast(msg + "sorry");
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
        new FilmProsenter(new HotPlayView<HotPlayBean>() {
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
                toast.Toast(msg + "sorry");
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
        new FilmProsenter(new PlayingView<PlayingBean>() {
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
                toast.Toast(msg + "sorry");
            }

            @Override
            public void onShowLoading() {

            }

            @Override
            public void onHideLoading() {

            }
        }).getPlayingBeanObservable(page, count);
    }


    //todo:根布局的recyclerview 相关操作
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


//┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━分割线━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
//┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━分割线━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛


/*-----------
 *🖐todo:根布局的recyclerview的适配器
 * 轮播布局+自定义搜索框+定位(未完成) √
 * list1                             √
 * list2                             √
 * list3                             √
 *
 */
class RootAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //非空判断工具类
    private EmptyUtil emptyUtil = new EmptyUtil();

    //轮播数据
    private CarouselBean mCarouselBean = new CarouselBean();

    //适配器中 录播的set方法
    public void setCarouselBean(CarouselBean mCarouselBean) {
        this.mCarouselBean = mCarouselBean;
        //同时刷新第一条
        notifyItemChanged(0);
    }

    //吐司工具类
    private ToastUtil toast = new ToastUtil();

    //热门电影数据 list
    private List<PopularBean.ResultBean> result = new ArrayList<>();

    //热门电影 数据 set 方法
    public void setResult(List<PopularBean.ResultBean> result) {
        if (emptyUtil.isNull(this.result) == false) {
            this.result.clear();
        }
        this.result.addAll(result);
        notifyItemChanged(1);
    }

    //热门电影 add 方法
    public void addResult(List<PopularBean.ResultBean> result) {
        if (emptyUtil.isNull(result)) {
            toast.Toast("没有更多了");
        } else {
            this.result.addAll(result);
        }
    }

    //正在热映 数据 List
    private List<HotPlayBean.ResultBean> hotresult = new ArrayList<>();


    //正在热映 数据 set 方法
    public void setHotResult(List<HotPlayBean.ResultBean> hotresult) {
        if (emptyUtil.isNull(this.hotresult) == false) {
            this.hotresult.clear();
        }
        this.hotresult.addAll(hotresult);
        notifyItemChanged(2);
    }

    //正在热映 add 方法
    public void addHotResult(List<HotPlayBean.ResultBean> hotresult) {
        if (emptyUtil.isNull(hotresult)) {
            toast.Toast("没有更多了");
        } else {
            this.hotresult.addAll(hotresult);

        }
    }

    // 即将上映 || 正在上映 数据 list
    private List<PlayingBean.ResultBean> playresult = new ArrayList<>();

    //即将上映 || 正在上映  数据 set 方法
    public void setPlayResult(List<PlayingBean.ResultBean> playresult) {
        if (emptyUtil.isNull(this.playresult) == false) {
            this.playresult.clear();
        }
        this.playresult.addAll(playresult);
        notifyItemChanged(3);
    }

    //即将上映 || 正在上映 数据 add 方法
    public void addPlayResult(List<PlayingBean.ResultBean> playresult) {
        if (emptyUtil.isNull(playresult)) {
            toast.Toast("没有更多了");
        } else {
            this.playresult.addAll(playresult);
        }

    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        switch (getItemViewType(i)) {
            case 0:
                //轮播布局
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.film_fragment_item_carouse, viewGroup, false);
                return new CarouseHolder(view);
            case 1:
                //热门布局
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.film_fragment_item_popular, viewGroup, false);
                return new PopularHodler(view);
            case 2:
                //热映
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.film_fragment_item_hotplay, viewGroup, false);
                return new HotPlayHodler(view);
            case 3:
                //上映Ing
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.film_fragment_item_palying, viewGroup, false);
                return new PlayingHodler(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        //轮播
        if (viewHolder instanceof CarouseHolder) {
            CarouseHolder carouseHolder = (CarouseHolder) viewHolder;
            carouseHolder.setData(mCarouselBean);

        }
        //list1 热门
        else if (viewHolder instanceof PopularHodler) {
            PopularHodler popularHodler = (PopularHodler) viewHolder;
            popularHodler.setData(result);
            //设置跳转事件
            popularHodler.mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EventBus.getDefault().post(new JumpEvent(0));
                }
            });
        }
        //list2 热映
        else if (viewHolder instanceof HotPlayHodler) {
            HotPlayHodler hotPlayHodler = (HotPlayHodler) viewHolder;
            hotPlayHodler.setData(hotresult);
            //设置跳转事件
            hotPlayHodler.mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EventBus.getDefault().post(new JumpEvent(1));
                }
            });
        }
        //list3 上映ing
        else if (viewHolder instanceof PlayingHodler) {
            PlayingHodler playingHodler = (PlayingHodler) viewHolder;
            playingHodler.setData(playresult);
            //设置跳转事件
            playingHodler.mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EventBus.getDefault().post(new JumpEvent(2));
                }
            });
        }
    }

    //将这个条目原本的 item position 传回去
    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        //辅助变量 判断布局是否被加载进来
        int a = 0;
        //判断非空行为
        if (emptyUtil.isNull(mCarouselBean.getResult()) == false) {
            a++;
        }
        if (emptyUtil.isNull(result) == false) {
            a++;
        }
        if (emptyUtil.isNull(hotresult) == false) {
            a++;
        }
        if (emptyUtil.isNull(playresult) == false) {
            a++;
        }
        return a;
    }

    //todo: 轮播图 holder 类
    private class CarouseHolder extends RecyclerView.ViewHolder {
        private final SearchView mSearchView;
        private final RecyclerCoverFlow mRecyclerCoverFlow;
        private final ScrollView mScrollView;
        private final CheckBox mPositinging;
        private final CarouselAdapter mCarouselAdapter;

        public CarouseHolder(View view) {
            super(view);
            mSearchView = view.findViewById(R.id.search_item_carouse);
            mRecyclerCoverFlow = view.findViewById(R.id.RecyclerCoverFlow_item_carouse);
            mScrollView = view.findViewById(R.id.customScrollView_item_carouse);
            mPositinging = view.findViewById(R.id.Positioning_item_carouse);
            mCarouselAdapter = new CarouselAdapter();
        }


        public void setData(CarouselBean resultBean) {
            //将外部的数据通过 根adapter 加入到 轮播的子 适配器
            mCarouselAdapter.setmCarouselBean(resultBean);
            mRecyclerCoverFlow.setAdapter(mCarouselAdapter);
            //默认选中第 120 个条目
            mRecyclerCoverFlow.smoothScrollToPosition(120);
            //为自定义效果添加监听事件
            mRecyclerCoverFlow.setOnItemSelectedListener(new CoverFlowLayoutManger.OnSelected() {
                @Override
                public void onItemSelected(int position) {
                    mScrollView.onChecked();
                }
            });


        }
    }

    //todo:list1 列表 热门电影列表 Holder 类
    private class PopularHodler extends RecyclerView.ViewHolder {
        private final RecyclerView mRecyclerView;
        private final TextView mTextView;
        private final PopularAdapter popularAdapter;

        public PopularHodler(View view) {
            super(view);
            mRecyclerView = view.findViewById(R.id.RecyclerView_item_popular);
            mTextView = view.findViewById(R.id.back_item_popular);
            popularAdapter = new PopularAdapter();
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            mRecyclerView.setLayoutManager(linearLayoutManager);
            mRecyclerView.setAdapter(popularAdapter);
        }

        public void setData(final List<PopularBean.ResultBean> result) {
            popularAdapter.setResult(result);
            popularAdapter.notifyDataSetChanged();
            mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                //用来标记是否正在向最后一个滑动
                boolean isSlidingToLast = false;

                @Override
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                    //设置什么布局管理器,就获取什么的布局管理器
                    LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    // 当停止滑动时
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        //获取最后一个完全显示的ItemPosition ,角标值
                        int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
                        //所有条目,数量值
                        int totalItemCount = manager.getItemCount();
                        // 判断是否滚动到底部，并且是向右滚动
                        if (lastVisibleItem == (totalItemCount - 1) && isSlidingToLast) {
                            //加载更多功能的代码
                            EventBus.getDefault().post(new PopularEvent(1));
                            popularAdapter.addResult(result);
                            popularAdapter.notifyDataSetChanged();
                        }
                    }
                }

                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    //dx用来判断横向滑动方向，dy用来判断纵向滑动方向
                    //dx>0:向右滑动,dx<0:向左滑动
                    //dy>0:向下滑动,dy<0:向上滑动
                    if (dx > 0) {
                        isSlidingToLast = true;
                    } else {
                        isSlidingToLast = false;
                    }
                }
            });


        }
    }


    //todo:list2 列表 正在热映  Holder 类
    private class HotPlayHodler extends RecyclerView.ViewHolder {
        private final RecyclerView mRecyclerView;
        private final TextView mTextView;
        private final HotPlayAdapter hotPlayAdapter;

        public HotPlayHodler(View view) {
            super(view);
            mRecyclerView = view.findViewById(R.id.RecyclerView_item_hotplay);
            mTextView = view.findViewById(R.id.back_item_hotplay);
            hotPlayAdapter = new HotPlayAdapter();
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            mRecyclerView.setLayoutManager(linearLayoutManager);
            mRecyclerView.setAdapter(hotPlayAdapter);
        }

        public void setData(final List<HotPlayBean.ResultBean> hotresult) {
            hotPlayAdapter.setHotResult(hotresult);
            hotPlayAdapter.notifyDataSetChanged();
            mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                //用来标记是否正在向最后一个滑动
                boolean isSlidingToLast = false;

                @Override
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                    //设置什么布局管理器,就获取什么的布局管理器
                    LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    // 当停止滑动时
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        //获取最后一个完全显示的ItemPosition ,角标值
                        int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
                        //所有条目,数量值
                        int totalItemCount = manager.getItemCount();
                        // 判断是否滚动到底部，并且是向右滚动
                        if (lastVisibleItem == (totalItemCount - 1) && isSlidingToLast) {
                            //加载更多功能的代码
                            EventBus.getDefault().post(new PopularEvent(2));
                            hotPlayAdapter.addHotResult(hotresult);
                            hotPlayAdapter.notifyDataSetChanged();
                        }
                    }
                }

                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    //dx用来判断横向滑动方向，dy用来判断纵向滑动方向
                    //dx>0:向右滑动,dx<0:向左滑动
                    //dy>0:向下滑动,dy<0:向上滑动
                    if (dx > 0) {
                        isSlidingToLast = true;
                    } else {
                        isSlidingToLast = false;
                    }
                }
            });


        }
    }


    //todo:list3 列表 即将上映|正在上映   Holder 类
    private class PlayingHodler extends RecyclerView.ViewHolder {
        private final RecyclerView mRecyclerView;
        private final TextView mTextView;
        private final PlayingAdapter playingAdapter;

        public PlayingHodler(View view) {
            super(view);
            mRecyclerView = view.findViewById(R.id.RecyclerView_item_playing);
            mTextView = view.findViewById(R.id.back_item_playing);
            playingAdapter = new PlayingAdapter();
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            mRecyclerView.setLayoutManager(linearLayoutManager);
            mRecyclerView.setAdapter(playingAdapter);
        }

        public void setData(final List<PlayingBean.ResultBean> playresult) {
            playingAdapter.setPlayResult(playresult);
            playingAdapter.notifyDataSetChanged();
            mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                //用来标记是否正在向最后一个滑动
                boolean isSlidingToLast = false;

                @Override
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                    //设置什么布局管理器,就获取什么的布局管理器
                    LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    // 当停止滑动时
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        //获取最后一个完全显示的ItemPosition ,角标值
                        int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
                        //所有条目,数量值
                        int totalItemCount = manager.getItemCount();
                        // 判断是否滚动到底部，并且是向右滚动
                        if (lastVisibleItem == (totalItemCount - 1) && isSlidingToLast) {
                            //加载更多功能的代码
                            EventBus.getDefault().post(new PopularEvent(3));
                            playingAdapter.addPlayResult(playresult);
                            playingAdapter.notifyDataSetChanged();
                        }
                    }
                }

                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    //dx用来判断横向滑动方向，dy用来判断纵向滑动方向
                    //dx>0:向右滑动,dx<0:向左滑动
                    //dy>0:向下滑动,dy<0:向上滑动
                    if (dx > 0) {
                        isSlidingToLast = true;
                    } else {
                        isSlidingToLast = false;
                    }
                }
            });


        }
    }
}//----------|根布局adapter结束|----------


//┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━分割线━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
//┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━分割线━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛


/*-----------
 *🖐todo:说明
 *   自定义轮播图的适配器
 */
class CarouselAdapter extends RecyclerCoverFlow.Adapter<RecyclerCoverFlow.ViewHolder> {
    //非空判断工具类
    private EmptyUtil emptyUtil = new EmptyUtil();
    //吐司工具类
    private ToastUtil toast = new ToastUtil();
    //轮播数据
    private CarouselBean mCarouselBean = new CarouselBean();

    //轮播图数据 set方法
    public void setmCarouselBean(CarouselBean mCarouselBean) {
        this.mCarouselBean = mCarouselBean;
    }

    @NonNull
    @Override
    public RecyclerCoverFlow.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.carouse_item, viewGroup, false);
        return new Hodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerCoverFlow.ViewHolder viewHolder, int i) {
        Hodler holder = (Hodler) viewHolder;

        //设置跳转
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new JumpEvent(0));
            }
        });

        //查找图片控件
        SimpleDraweeView img = holder.itemView.findViewById(R.id.entrypicture_item_carouse_item);
        //查找文字控件
        TextView tv = holder.itemView.findViewById(R.id.moviename_item_carouse_item);
        //判断是否为空
        if (emptyUtil.isNull(mCarouselBean.getResult()) == false) {
            //计算循环下标
            int i1 = i % mCarouselBean.getResult().size();
            //获取集合
            CarouselBean.ResultBean resultBean = mCarouselBean.getResult().get(i1);
            //取出图片
            String imageUrl = resultBean.getImageUrl();
            //赋值
            img.setImageURI(Uri.parse(imageUrl));
            //取出文字并赋值
            tv.setText(resultBean.getName());

        } else {
            toast.Toast("请求数据有误");
        }


    }

    //todo:条目总数 最大整型值
    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    private class Hodler extends RecyclerView.ViewHolder {
        public Hodler(View view) {
            super(view);
        }
    }
}//----------|轮播图适配器函数结束|----------


//┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━分割线━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
//┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━分割线━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛

/*-----------
 *🖐说明:
 *   热门电影 adapter 管理类
 */
class PopularAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //非空判断工具类
    private EmptyUtil emptyUtil = new EmptyUtil();

    //吐司工具类
    private ToastUtil toast = new ToastUtil();


    //热门电影数据 list
    private List<PopularBean.ResultBean> result = new ArrayList<>();

    //热门电影 数据 set 方法
    public void setResult(List<PopularBean.ResultBean> result) {
        if (emptyUtil.isNull(this.result) == false) {
            this.result.clear();
        }
        this.result.addAll(result);
    }

    //热门电影 add 方法
    public void addResult(List<PopularBean.ResultBean> result) {
        this.result.addAll(result);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.popular_item, viewGroup, false);
        return new Hodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Hodler holder = (Hodler) viewHolder;
        //查找图片控件
        SimpleDraweeView img = holder.itemView.findViewById(R.id.entrypicture_item_popular_item);
        //查找文字控件
        TextView tv = holder.itemView.findViewById(R.id.moviename_item_popular_item);
        //判断是否为空
        if (emptyUtil.isNull(result) == false) {
            PopularBean.ResultBean resultBean = result.get(i);
            //取出图片
            String imageUrl = resultBean.getImageUrl();
            //赋值
            img.setImageURI(Uri.parse(imageUrl));
            //取出文字并赋值
            tv.setText(resultBean.getName());
        } else {
            toast.Toast("请求数据有误");
        }

    }

    @Override
    public int getItemCount() {
        //非空判断
        if (emptyUtil.isNull(result) == false) {
            return result.size();
        }
        return 0;
    }

    //内部 holder 类
    private class Hodler extends RecyclerView.ViewHolder {
        public Hodler(View view) {
            super(view);
        }
    }
}//----------|热门电影适配器结束|----------


//┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━分割线━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
//┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━分割线━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛


/*-----------
 *🖐说明:
 *   正在热映 适配器
 */
class HotPlayAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //非空判断工具类
    private EmptyUtil emptyUtil = new EmptyUtil();

    //吐司工具类
    private ToastUtil toast = new ToastUtil();


    //正在热映 数据 List
    private List<HotPlayBean.ResultBean> hotresult = new ArrayList<>();


    //正在热映 数据 set 方法
    public void setHotResult(List<HotPlayBean.ResultBean> hotresult) {
        if (emptyUtil.isNull(this.hotresult) == false) {
            this.hotresult.clear();
        }
        this.hotresult.addAll(hotresult);
    }

    //正在热映 add 方法
    public void addHotResult(List<HotPlayBean.ResultBean> hotresult) {
        if (emptyUtil.isNull(hotresult)) {
            toast.Toast("没有更多了");
        }
        this.hotresult.addAll(hotresult);

    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.popular_item, viewGroup, false);
        return new Hodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Hodler holder = (Hodler) viewHolder;
        //查找图片控件
        SimpleDraweeView img = holder.itemView.findViewById(R.id.entrypicture_item_popular_item);
        //查找文字控件
        TextView tv = holder.itemView.findViewById(R.id.moviename_item_popular_item);
        //判断是否为空
        if (emptyUtil.isNull(hotresult) == false) {
            HotPlayBean.ResultBean resultBean = hotresult.get(i);
            //取出图片
            String imageUrl = resultBean.getImageUrl();
            //赋值
            img.setImageURI(Uri.parse(imageUrl));
            //取出文字并赋值
            tv.setText(resultBean.getName());
        } else {
            toast.Toast("请求数据有误");
        }

    }

    @Override
    public int getItemCount() {
        //非空判断
        if (emptyUtil.isNull(hotresult) == false) {
            return hotresult.size();
        }
        return 0;
    }

    //内部 holder 类
    private class Hodler extends RecyclerView.ViewHolder {
        public Hodler(View view) {
            super(view);
        }
    }
}//----------|正在热映适配器结束|----------


//┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━分割线━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
//┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━分割线━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛


/*-----------
 *🖐说明:
 *   正在上映 adapter
 */
class PlayingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //非空判断工具类
    private EmptyUtil emptyUtil = new EmptyUtil();

    //吐司工具类
    private ToastUtil toast = new ToastUtil();


    // 即将上映 || 正在上映 数据 list
    private List<PlayingBean.ResultBean> playresult = new ArrayList<>();

    //即将上映 || 正在上映  数据 set 方法
    public void setPlayResult(List<PlayingBean.ResultBean> playresult) {
        if (emptyUtil.isNull(this.playresult) == false) {
            this.playresult.clear();
        }
        this.playresult.addAll(playresult);
    }

    //即将上映 || 正在上映 数据 add 方法
    public void addPlayResult(List<PlayingBean.ResultBean> playresult) {
        if (emptyUtil.isNull(playresult)) {
            toast.Toast("没有更多了");
        }
        this.playresult.addAll(playresult);
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.popular_item, viewGroup, false);
        return new Hodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Hodler holder = (Hodler) viewHolder;
        //查找图片控件
        SimpleDraweeView img = holder.itemView.findViewById(R.id.entrypicture_item_popular_item);
        //查找文字控件
        TextView tv = holder.itemView.findViewById(R.id.moviename_item_popular_item);
        //判断是否为空
        if (emptyUtil.isNull(playresult) == false) {
            PlayingBean.ResultBean resultBean = playresult.get(i);
            //取出图片
            String imageUrl = resultBean.getImageUrl();
            //赋值
            img.setImageURI(Uri.parse(imageUrl));
            //取出文字并赋值
            tv.setText(resultBean.getName());
        } else {
            toast.Toast("请求数据有误");
        }

    }

    @Override
    public int getItemCount() {
        //非空判断
        if (emptyUtil.isNull(playresult) == false) {
            return playresult.size();
        }
        return 0;
    }

    //内部 holder 类
    private class Hodler extends RecyclerView.ViewHolder {
        public Hodler(View view) {
            super(view);
        }
    }

}//----------|正在上映适配器函数 结束|----------