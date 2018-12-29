package com.bw.movie.my.myoption.activity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.my.myoption.bean.MyOptionEntity;
import com.bw.movie.my.myoption.prosenter.MyOptionPresenter;
import com.bw.movie.my.myoption.view.MyOpitionView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
/*
*   我的意见activity
* */
public class MyOpitionActivity extends BaseActivity<MyOptionPresenter> implements MyOpitionView<MyOptionEntity> {

    @BindView(R.id.my_option_tj)
    Button mMyOptionTj;
    @BindView(R.id.my_option_info)
    EditText mMyOptionInfo;
    @BindView(R.id.viewsub_loading)
    ViewStub mViewsubLoading;
    @BindView(R.id.yijianlinear)
    LinearLayout yijianlinear;
    @BindView(R.id.my_back)
    ImageView mMyBack;
    private MyOptionPresenter presenter;
    private boolean isSuccess = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        return R.layout.activity_my_opition;
    }

    @Override
    public void initVariable() {

    }

    @Override
    public MyOptionPresenter initPresenter() {
        return null;
    }

    @OnClick({R.id.my_option_tj, R.id.my_option_info, R.id.viewsub_loading, R.id.my_back})
    public void onClick(View v) {
        String info = mMyOptionInfo.getText().toString().trim();
        switch (v.getId()) {
            default:
                break;
            case R.id.my_option_tj:
                presenter = new MyOptionPresenter(this);
                presenter.getOption(info);
                isSuccess = true;
                break;
            case R.id.my_option_info:
                break;
            case R.id.viewsub_loading:
                break;
            case R.id.my_back:
                finish();
                break;
        }
    }

    @Override
    public void onDataSuccess(MyOptionEntity myOptionEntity) {
        mViewsubLoading.setVisibility(View.INVISIBLE);
        String image = String.valueOf(R.drawable.my_option_success);
        Uri uri = Uri.parse(image);
        yijianlinear.setVisibility(View.GONE);
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
}
