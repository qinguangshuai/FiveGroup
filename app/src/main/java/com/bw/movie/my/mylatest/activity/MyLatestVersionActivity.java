package com.bw.movie.my.mylatest.activity;

import android.os.Bundle;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.my.mylatest.bean.MyLatestUser;
import com.bw.movie.my.mylatest.prosenter.MyLatestPresenter;
import com.bw.movie.my.mylatest.view.MyLatestView;

public class MyLatestVersionActivity extends BaseActivity implements MyLatestView<MyLatestUser> {

    private MyLatestPresenter mMyLatestPresenter;
    public static String mDownloadUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMyLatestPresenter = new MyLatestPresenter(this);
        mMyLatestPresenter.getVersion();
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
        return R.layout.activity_latest_version;
    }

    @Override
    public void initVariable() {

    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public void onDataSuccess(MyLatestUser myLatestUser) {
        String message = myLatestUser.getMessage();
        int flag = myLatestUser.getFlag();
        Toast.makeText(this, message + flag, Toast.LENGTH_SHORT).show();
        /*mDownloadUrl = myLatestUser.getDownloadUrl();
        LinearLayout linearLayout = new LinearLayout(MyLatestVersionActivity.this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        WebView mWebView = new WebView(MyLatestVersionActivity.this);
        mWebView.loadUrl(mDownloadUrl);
        mWebView.setWebChromeClient(new WebChromeClient());
        linearLayout.addView(mWebView, params);
        setContentView(linearLayout);*/
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