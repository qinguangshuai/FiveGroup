package com.bw.movie.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


import com.bw.movie.MyApp;
import com.bw.movie.util.ToastUtil;
import com.bw.movie.util.WeiXinUtil;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * date:2019/1/2
 * author:张文龙 liu
 * function:微信支付对应的Activity
 */
public class WXPayEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {

    private IWXAPI mIwxapi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIwxapi = WXAPIFactory.createWXAPI(MyApp.context, WeiXinUtil.APP_ID);
        mIwxapi.handleIntent(getIntent(), this);

    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            int errCord = baseResp.errCode;
            if (errCord == 0) {
                new ToastUtil().Toast("支付成功！");
            } else if (errCord == -1) {
                new ToastUtil().Toast("支付失败！");
            } else {
                new ToastUtil().Toast("用户取消了！");
            }
            //这里接收到了返回的状态码可以进行相应的操作，如果不想在这个页面操作可以把状态码存在本地然后finish掉这个页面，这样就回到了你调起支付的那个页面
            //获取到你刚刚存到本地的状态码进行相应的操作就可以了
            finish();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        mIwxapi.handleIntent(intent, this);
    }
}