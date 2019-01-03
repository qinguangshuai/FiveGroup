package com.bw.movie.film.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.film.adapter.StillsAdapder;
import com.bw.movie.film.adapter.StillsItem;
import com.bw.movie.film.adapter.WeakCurrencyAdapter;
import com.bw.movie.film.bean.CommentBean;
import com.bw.movie.film.bean.DetailBean;
import com.bw.movie.film.p.FilmProsenter;
import com.bw.movie.film.v.CommentView;
import com.bw.movie.film.v.DetailView;
import com.bw.movie.util.EmptyUtil;
import com.bw.movie.util.ToastUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dak.weakview.adapter.viewholder.WeakCurrencyViewHold;
import com.dak.weakview.layout.WeakCardOverlapLayout;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.postprocessors.IterativeBoxBlurPostProcessor;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.squareup.picasso.Picasso;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/*-----------
 *🖐说明:
 *       详情 页面 Activity
 *       标题
 *       轮播图
 *       4个按钮
 */

public class SynopsisActivity extends BaseActivity {


    @BindView(R.id.card_synopsis)
    WeakCardOverlapLayout mCardSynopsis;
    @BindView(R.id.title_synopsis)
    TextView mTitleSynopsis;
    @BindView(R.id.background_synopsis)
    SimpleDraweeView mBackgroundSynopsis;
    @BindView(R.id.rb_Datail_synopsis)
    Button rbDatailSynopsis;
    @BindView(R.id.rb_Trail_synopsis)
    Button rbTrailSynopsis;
    @BindView(R.id.rb_Stills_synopsis)
    Button rbStillsSynopsis;
    @BindView(R.id.rb_Review_synopsis)
    Button rbReviewSynopsis;
    private WeakCurrencyAdapter<String> adapter;
    private View mTrail;
    private View mStills;
    private View mReview;
    private View mDatail;
    private RecyclerView.Adapter adapter1;
    private int a = 1;
    private int id;
    private EmptyUtil emptyUtil;
    private ToastUtil toast;
    private ArrayList<CommentBean.ResultBean> list = new ArrayList<>();


    @Override
    public void initView() {
        ButterKnife.bind(this);
        mDatail = View.inflate(this, R.layout.popupwindow_datail, null);        //详情
        mTrail = View.inflate(this, R.layout.popupwindow_trail, null);        //预告
        mStills = View.inflate(this, R.layout.popupwindow_stills, null);      //剧照
        mReview = View.inflate(this, R.layout.popupwindow_synopsis, null);    //影评
        emptyUtil = new EmptyUtil();
        toast = new ToastUtil();
        Intent intent = getIntent();
        id = intent.getIntExtra("详情id", -1);
        setCardSynopsis();
        getData(id);
        getCommentData(id, 1, 10);
    }

    @Override
    public void initListener() {
    }

    @Override
    public void initData() {
    }

    @Override
    public int initLayoutId() {
        return R.layout.activity_synopsis;
    }

    @Override
    public void initVariable() {
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }


    //向第三方控件中set 数据
    public void setCardSynopsis() {
        mCardSynopsis.setParentClipChild(false);
        mCardSynopsis.setOnItemClickListener(new WeakCardOverlapLayout.OnItemClickListener() {
            @Override
            public void onWeakItemClickListener(int i, View view) {
            }
        });

        adapter = new WeakCurrencyAdapter<String>(this, R.layout.item_diycard) {
            @Override
            public void notifyItemView(WeakCurrencyViewHold holder, String item, int position) {
                Uri uri = Uri.parse(item);
                View convertView = holder.getConvertView();
                SimpleDraweeView img = convertView.findViewById(R.id.img_diycard);
                img.setImageURI(uri);
            }
        };
        mCardSynopsis.setAdapter(adapter);
    }


    //请求评论数据
    public void getCommentData(int id, int page, int count) {
        new FilmProsenter(new CommentView<CommentBean>() {
            @Override
            public void onDataSuccess(CommentBean commentBean) {
                List<CommentBean.ResultBean> result = commentBean.getResult();
                if (emptyUtil.isNull(result) == false) {
                    list.addAll(result);
                    setmReview(list);
                } else {
                    toast.Toast("没有更多了");
                }
            }

            @Override
            public void onDataFailer(String msg) {
                Toast.makeText(SynopsisActivity.this, msg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onShowLoading() {

            }

            @Override
            public void onHideLoading() {

            }
        }).getCommentBeanObservable(id, page, count);
    }


    //第四个pop
    public void setmReview(final List<CommentBean.ResultBean> result) {
        RecyclerView mRecyclerView = mReview.findViewById(R.id.Recyclerview_pop_synopsis);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(new RecyclerView.Adapter() {
            class Holder extends RecyclerView.ViewHolder {
                private final SimpleDraweeView mDraweeView;
                private final TextView mName;
                private final TextView mTime;
                private final TextView mContext;
                private final CheckBox mGood;
                private final CheckBox mComment;

                public Holder(View view) {
                    super(view);
                    mDraweeView = view.findViewById(R.id.img_item_comment);
                    mName = view.findViewById(R.id.name_item_comment);
                    mContext = view.findViewById(R.id.context_item_comment);
                    mTime = view.findViewById(R.id.time_item_comment);
                    mGood = view.findViewById(R.id.good_item_comment);
                    mComment = view.findViewById(R.id.comment_item_comment);
                }

                public void setData(CommentBean.ResultBean resultBean) {
                    mDraweeView.setImageURI(Uri.parse(resultBean.getCommentHeadPic()));
                    mName.setText(resultBean.getCommentUserName());
                    long browseTime = resultBean.getCommentTime();
                    GregorianCalendar gc = new GregorianCalendar();
                    String s = String.valueOf(browseTime);
                    gc.setTimeInMillis(Long.parseLong(s));
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    mTime.setText(df.format(gc.getTime()));
                    mGood.setText(resultBean.getReplyNum() + "");
                    mComment.setText(resultBean.getGreatNum() + "");
                    mContext.setText(resultBean.getCommentContent() + "");
                }
            }

            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_popcomment, viewGroup, false);
                return new Holder(view);
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                Holder holder = (Holder) viewHolder;
                holder.setData(result.get(i));
            }

            @Override
            public int getItemCount() {
                return result.size();
            }
        });


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
                        a++;
                        getCommentData(id, a, 10);
                    }
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //dx用来判断横向滑动方向，dy用来判断纵向滑动方向
                //dx>0:向右滑动,dx<0:向左滑动
                //dy>0:向下滑动,dy<0:向上滑动
                if (dy > 0) {
                    isSlidingToLast = true;
                } else {
                    isSlidingToLast = false;
                }
            }
        });

    }


    //请求数据
    public void getData(int id) {
        new FilmProsenter(new DetailView<DetailBean>() {
            @Override
            public void onDataSuccess(DetailBean detailBean) {
                //拆装
                DetailBean.ResultBean result = detailBean.getResult();
                //获取图片集合
                List<String> posterList = result.getPosterList();
                //将数据装进适配器
                adapter.refreshData(posterList);

                //给tv 赋值
                mTitleSynopsis.setText(result.getName());
                //给背景 赋值
                onBlurry(Uri.parse(posterList.get(0)), mBackgroundSynopsis);
                //为第一个pop 添加数据
                setmDatail(result);
                //预告片
                setTrail(result);
                //剧照
                setStills(result);
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
        }).getDetailBeanObservable(id);
    }


    //高斯模糊效果
    public void onBlurry(Uri uri, SimpleDraweeView draweeView) {
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                //参数1:重度
                //参数2:半径
                .setPostprocessor(new IterativeBoxBlurPostProcessor(5, 5))
                .build();
        AbstractDraweeController controller = Fresco.newDraweeControllerBuilder()
                .setOldController(draweeView.getController())
                .setImageRequest(request)
                .build();
        draweeView.setController(controller);
    }


    //第一个pop setmDatail 中的控件
    private void setmDatail(DetailBean.ResultBean result) {
        //获取图片集合
        List<String> posterList = result.getPosterList();

        //找到控件
        RecyclerView mRecyclerView = mDatail.findViewById(R.id.Recyclerview_pop_datail);
        TextView mPlot = mDatail.findViewById(R.id.Plot_pop_datail); //剧情
        TextView mArea = mDatail.findViewById(R.id.area_pop_datail); //产地
        TextView mTime = mDatail.findViewById(R.id.time_pop_datail); //时长
        TextView mDirector = mDatail.findViewById(R.id.director_pop_datail); //导演
        TextView mTyep = mDatail.findViewById(R.id.type_pop_datail);  //类型
        SimpleDraweeView mImg = mDatail.findViewById(R.id.img_pop_datail); //图片
        mImg.setImageURI(Uri.parse(posterList.get(0))); //第一个pop 图片
        mPlot.setText(result.getSummary()); //给剧情赋值
        mArea.setText("地区:" + result.getPlaceOrigin());  //地区
        mTime.setText("时长:" + result.getDuration());  //时长
        mDirector.setText("导演:" + result.getDirector());  //导演
        mTyep.setText("类型:" + result.getMovieTypes());  //类型
        String starring = result.getStarring();
        final String[] split = starring.split(",");
        //适配器!!
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(new RecyclerView.Adapter() {
            class Holder extends RecyclerView.ViewHolder {
                private final TextView tv;

                public Holder(View view) {
                    super(view);
                    tv = view.findViewById(R.id.tv_item_poprecy);
                }
            }

            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_poprecy, viewGroup, false);
                return new Holder(view);
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                Holder holder = (Holder) viewHolder;
                holder.tv.setText(split[i]);
            }

            @Override
            public int getItemCount() {
                return split.length;
            }
        });

    }


    //mTrail  预告片
    public void setTrail(DetailBean.ResultBean result) {
        final List<DetailBean.ResultBean.ShortFilmListBean> shortFilmList = result.getShortFilmList();
        RecyclerView mRecyclerView = mTrail.findViewById(R.id.Recyclerview_pop_trail);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        adapter1 = new RecyclerView.Adapter() {

            class Holder extends RecyclerView.ViewHolder {
                private final JCVideoPlayerStandard mJcVideoPlayerStandard;

                public Holder(View view) {
                    super(view);
                    mJcVideoPlayerStandard = view.findViewById(R.id.video_item_popvideo);
                }
            }

            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_popvideo, viewGroup, false);
                return new Holder(view);
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                Holder holder = (Holder) viewHolder;
                holder.mJcVideoPlayerStandard.setUp(shortFilmList.get(i).getVideoUrl(), JCVideoPlayer.SCREEN_LAYOUT_NORMAL);
                Picasso.with(SynopsisActivity.this)
                        .load(shortFilmList.get(i).getImageUrl())
                        .into(holder.mJcVideoPlayerStandard.thumbImageView);
            }

            @Override
            public int getItemCount() {
                return shortFilmList.size();
            }


        };
        mRecyclerView.setAdapter(adapter1);
    }


    //剧照
    public void setStills(final DetailBean.ResultBean result) {
        RecyclerView mRecyclerView = mStills.findViewById(R.id.Recyclerview_pop_stills);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        List<DetailBean.ResultBean> list = new ArrayList<>();
        for (int i = 0; i <5 ; i++) {
            list.add(new DetailBean.ResultBean(1));
            list.add(new DetailBean.ResultBean(2));
        }

        List<String> posterList = result.getPosterList();
        StillsAdapder stillsAdapder = new StillsAdapder(list);
        mRecyclerView.setAdapter(stillsAdapder);
         stillsAdapder.setPoster(posterList);
        mRecyclerView.setLayoutManager(gridLayoutManager);


    }


    //暂停
    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    //销毁
    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

    //点击事件
    @OnClick({R.id.rb_Datail_synopsis, R.id.rb_Trail_synopsis, R.id.rb_Stills_synopsis, R.id.rb_Review_synopsis})
    public void onViewClicked(View v) {
        WindowManager windowManager = this.getWindowManager();
        int height = windowManager.getDefaultDisplay().getHeight();
        switch (v.getId()) {
            case R.id.rb_Datail_synopsis:
                PopupWindow popupWindow = new PopupWindow(mDatail, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                popupWindow.setBackgroundDrawable(new ColorDrawable());
                popupWindow.setOutsideTouchable(true);

                popupWindow.showAsDropDown(v, 0, -(height * 2 / 3) + 40);
                break;
            case R.id.rb_Trail_synopsis:
                PopupWindow popupWindow2 = new PopupWindow(mTrail, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                popupWindow2.setBackgroundDrawable(new ColorDrawable());
                popupWindow2.setOutsideTouchable(true);
                popupWindow2.showAsDropDown(v, 0, -(height * 2 / 3) + 40);
                break;
            case R.id.rb_Stills_synopsis:
                PopupWindow popupWindow3 = new PopupWindow(mStills, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                popupWindow3.setBackgroundDrawable(new ColorDrawable());
                popupWindow3.setOutsideTouchable(true);
                popupWindow3.showAsDropDown(v, 0, -(height * 2 / 3) + 40);
                break;
            case R.id.rb_Review_synopsis:
                PopupWindow popupWindow4 = new PopupWindow(mReview, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                popupWindow4.setBackgroundDrawable(new ColorDrawable());
                popupWindow4.setOutsideTouchable(true);
                popupWindow4.showAsDropDown(v, 0, -(height * 2 / 3) + 40);
                break;
        }
    }


}



