package com.bw.movie.my.mylatest.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.my.mylatest.bean.MyLatestUser;
import com.bw.movie.my.mylatest.prosenter.MyLatestPresenter;
import com.bw.movie.my.mylatest.view.MyLatestView;

import java.net.MalformedURLException;
import java.net.URL;

import butterknife.BindView;

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
        String downloadUrl = myLatestUser.getDownloadUrl();
        try {
            URL url1 = new URL(downloadUrl);

        } catch (Exception e) {
            e.printStackTrace();
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

    public static final String getVersion(Activity activity){
        try {
            PackageManager manager = activity.getPackageManager();
            PackageInfo info = manager.getPackageInfo(activity.getPackageName(), 0);
            String version = info.versionCode + "." + info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}