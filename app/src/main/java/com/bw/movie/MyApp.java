package com.bw.movie;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import com.bw.movie.error.UnCatchExceptionHandler;
import com.bw.movie.util.OkHttpUtil;
import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

/*
 *作者:ash
 *TODO:
 *
 */public class MyApp extends Application {

     public static Context context;

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
    }
}
