package com.bw.movie.util;

import android.content.Context;

import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

//微信工具类
public class WeiXinUtil {
    // APP_ID 替换为你的应用从官方网站申请到的合法appID
    private static final String APP_ID = "wxb3852e6a6b7d9516";
    // IWXAPI 是第三方app和微信通信的openApi接口
    private WeiXinUtil() {

    }

    public static IWXAPI success(Context context){
        if (context!=null) {
            //AppConst.WEIXIN.APP_ID是指你应用在微信开放平台上的AppID，记得替换。
            IWXAPI api = WXAPIFactory.createWXAPI(context, APP_ID, true);
            //注册到微信
            api.registerApp(APP_ID);
            return api;
        }else {
            return  null;
        }
    }

}
