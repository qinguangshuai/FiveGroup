package com.bw.movie.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.widget.Toast;

import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import java.util.List;

public class PermissionUtils {
    public static void permission(final Context context,final PremissionListener listener){
        AndPermission.with(context)
                .permission(Permission.ACCESS_COARSE_LOCATION,
                        Permission.READ_EXTERNAL_STORAGE,
                        Permission.WRITE_EXTERNAL_STORAGE)
                .onGranted(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        listener.success();
                    }
                })
                .onDenied(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        Uri packageURL=Uri.parse("package:"+context.getPackageName());
                        Intent intent=new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS,packageURL);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        context.startActivity(intent);
                        Toast.makeText(context,"没有权限不能扫描呦",Toast.LENGTH_LONG).show();

                    }
                }).start();
    }

    //创建接口
    public interface PremissionListener{
        void success();
    }
}
