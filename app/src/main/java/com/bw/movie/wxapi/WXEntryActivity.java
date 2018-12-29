package com.bw.movie.wxapi;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.bw.movie.MainActivity;
import com.bw.movie.R;
import com.bw.movie.ShowActivity;
import com.bw.movie.util.WeiXinUtil;
import com.bw.movie.wxapi.bean.WXUser;
import com.bw.movie.wxapi.presenter.WXPresenter;
import com.bw.movie.wxapi.view.WXView;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;


/**
 * 微信界面
 * date:2018/12/27
 * author:秦广帅(Lenovo)
 * */

public class WXEntryActivity extends AppCompatActivity implements IWXAPIEventHandler,WXView<WXUser> {


    //    private LoginPresenter mLoginPresenter;
    private static final String TAG = "WXEntryActivity";

    public static String code;
    private static final int REQUESTMSG = 0;
    private String message;
    private WXPresenter mWxPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxentry);

        WeiXinUtil.success(WXEntryActivity.this).handleIntent(getIntent(), this);
//        mLoginPresenter = new LoginPresenter(this);
        mWxPresenter = new WXPresenter(this);
        mWxPresenter.postWX(code);
    }

    @Override
    public void onReq(BaseReq baseReq) {
//        LogUtil.d("==1111111" );
        Log.d(TAG,"==1111111" );
    }

    // 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
    @Override
    public void onResp(final BaseResp baseResp) {
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //得到code
                        code = ((SendAuth.Resp) baseResp).code;
//                        LogUtil.d("==1111111" + code);

                        Log.d(TAG,"==1111111     "+ code);

                    }
                });

                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                //取消
//                LogUtil.d("code =ERR_USER_CANCEL");
                Log.d(TAG,"code =ERR_USER_CANCEL");
                break;

        }

    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    startActivity(new Intent(WXEntryActivity.this, MainActivity.class));
                    break;
                case 2:

                    break;
            }
        }
    };

    @Override
    public void onDataSuccess(WXUser wxUser) {
        String message = wxUser.getMessage();
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
        startActivity(new Intent(WXEntryActivity.this,ShowActivity.class));
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
