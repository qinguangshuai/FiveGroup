package com.bw.movie.base;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bw.movie.R;
import com.bw.movie.util.NetWorkChangeEvent;

import org.greenrobot.eventbus.Subscribe;

/**
 * date:2019/1/6
 * author:秦广帅
 * function
 */
public class ErrorView extends LinearLayout {

    private View view;
    private ImageView image;

    public ErrorView(Context context) {
        this(context, null);
    }

    public ErrorView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public ErrorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        BaseEvent.register(this);
        initView(context);
    }

    @Subscribe
    public void isNetWork(NetWorkChangeEvent event) {
        boolean aTrue = event.isConnected;
        //有网络
        if (aTrue) {
            view.setVisibility(View.GONE);
        } else {
            view.setVisibility(View.VISIBLE);
        }
    }

    private void initView(final Context context){
        view = LayoutInflater.from(context).inflate(R.layout.layout_error,this);
        image = view.findViewById(R.id.errImage);
        image.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                context.startActivity(intent);
            }
        });
    }

    public void unRegister(){
        BaseEvent.unregister(this);
    }
}
