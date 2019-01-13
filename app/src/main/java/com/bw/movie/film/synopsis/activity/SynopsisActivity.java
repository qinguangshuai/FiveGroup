package com.bw.movie.film.synopsis.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.cinema.fragment.ChuanUser;
import com.bw.movie.error.AppManager;
import com.bw.movie.film.cinema.activity.AffiliatedTheaterActivity;
import com.bw.movie.film.details.presenter.DetailPresenter;
import com.bw.movie.film.popwindow.ScrollWindow;
import com.bw.movie.film.synopsis.bean.ResultBean;
import com.bw.movie.film.synopsis.popwindow.adapter.PopupWindow2Adapter;
import com.bw.movie.film.synopsis.popwindow.adapter.PopupWindow4Adapter;
import com.bw.movie.film.synopsis.popwindow.adapter.Popupwindow1Adapter;
import com.bw.movie.film.synopsis.popwindow.adapter.StillsAdapder;
import com.bw.movie.film.synopsis.popwindow.adapter.StillsItem;
import com.bw.movie.film.synopsis.adapter.WeakCurrencyAdapter;
import com.bw.movie.film.synopsis.bean.CommentBean;
import com.bw.movie.film.details.bean.DetailBean;
import com.bw.movie.film.synopsis.bean.PraiseBean;
import com.bw.movie.film.event.PraiseEvent;
import com.bw.movie.film.synopsis.bean.InputcommentsBean;
import com.bw.movie.film.event.RefreshEvent;
import com.bw.movie.film.synopsis.presenter.SynopsisPresenter;
import com.bw.movie.film.synopsis.view.CommentView;
import com.bw.movie.film.details.view.DetailView;
import com.bw.movie.film.synopsis.view.InputcommentsView;
import com.bw.movie.film.synopsis.view.PraiseView;
import com.bw.movie.login.LoginActivity;
import com.bw.movie.util.EmptyUtil;
import com.bw.movie.util.RecyclerViewScrollUtil;
import com.bw.movie.util.ToastUtil;
import com.dak.weakview.adapter.viewholder.WeakCurrencyViewHold;
import com.dak.weakview.layout.WeakCardOverlapLayout;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.postprocessors.IterativeBoxBlurPostProcessor;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

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
    @BindView(R.id.hart_synopsis)
    CheckBox mHartSynopsis;
    @BindView(R.id.buy_synopsis)
    Button mBuySynopsis;
    @BindView(R.id.imagereturnsynopsis)
    ImageView imagereturnsynopsis;
    private WeakCurrencyAdapter<String> adapter;
    private View mTrail;
    private View mStills;
    private View mReview;
    private View mDatail;
    private int a = 1;
    private int id;
    private PopupWindow popupWindow;
    private PopupWindow popupWindow2;
    private PopupWindow popupWindow3;
    private PopupWindow popupWindow4;
    private boolean flag;
    private PopupWindow4Adapter mPopupWindow4Adapter = new PopupWindow4Adapter();
    private ScrollWindow mScrollWindow = new ScrollWindow(this);
    @Override
    public void initView() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        ButterKnife.bind(this);
        mDatail = View.inflate(this, R.layout.popupwindow_datail, null);        //详情
        mTrail = View.inflate(this, R.layout.popupwindow_trail, null);        //预告
        mStills = View.inflate(this, R.layout.popupwindow_stills, null);      //剧照
        mReview = View.inflate(this, R.layout.popupwindow_synopsis, null);    //影评
        Intent intent = getIntent();
        id = intent.getIntExtra("详情id", -1);
        setCardSynopsis();
        getData(id);
        setmReview();
        getCommentData(id, a, 10);
        mPopupWindow4Adapter.notifyDataSetChanged();
    }

    @Override
    public void initListener() {
        imagereturnsynopsis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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


    //点赞
    @Subscribe
    public void good(final PraiseEvent praiseEvent) {
        new SynopsisPresenter(new PraiseView<PraiseBean>() {
            @Override
            public void onDataSuccess(PraiseBean praiseBean) {
                ToastUtil.Toast(praiseBean.getMessage());
                if (praiseBean.getMessage().equals("点赞成功")) {
                    praiseEvent.getRadioButton().setText((praiseEvent.getNum() + 1) + "");
                } else if (praiseBean.getMessage().equals("不能重复点赞")) {

                } else {
                    praiseEvent.getRadioButton().setChecked(false);
                }
            }

            @Override
            public void onDataFailer(String msg) {
                ToastUtil.Toast(msg);
            }

            @Override
            public void onShowLoading() {

            }

            @Override
            public void onHideLoading() {

            }
        }).getPraiseBeanObservable(praiseEvent.getI());
    }


    //判断并销毁视频 的 播放
    public void isPlay() {
        if (popupWindow2.isShowing()) {

        } else {
            if (JCVideoPlayer.backPress()) {
                return;
            }
            JCVideoPlayer.releaseAllVideos();
        }
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
        new SynopsisPresenter(new CommentView<CommentBean>() {
            @Override
            public void onDataSuccess(CommentBean commentBean) {
                List<ResultBean> result = commentBean.getResult();
                if (EmptyUtil.isNull(result) == false) {
                    mPopupWindow4Adapter.addResult(commentBean.getResult());
                    mPopupWindow4Adapter.notifyDataSetChanged();

                } else {
                    mScrollWindow.dismissPop();
                    ToastUtil.Toast("没有更多了");
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

    //请求数据
    public void getData(int id) {
        new DetailPresenter(new DetailView<DetailBean>() {
            @Override
            public void onDataSuccess(final DetailBean detailBean) {
                //拆装
                DetailBean.ResultBean result = detailBean.getResult();
                //获取图片集合
                List<String> posterList = result.getPosterList();
                //将数据装进适配器
                adapter.refreshData(posterList);
                //给tv 赋值
                mTitleSynopsis.setText(result.getName());
                //红心
                mHartSynopsis.setChecked(result.getFollowMovie() == 2 ? false : true);
                //点亮
                mHartSynopsis.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        EventBus.getDefault().post(new RefreshEvent(isChecked == true ? true : false, detailBean.getResult().getId()));
                    }
                });
                //跳转
                mBuySynopsis.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(SynopsisActivity.this, AffiliatedTheaterActivity.class);
                        intent.putExtra("id", detailBean.getResult().getId());
                        intent.putExtra("name", detailBean.getResult().getName());
                        startActivity(intent);
                    }
                });
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
                .setPostprocessor(new IterativeBoxBlurPostProcessor(2, 2))
                .build();
        AbstractDraweeController controller = Fresco.newDraweeControllerBuilder()
                .setOldController(draweeView.getController())
                .setImageRequest(request)
                .build();
        draweeView.setController(controller);
    }

    //第一个Popupwindow 的数据
    private void setmDatail(DetailBean.ResultBean result) {
        //获取图片集合
        List<String> posterList = result.getPosterList();
        //找到控件
        TextView back = mDatail.findViewById(R.id.back_pop_datail);
        //关闭方法
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        //查找控件
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
        Popupwindow1Adapter popupwindow1Adapter = new Popupwindow1Adapter();
        popupwindow1Adapter.setData(split);
        mRecyclerView.setAdapter(popupwindow1Adapter);
    }


    //第二个Popupwindow  mTrail  预告片
    public void setTrail(DetailBean.ResultBean result) {
        final List<DetailBean.ResultBean.ShortFilmListBean> shortFilmList = result.getShortFilmList();
        ImageView back = mTrail.findViewById(R.id.back_pop_trail);
        //销毁
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow2.dismiss();
                isPlay();

            }
        });
        RecyclerView mRecyclerView = mTrail.findViewById(R.id.Recyclerview_pop_trail);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        PopupWindow2Adapter popupWindow2Adapter = new PopupWindow2Adapter(this);
        popupWindow2Adapter.setShortFilmList(shortFilmList);
        mRecyclerView.setAdapter(popupWindow2Adapter);
    }

    //第三个popupwindow  剧照
    public void setStills(DetailBean.ResultBean result) {
        RecyclerView mRecyclerView = mStills.findViewById(R.id.Recyclerview_pop_stills);
        ImageView back = mStills.findViewById(R.id.back_pop_stills);
        //销毁
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow3.dismiss();
            }
        });
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        List<StillsItem> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new StillsItem(1));
            list.add(new StillsItem(2));
        }
        List<String> posterList = result.getPosterList();
        StillsAdapder stillsAdapder = new StillsAdapder(list);
        mRecyclerView.setAdapter(stillsAdapder);
        stillsAdapder.setPoster(posterList);
        mRecyclerView.setLayoutManager(gridLayoutManager);
    }


    //第四个pop
    public void setmReview() {
        final RecyclerView mRecyclerView = mReview.findViewById(R.id.Recyclerview_pop_synopsis);
        final ImageView imageView = mReview.findViewById(R.id.writemessage);
        ImageView back = mReview.findViewById(R.id.back_pop_synopsis);
        //取消 pop
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow4.dismiss();
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mPopupWindow4Adapter);
        final List<ResultBean> result = mPopupWindow4Adapter.getResult();
        mPopupWindow4Adapter.setGetData(new PopupWindow4Adapter.getData() {
            @Override
            public void isData(View view, final int position) {
                if (flag) {
                    imageView.setVisibility(View.GONE);
                    flag = false;
                } else {
                    imageView.setVisibility(View.VISIBLE);
                    flag = true;
                }

                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getpop(result,position);
                    }
                });
            }
        });

        //抽取方法 上拉 加载更多
        RecyclerViewScrollUtil.Scroll(mRecyclerView, true, new RecyclerViewScrollUtil.onEvent() {
            @Override
            public void info() {
                a++;
                //加载更多功能的代码
                getCommentData(id, a, 10);
            }
        });
    }
    public void getpop(final List<ResultBean> result, final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(SynopsisActivity.this);
        View view = View.inflate(SynopsisActivity.this, R.layout.alertdialogitem, null);
        builder.setView(view);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
        final EditText Inputcomments = view.findViewById(R.id.inputcomments);
        TextView SendInputcomments = view.findViewById(R.id.sendinputcomments);
        SendInputcomments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String trim = Inputcomments.getText().toString().trim();
                if (TextUtils.isEmpty(trim)) {
                    ToastUtil.Toast("请输入内容");
                } else {
                    new SynopsisPresenter(new InputcommentsView<InputcommentsBean>() {
                        @Override
                        public void onDataSuccess(InputcommentsBean inputcommentsBean) {
                            ToastUtil.Toast(inputcommentsBean.getMessage());
                            if (inputcommentsBean.getMessage().contains("成功")) {
                                alertDialog.dismiss();
                            }
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
                    }).getInputcomments(result.get(position).getCommentId(), trim);
                }
            }
        });
    }

    @Subscribe
    public void getChuan(ChuanUser chuanUser) {
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
        AppManager.getAppManager().finishAllActivity();
    }

    //暂停
    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    //暂停
    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }


    //注销
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    //点击事件
    @OnClick({R.id.rb_Datail_synopsis, R.id.rb_Trail_synopsis, R.id.rb_Stills_synopsis, R.id.rb_Review_synopsis})
    public void onViewClicked(View v) {
        WindowManager windowManager = this.getWindowManager();
        int height = windowManager.getDefaultDisplay().getHeight();
        switch (v.getId()) {
            case R.id.rb_Datail_synopsis:
                popupWindow = new PopupWindow(mDatail, LinearLayout.LayoutParams.MATCH_PARENT, height * 5 / 6);
                popupWindow.setAnimationStyle(R.style.popwin_anim_style);
                popupWindow.showAtLocation(v.getRootView(), Gravity.BOTTOM, 0, 0);
                break;
            case R.id.rb_Trail_synopsis:
                popupWindow2 = new PopupWindow(mTrail, LinearLayout.LayoutParams.MATCH_PARENT, height * 5 / 6);
                popupWindow2.setAnimationStyle(R.style.popwin_anim_style);
                popupWindow2.showAtLocation(v.getRootView(), Gravity.BOTTOM, 0, 0);
                break;
            case R.id.rb_Stills_synopsis:
                popupWindow3 = new PopupWindow(mStills, LinearLayout.LayoutParams.MATCH_PARENT, height * 5 / 6);
                popupWindow3.setAnimationStyle(R.style.popwin_anim_style);
                popupWindow3.showAtLocation(v.getRootView(), Gravity.BOTTOM, 0, 0);
                break;
            case R.id.rb_Review_synopsis:
                popupWindow4 = new PopupWindow(mReview, LinearLayout.LayoutParams.MATCH_PARENT, height * 5 / 6);
                popupWindow4.setAnimationStyle(R.style.popwin_anim_style);
                popupWindow4.showAtLocation(v.getRootView(), Gravity.BOTTOM, 0, 0);
                break;
        }
    }


}



