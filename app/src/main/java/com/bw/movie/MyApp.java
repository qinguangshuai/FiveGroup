package com.bw.movie;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import com.bw.movie.error.UnCatchExceptionHandler;
import com.bw.movie.greenbean.DaoMaster;
import com.bw.movie.greenbean.DaoSession;
import com.bw.movie.util.LogUtil;
import com.bw.movie.net.OkHttpUtil;
import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.greendao.database.Database;

/*
 *作者:ash
 *TODO:
 */
public class MyApp extends Application {

    public static Context sContext;
    private IWXAPI wxApi;
    public static final boolean ENCRYPTED = true;
    private DaoSession daoSession;

    private DaoSession cache;



    @Override
    public void onCreate() {
        super.onCreate();

        sContext = this;
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
        Fresco.initialize(this, config);
        //捕获全局异常
        UnCatchExceptionHandler.getmExceptionHandler().init(this);
        LogUtil.init();
        initXG();
        initWX();

        //第一个String 是 数据库名字 //第二个String 是 数据库名-db 文件类型
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, ENCRYPTED ? "GreenBean" : "GreenBean-db");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();

        DaoMaster.DevOpenHelper helper2 = new DaoMaster.DevOpenHelper(this, "Cache.db");
        Database db2 = helper2.getWritableDb();
        cache = new DaoMaster(db2).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public DaoSession getCache() {
        return cache;
    }


    private void initXG() {
        XGPushConfig.enableDebug(this, true);
        XGPushConfig.enableOtherPush(getApplicationContext(), true);
        XGPushConfig.setHuaweiDebug(true);
        XGPushConfig.setMiPushAppId(getApplicationContext(), "d71d384497c51");
        XGPushConfig.setMiPushAppKey(getApplicationContext(), "A44FJ9N7N9EY");
        XGPushConfig.setMzPushAppId(this, "d71d384497c51");
        XGPushConfig.setMzPushAppKey(this, "A44FJ9N7N9EY");
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
