package com.bw.movie.wxapi;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.ShowActivity;
import com.bw.movie.util.NotifyUtil;
import com.bw.movie.util.WeiXinUtil;
import com.bw.movie.wxapi.bean.WXUser;
import com.bw.movie.wxapi.presenter.WXPresenter;
import com.bw.movie.wxapi.view.WXView;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.io.ByteArrayOutputStream;

import static com.tencent.mm.opensdk.modelmsg.SendMessageToWX.Req.WXSceneSession;
import static com.tencent.mm.opensdk.modelmsg.SendMessageToWX.Req.WXSceneTimeline;

/**
 * 微信界面
 * date:2018/12/27
 * author:秦广帅(Lenovo)
 */

public class WXEntryActivity extends AppCompatActivity implements IWXAPIEventHandler, WXView<WXUser> {


    //    private LoginPresenter mLoginPresenter;
    private static final String TAG = "WXEntryActivity";
    private static final int REQUESTMSG = 0;
    private String message;
    private WXPresenter mWxPresenter;
    private WXUser mWxUser1;
    private String mCode;
    private NotifyUtil currentNotify;
    private boolean isoncl = true;
    private int requestCode = (int) SystemClock.uptimeMillis();
    private String APP_ID = "wxb3852e6a6b7d9516";
    private IWXAPI iwxapi;
    boolean flag;
    enum SHARE_TYPE {Type_WXSceneSession, Type_WXSceneTimeline}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxentry);

        WeiXinUtil.reg(WXEntryActivity.this).handleIntent(getIntent(), this);
//        mLoginPresenter = new LoginPresenter(this);
        mWxPresenter = new WXPresenter(this);
        //
        iwxapi = WXAPIFactory.createWXAPI(this, APP_ID, false);
        iwxapi.handleIntent(getIntent(), this);
        iwxapi.registerApp(APP_ID);
    }

    @Override
    public void onReq(BaseReq baseReq) {
//        LogUtil.d("==1111111" );
        Log.d(TAG, "==1111111");
    }

    // 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
    @Override
    public void onResp(final BaseResp baseResp) {
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //得到code
                        mCode = ((SendAuth.Resp) baseResp).code;
//                        LogUtil.d("==1111111" + code);
                        if (!flag) {
                            flag = true;
                            Log.d(TAG, "==1111111     " + mCode);
                            mWxPresenter.postWX(mCode);
                        }

                    }
                });

                finish();

                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                //取消
//                LogUtil.d("code =ERR_USER_CANCEL");
                Log.d(TAG, "code =ERR_USER_CANCEL");
                break;

        }


    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    Toast.makeText(WXEntryActivity.this, mWxUser1.getMessage(), Toast.LENGTH_SHORT).show();
                    ;
                    startActivity(new Intent(WXEntryActivity.this, ShowActivity.class));
                    break;
                case 2:

                    break;
            }
        }
    };

    @Override
    public void onDataSuccess(WXUser wxUser) {
        mWxUser1 = wxUser;
        handler.sendEmptyMessage(1);
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

    public void shareWXSceneSession(View view) {
        share(SHARE_TYPE.Type_WXSceneSession);
    }

    public void shareWXSceneTimeline(View view) {
        share(SHARE_TYPE.Type_WXSceneTimeline);
    }

    private void share(SHARE_TYPE type) {
        WXWebpageObject webpageObject = new WXWebpageObject();
        webpageObject.webpageUrl = "http://www.dangdang.com/?_utm_sem_id=16202047&_ddclickunion=422-kw-1-%C6%B7%C5%C6%B4%CA_%C6%B7%C5%C6%B4%CA-%BA%CB%D0%C4_%B5%B1%B5%B1%CD%F8|ad_type=0|sys_id=1";
        WXMediaMessage msg = new WXMediaMessage(webpageObject);
        msg.title = "Sunny Cinema";
        msg.description = "This is a movie about the software";
        Bitmap thumb = BitmapFactory.decodeResource(getResources(), R.mipmap.ali);
        msg.thumbData = bmpToByteArray(thumb, true);
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("Req");
        req.message = msg;
        switch (type) {
            case Type_WXSceneSession:
                req.scene = WXSceneSession;
                break;
            case Type_WXSceneTimeline:
                req.scene = WXSceneTimeline;
                break;
        }
        iwxapi.sendReq(req);
        finish();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        iwxapi.handleIntent(intent, this);
    }

    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

    public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, output);
        if (needRecycle) {
            bmp.recycle();
        }
        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
