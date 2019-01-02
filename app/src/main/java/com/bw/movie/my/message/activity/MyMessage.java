package com.bw.movie.my.message.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.my.message.bean.MyMessageEntity;
import com.bw.movie.my.message.presenter.MyMessagePresenter;
import com.bw.movie.my.message.view.MyMessageView;
import com.bw.movie.my.myinfo.activity.UpdataInfoActivity;
import com.facebook.drawee.view.SimpleDraweeView;

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
    private MyMessagePresenter presenter;
    private MyMessageEntity.ResultBean result;
    private int sex1;
    private String email;
    private String headPic;
    private String nickName;
    private String phone1;
    private long browseTime;
    private String s;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new MyMessagePresenter(this);
        presenter.getMessage();



        ButterKnife.bind(this);
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

        result = myMessageEntity.getResult();
        email = result.getEmail();
        headPic = result.getHeadPic();
        int id = result.getId();
        nickName = result.getNickName();
        phone1 = result.getPhone();
        sex1 = result.getSex();



        if (sex1 == 1) {
            mTxtMyinfoSex.setText("男");
        } else if (sex1 == 2) {
            mTxtMyinfoSex.setText("女");
        } else {
            mTxtMyinfoSex.setText("获取性别失败");
        }

        mTxtMyinfoMail.setText(email);
        mTxtMyinfoPhone.setText(phone1);
        mTxtMyinfoNikename.setText(nickName);


        browseTime = result.getBirthday();
        GregorianCalendar gc = new GregorianCalendar();
        s = String.valueOf(browseTime);
        gc.setTimeInMillis(Long.parseLong(s));
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        mTxtMyinfoBirthday.setText(df.format(gc.getTime()));


        Uri uri = Uri.parse(headPic);
        mMyHeadimage.setImageURI(uri);


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
                intent.putExtra("sex1",sex1);
                intent.putExtra("email",email);
                intent.putExtra("headPic",headPic);
                intent.putExtra("nickName",nickName);
                intent.putExtra("phone1",phone1);
                intent.putExtra("s",s);
                startActivity(intent);
                finish();
                break;
        }
    }
}
