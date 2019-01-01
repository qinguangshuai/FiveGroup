package com.bw.movie.my;

import android.os.Bundle;
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
import com.bw.movie.my.mysound.UpdateSoundPresenter;
import com.bw.movie.my.mysound.UpdateSoundUser;
import com.bw.movie.my.mysound.UpdateSoundView;
import com.bw.movie.my.mysound.XiSoundPresenter;
import com.bw.movie.my.mysound.XiSoundUser;
import com.bw.movie.my.mysound.XiSoundView;

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
    RecyclerView soundrecycle;
    @BindView(R.id.soundRb1)
    RadioButton soundRb1;
    @BindView(R.id.soundRb2)
    RadioButton soundRb2;
    @BindView(R.id.soundRb3)
    RadioButton soundRb3;
    @BindView(R.id.soundRg)
    RadioGroup soundRg;
    @BindView(R.id.soundimage)
    ImageView soundimage;
    private MySoundPresenter mMySoundPresenter;
    private List<MySoundUser.ResultBean> mList;
    private int mCount;
    private int mId;


    @Override
    public void initView() {
        ButterKnife.bind(this);
        mMySoundPresenter = new MySoundPresenter(this);
        soundRb1.setText("1");
        String s1 = soundRb1.getText().toString().trim();
        Integer value = Integer.valueOf(s1);
        mMySoundPresenter.getSound(value);
    }

    @Override
    public void initListener() {

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
        soundrecycle.setLayoutManager(linearLayoutManager);
        mList = mySoundUser.getResult();
        MySoundAdapter mySoundAdapter = new MySoundAdapter(getApplicationContext(), mList);
        mySoundAdapter.setHttpClick(new MySoundAdapter.HttpClick() {
            @Override
            public void getClick(View view, int position) {
                mId = mList.get(position).getId();
                new UpdateSoundPresenter(new UpdateSoundView<UpdateSoundUser>() {

                    @Override
                    public void onDataSuccess(UpdateSoundUser updateSoundUser) {
                        String message = updateSoundUser.getMessage();
                        mCount--;
                        Toast.makeText(MySoundActivity.this,message.toString(),Toast.LENGTH_SHORT).show();
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
        soundrecycle.setAdapter(mySoundAdapter);
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

    @OnClick({R.id.soundRb1, R.id.soundRb2, R.id.soundRb3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.soundRb1:
                soundRb1.setText("1");
                String ss1 = soundRb1.getText().toString().trim();
                Integer value1 = Integer.valueOf(ss1);
                mMySoundPresenter.getSound(value1);
                break;
            case R.id.soundRb2:
                soundRb2.setText("2");
                String ss2 = soundRb2.getText().toString().trim();
                Integer value2 = Integer.valueOf(ss2);
                mMySoundPresenter.getSound(value2);
                break;
            case R.id.soundRb3:
                soundRb3.setText("3");
                String ss3 = soundRb3.getText().toString().trim();
                Integer value3 = Integer.valueOf(ss3);
                mMySoundPresenter.getSound(value3);
                break;
        }
    }

    @OnClick(R.id.soundimage)
    public void onViewClicked() {
        finish();
    }
}
