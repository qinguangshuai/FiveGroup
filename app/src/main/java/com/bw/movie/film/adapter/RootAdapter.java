package com.bw.movie.film.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.custom.ScrollView;
import com.bw.movie.custom.SearchView;
import com.bw.movie.film.bean.CarouselBean;
import com.bw.movie.film.bean.HotPlayBean;
import com.bw.movie.film.bean.PlayingBean;
import com.bw.movie.film.bean.PopularBean;
import com.bw.movie.film.event.JumpEvent;
import com.bw.movie.film.event.PopularEvent;
import com.bw.movie.util.EmptyUtil;
import com.bw.movie.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import recycler.coverflow.CoverFlowLayoutManger;
import recycler.coverflow.RecyclerCoverFlow;

/*-----------
 *🖐todo:根布局的recyclerview的适配器
 * 轮播布局+自定义搜索框+定位(未完成) √
 * list1                             √
 * list2                             √
 * list3                             √
 *
 */
public class RootAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

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
                return new RootAdapter.CarouseHolder(view);
            case 1:
                //热门布局
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.film_fragment_item_popular, viewGroup, false);
                return new RootAdapter.PopularHodler(view);
            case 2:
                //热映
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.film_fragment_item_hotplay, viewGroup, false);
                return new RootAdapter.HotPlayHodler(view);
            case 3:
                //上映Ing
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.film_fragment_item_palying, viewGroup, false);
                return new RootAdapter.PlayingHodler(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        //轮播
        if (viewHolder instanceof RootAdapter.CarouseHolder) {
            RootAdapter.CarouseHolder carouseHolder = (RootAdapter.CarouseHolder) viewHolder;
            carouseHolder.setData(mCarouselBean);

        }
        //list1 热门
        else if (viewHolder instanceof RootAdapter.PopularHodler) {
            RootAdapter.PopularHodler popularHodler = (RootAdapter.PopularHodler) viewHolder;
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
        else if (viewHolder instanceof RootAdapter.HotPlayHodler) {
            RootAdapter.HotPlayHodler hotPlayHodler = (RootAdapter.HotPlayHodler) viewHolder;
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
        else if (viewHolder instanceof RootAdapter.PlayingHodler) {
            RootAdapter.PlayingHodler playingHodler = (RootAdapter.PlayingHodler) viewHolder;
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
        private final RecyclerCoverFlow mRecyclerCoverFlow;
        private final ScrollView mScrollView;
        private final CheckBox mPositinging;
        private final SearchView mSearch;
        private final CarouselAdapter mCarouselAdapter;

        public CarouseHolder(View view) {
            super(view);
            mSearch = view.findViewById(R.id.search_item_carouse);
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


            //搜索
            mSearch.setClick(new SearchView.Click() {
                @Override
                public void onClickListener(View v, String s) {
                    toast.Toast(s);
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
