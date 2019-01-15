package com.bw.movie.my.message.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.Constant;
import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.cinema.fragment.ChuanUser;
import com.bw.movie.error.AppManager;
import com.bw.movie.login.LoginActivity;
import com.bw.movie.my.message.bean.MyMessageEntity;
import com.bw.movie.my.message.bean.Portrait;
import com.bw.movie.my.message.bean.ResultBean;
import com.bw.movie.my.message.presenter.MyMessagePresenter;
import com.bw.movie.my.message.view.MyMessageView;
import com.bw.movie.my.myinfo.activity.UpdataInfoActivity;
import com.bw.movie.util.SpUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
  我的信息首页
* */
public class MyMessage extends BaseActivity<MyMessagePresenter> implements MyMessageView<MyMessageEntity> {

    @BindView(R.id.txt_myinfo_nikename)
    TextView mTxtMyinfoNikename;
    @BindView(R.id.txt_myinfo_sex)
    TextView mTxtMyinfoSex;
    @BindView(R.id.txt_myinfo_birthday)
    TextView mTxtMyinfoBirthday;
    @BindView(R.id.txt_myinfo_phone)
    TextView mTxtMyinfoPhone;
    @BindView(R.id.txt_myinfo_mail)
    TextView mTxtMyinfoMail;
    @BindView(R.id.myinfo_back)
    ImageView mMyinfoBack;
    @BindView(R.id.my_headimage)
    SimpleDraweeView mMyHeadimage;
    @BindView(R.id.my_update)
    Button mMyUpdate;
    @BindView(R.id.my_myback)
    Button myMyback;
    private MyMessagePresenter mPresenter;
    private int mSex1;
    private String mEmail;
    private String mHeadPic;
    private String mNickName;
    private String mPhone1;
    private long mBrowseTime;
    private String s;
    private ResultBean mResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new MyMessagePresenter(this);
        mPresenter.getMessage();
        ButterKnife.bind(this);
    }

    @Subscribe
    public void getChuan(ChuanUser chuanUser) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        AppManager.getAppManager().finishActivity(this);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public int initLayoutId() {
        return R.layout.activity_my_info;
    }

    @Override
    public void initVariable() {

    }

    @Override
    public MyMessagePresenter initPresenter() {
        return null;
    }

    @Override
    public void onDataSuccess(MyMessageEntity myMessageEntity) {

        mResult = myMessageEntity.getResult();
        mEmail = this.mResult.getEmail();
        mHeadPic = this.mResult.getHeadPic();
        int id = this.mResult.getId();
        mNickName = this.mResult.getNickName();
        mPhone1 = this.mResult.getPhone();
        mSex1 = this.mResult.getSex();

        if (mSex1 == 1) {
            mTxtMyinfoSex.setText("男");
        } else if (mSex1 == 2) {
            mTxtMyinfoSex.setText("女");
        } else {
            mTxtMyinfoSex.setText("获取性别失败");
        }

        mTxtMyinfoMail.setText(mEmail);
        mTxtMyinfoPhone.setText(mPhone1);
        mTxtMyinfoNikename.setText(mNickName);

        mBrowseTime = this.mResult.getBirthday();
        GregorianCalendar gc = new GregorianCalendar();
        s = String.valueOf(mBrowseTime);
        gc.setTimeInMillis(Long.parseLong(s));
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        mTxtMyinfoBirthday.setText(df.format(gc.getTime()));
        Uri uri = Uri.parse(mHeadPic);
        mMyHeadimage.setImageURI(uri);
        EventBus.getDefault().post(new Portrait(mHeadPic));
    }

    @Override
    public void onDataFailer(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onShowLoading() {

    }

    @Override
    public void onHideLoading() {

    }

    @OnClick({R.id.txt_myinfo_nikename, R.id.txt_myinfo_sex, R.id.txt_myinfo_birthday, R.id.txt_myinfo_phone, R.id.txt_myinfo_mail, R.id.myinfo_back, R.id.my_headimage, R.id.my_update})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.txt_myinfo_nikename:
                break;
            case R.id.txt_myinfo_sex:
                break;
            case R.id.txt_myinfo_birthday:
                break;
            case R.id.txt_myinfo_phone:
                break;
            case R.id.txt_myinfo_mail:
                break;
            case R.id.myinfo_back:

                finish();
                break;
            case R.id.my_headimage:
                break;
            case R.id.my_update:
                Intent intent = new Intent(this, UpdataInfoActivity.class);
                intent.putExtra(Constant.SEX, mSex1);
                intent.putExtra("mEmail", mEmail);
                intent.putExtra(Constant.HEADPIC, mHeadPic);
                intent.putExtra(Constant.NICKNAME, mNickName);
                intent.putExtra(Constant.PHONE, mPhone1);
                intent.putExtra("s", s);
                startActivity(intent);
                finish();
                break;
        }
    }

    @OnClick(R.id.my_myback)
    public void onViewClicked() {
        SpUtil.remove("phone");
        SpUtil.remove("pwd");
        SpUtil.remove("loginbox");
        SpUtil.remove("headPic");
        SpUtil.remove("nickName");
        SpUtil.remove("sessionId");
        SpUtil.remove("userId");

        startActivity(new Intent(this, LoginActivity.class));
        AppManager.getAppManager().finishAllActivity();
    }
}
