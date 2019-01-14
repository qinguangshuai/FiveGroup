package com.bw.movie.my;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BaseEvent;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.cinema.fragment.ChuanUser;
import com.bw.movie.error.AppManager;
import com.bw.movie.film.popwindow.ScrollWindow;
import com.bw.movie.login.LoginActivity;
import com.bw.movie.my.mysound.MySoundAdapter;
import com.bw.movie.my.mysound.MySoundPresenter;
import com.bw.movie.my.mysound.MySoundUser;
import com.bw.movie.my.mysound.MySoundView;
import com.bw.movie.my.mysound.ResultBean;
import com.bw.movie.my.mysound.UpdateSoundPresenter;
import com.bw.movie.my.mysound.UpdateSoundUser;
import com.bw.movie.my.mysound.UpdateSoundView;
import com.bw.movie.my.mysound.XiSoundPresenter;
import com.bw.movie.my.mysound.XiSoundUser;
import com.bw.movie.my.mysound.XiSoundView;
import com.bw.movie.util.RecyclerViewScrollUtil;
import com.bw.movie.util.ToastUtil;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
 *秦广帅
 *通知系统消息
 * */
public class MySoundActivity extends BaseActivity implements MySoundView<MySoundUser> {

    @BindView(R.id.soundtext)
    TextView soundtext;
    @BindView(R.id.soundrecycle)
    RecyclerView mSoundrecycle;
    @BindView(R.id.soundimage)
    ImageView soundimage;
    @BindView(R.id.sounSwipeRefreshLayout)
    SwipeRefreshLayout mSounSwipeRefreshLayout;
    private MySoundPresenter mMySoundPresenter;
    private int mCount;
    //private int mId;
    private int page = 1;
    private MySoundAdapter mMySoundAdapter;
    private List<ResultBean> mList;
    private ScrollWindow mScrollWindow = new ScrollWindow(this);

    @Override
    public void initView() {
        ButterKnife.bind(this);
        showloading();
        mMySoundPresenter = new MySoundPresenter(this);
        BaseEvent.register(this);
    }

    @Override
    public void initListener() {
        mMySoundPresenter.getSound(page);

      /* RecyclerViewScrollUtil.Refresh(mSounSwipeRefreshLayout, 2000, new RecyclerViewScrollUtil.onEvent() {
           @Override
           public void info() {
               showloading();
               mMySoundPresenter.getSound(page);


           }
       });*/
        mSounSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                showloading();
                mMySoundPresenter.getSound(page);
            }
        });

       RecyclerViewScrollUtil.Scroll(mSoundrecycle, true, new RecyclerViewScrollUtil.onEvent() {
           @Override
           public void info() {
               showloading();
               mScrollWindow.showPop(mSoundrecycle);
               mMySoundPresenter.getSound(page++);
           }
       });
    }

    @Override
    public void initData() {
        new XiSoundPresenter(new XiSoundView<XiSoundUser>() {
            @Override
            public void onDataSuccess(XiSoundUser xiSoundUser) {

                mCount = xiSoundUser.getCount();
                soundtext.setText("系统消息  (" + mCount + "条未读" + ")");
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
        }).getSound();
    }

    @Override
    public int initLayoutId() {
        return R.layout.activity_sound;
    }

    @Override
    public void initVariable() {

    }

    @Subscribe
    public void getChuan(ChuanUser chuanUser) {
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
        AppManager.getAppManager().finishActivity(this);
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public void onDataSuccess(final MySoundUser mySoundUser) {
        showContent();
        mSounSwipeRefreshLayout.setRefreshing(false);
        mList = mySoundUser.getResult();
        if(mList!=null && mList.size()>0){
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            mSoundrecycle.setLayoutManager(linearLayoutManager);
            mMySoundAdapter = new MySoundAdapter(getApplicationContext(), mList);
            mMySoundAdapter.setHttpClick(new MySoundAdapter.HttpClick() {
                @Override
                public void getClick(View view, final int position) {
                    new UpdateSoundPresenter(new UpdateSoundView<UpdateSoundUser>() {

                        @Override
                        public void onDataSuccess(UpdateSoundUser updateSoundUser) {
                            String message = updateSoundUser.getMessage();
                            int status = mList.get(position).getStatus();
                            if (status == 1){
                                ToastUtil.Toast("状态已修改完");
                                return;
                            }else {
                                if (message.equals("状态改变成功")) {
                                    if (mCount>0){
                                        mCount--;
                                        soundtext.setText("系统消息  (" + mCount + "条未读" + ")");
                                        return;
                                    }else{
                                        soundtext.setText("系统消息  (" + 0 + "条未读" + ")");
                                        return;
                                    }
                                }
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
                    }).getSound(mList.get(position).getId());
                }
            });
            mMySoundAdapter.notifyDataSetChanged();
            mSoundrecycle.setAdapter(mMySoundAdapter);
        }else{
            showEmpty();
        }


        mSounSwipeRefreshLayout.setRefreshing(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mScrollWindow.dismissPop();
            }
        },1000);

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


    @OnClick(R.id.soundimage)
    public void onViewClicked() {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BaseEvent.unregister(this);
    }
}
