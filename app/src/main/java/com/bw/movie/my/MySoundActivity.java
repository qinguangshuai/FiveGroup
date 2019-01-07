package com.bw.movie.my;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
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
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
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
    XRecyclerView soundrecycle;
    @BindView(R.id.soundimage)
    ImageView soundimage;
    private MySoundPresenter mMySoundPresenter;
//    private List<MySoundUser.ResultBean> mList;
    private int mCount;
    private int mId;
    private int page = 1;
    private MySoundAdapter mMySoundAdapter;
    private List<ResultBean> mList;


    @Override
    public void initView() {
        ButterKnife.bind(this);
        mMySoundPresenter = new MySoundPresenter(this);
    }

    @Override
    public void initListener() {
        mMySoundPresenter.getSound(page);
        soundrecycle.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mMySoundPresenter.getSound(page);
                        soundrecycle.refreshComplete();
                    }
                },2000);
            }

            @Override
            public void onLoadMore() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mMySoundPresenter.getSound(page++);
                        soundrecycle.loadMoreComplete();
                    }
                },2000);
            }
        });
    }

    @Override
    public void initData() {
        new XiSoundPresenter(new XiSoundView<XiSoundUser>() {
            @Override
            public void onDataSuccess(XiSoundUser xiSoundUser) {
                mCount = xiSoundUser.getCount();
                //Toast.makeText(MySoundActivity.this,xiSoundUser.getCount()+"",Toast.LENGTH_SHORT).show();
                soundtext.setText("系统消息  ("+mCount+"条未读"+")");
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

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public void onDataSuccess(MySoundUser mySoundUser) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        soundrecycle.setLayoutManager(linearLayoutManager);
        mList = mySoundUser.getResult();
        mMySoundAdapter = new MySoundAdapter(getApplicationContext(), mList);
        mMySoundAdapter.setHttpClick(new MySoundAdapter.HttpClick() {
            @Override
            public void getClick(View view, int position) {
                mId = mList.get(position).getId();
                new UpdateSoundPresenter(new UpdateSoundView<UpdateSoundUser>() {

                    @Override
                    public void onDataSuccess(UpdateSoundUser updateSoundUser) {
                        String message = updateSoundUser.getMessage();

                        if (message.equals("状态改变成功")){
                            mCount--;
                            soundtext.setText("系统消息  ("+mCount+"条未读"+")");
                        }
                        Toast.makeText(MySoundActivity.this,message,Toast.LENGTH_SHORT).show();
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
                }).getSound(mId);
            }
        });
        soundrecycle.setAdapter(mMySoundAdapter);
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.soundimage)
    public void onViewClicked() {
        finish();
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
}
