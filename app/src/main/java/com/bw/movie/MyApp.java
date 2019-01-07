package com.bw.movie;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.bw.movie.error.UnCatchExceptionHandler;
import com.bw.movie.util.OkHttpUtil;
import com.bw.movie.util.SpUtil;
import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/*
 *作者:ash
 *TODO:
 *
 */public class MyApp extends Application {

     public static Context context;
    private IWXAPI wxApi;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this ;
        OkHttpUtil.init();
        Fresco.initialize(this);

        //设置缓存路径,磁盘缓存
        DiskCacheConfig build = DiskCacheConfig.newBuilder(this)
                .setBaseDirectoryName("image")
                .setBaseDirectoryPath(Environment.getExternalStorageDirectory())
                .build();
        //生成配置文件
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
                .setMainDiskCacheConfig(build)
                .build();
        Fresco.initialize(this,config);
        //捕获全局异常
        UnCatchExceptionHandler.getmExceptionHandler().init(this);

        XGPushConfig.enableDebug(this,true);
        XGPushConfig.enableOtherPush(getApplicationContext(), true);
        XGPushConfig.setHuaweiDebug(true);
        XGPushConfig.setMiPushAppId(getApplicationContext(), "d71d384497c51");
        XGPushConfig.setMiPushAppKey(getApplicationContext(), "A44FJ9N7N9EY");
        XGPushConfig.setMzPushAppId(this, "d71d384497c51");
        XGPushConfig.setMzPushAppKey(this, "A44FJ9N7N9EY");
        initWX();
    }

    /**
     * 初始化微信支付
     */
    private void initWX() {
        if (wxApi == null) {
            wxApi = WXAPIFactory.createWXAPI(this, "wxb3852e6a6b7d9516");
            wxApi.registerApp("wxb3852e6a6b7d9516");
        }
    }
}
