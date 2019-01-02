package com.bw.movie.my.myinfo.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.my.message.activity.MyMessage;
import com.bw.movie.my.myinfo.bean.UpDateUserInfoEntity;
import com.bw.movie.my.myinfo.prosenter.UpDateUserInfoPresenter;
import com.bw.movie.my.myinfo.updatepwd.activity.UpdatePwdActivity;
import com.bw.movie.my.myinfo.view.UpDateUserInfoView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
/*
*  修改信息的activity
* */
public class UpdataInfoActivity extends BaseActivity<UpDateUserInfoPresenter> implements UpDateUserInfoView<UpDateUserInfoEntity> {

    @BindView(R.id.mtouxiang)
    SimpleDraweeView mMtouxiang;
    @BindView(R.id.update_touxiang)
    Button mUpdateTouxiang;
    @BindView(R.id.mnicheng)
    TextView mMnicheng;
    @BindView(R.id.mxingbie)
    TextView mMxingbie;
    @BindView(R.id.mshoujihao)
    TextView mMshoujihao;
    @BindView(R.id.myouxiang)
    TextView mMyouxiang;
    Button mUpdateMyinfo;
    @BindView(R.id.mriqi)
    TextView mMriqi;
    @BindView(R.id.chongzhimima)
    ImageView mChongzhimima;
    private UpDateUserInfoPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);


        Intent intent = getIntent();
        int sex1 = intent.getIntExtra("sex1", -1);
        mMxingbie.setText(sex1 + "");
        String email = intent.getStringExtra("email");
        mMyouxiang.setText(email);
        String headPic = intent.getStringExtra("headPic");
        Uri uri = Uri.parse(headPic);
        mMtouxiang.setImageURI(uri);
        String nickName = intent.getStringExtra("nickName");
        mMnicheng.setText(nickName);
        String phone1 = intent.getStringExtra("phone1");
        mMshoujihao.setText(phone1);

        String s = intent.getStringExtra("s");
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTimeInMillis(Long.parseLong(s));
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        mMriqi.setText(df.format(gc.getTime()));


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
        return R.layout.activity_updata_info;
    }

    @Override
    public void initVariable() {

    }

    @Override
    public UpDateUserInfoPresenter initPresenter() {
        return null;
    }


    @Override
    public void onDataSuccess(UpDateUserInfoEntity upDateUserInfoEntity) {
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

    @OnClick({R.id.mtouxiang, R.id.update_touxiang, R.id.mnicheng, R.id.mxingbie, R.id.mriqi, R.id.mshoujihao, R.id.myouxiang, R.id.update_myinfo, R.id.chongzhimima})
    public void onClick(View v) {
        String nickname = mMnicheng.getText().toString().trim();

        String sex = mMxingbie.getText().toString();
        Integer sex1 = Integer.valueOf(sex);
        String email = mMyouxiang.getText().toString();

        switch (v.getId()) {
            case R.id.mtouxiang:
                break;
            case R.id.update_touxiang:
                break;
            case R.id.mnicheng:
                break;
            case R.id.mxingbie:
                break;
            case R.id.mriqi:
                break;
            case R.id.mshoujihao:
                break;
            case R.id.myouxiang:
                break;

            case R.id.update_myinfo:
                mMxingbie.setText(sex1 + "");
                presenter = new UpDateUserInfoPresenter(this);
                presenter.getUserInfo(nickname, sex1, email);
                startActivity(new Intent(this, MyMessage.class));

                finish();
                break;

            case R.id.chongzhimima:
                startActivity(new Intent(this, UpdatePwdActivity.class));
                finish();
                break;
        }
    }
}
