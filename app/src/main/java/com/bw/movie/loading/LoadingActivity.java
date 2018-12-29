package com.bw.movie.loading;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.login.LoginActivity;
import com.bw.movie.start.StartActivity;
import com.bw.movie.util.NewThread;
import com.bw.movie.util.ToastUtil;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 主页倒计时
 * */
public class LoadingActivity extends AppCompatActivity {

    @BindView(R.id.loadtext)
    TextView loadtext;
    @BindView(R.id.loadimage)
    ImageView loadimage;
    private int i = 2;
    private ToastUtil mToastUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        ButterKnife.bind(this);

        final NewThread newThread = new NewThread();
        boolean netWork = newThread.isNetWork(this);
        mToastUtil = new ToastUtil();
        if (netWork){
            mToastUtil.Toast("连接成功");
            loadimage.setImageResource(R.mipmap.load);
            mTimer.schedule(mTask,1000,1000);
        }else {
            mToastUtil.Toast("连接失败");
            loadimage.setImageResource(R.mipmap.fail);
            loadimage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean wifi = newThread.isWifi(LoadingActivity.this);
                    if (wifi){
                        mToastUtil.Toast("连接成功");
                    }else{
                        startActivity(new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS));
                        finish();
                    }

                }
            });
            //startActivity(new Intent(LoadingActivity.this,FailerActivity.class));
        }
        loadtext.setVisibility(View.GONE);

    }

    //创建计时器
    Timer mTimer = new Timer();
    //创建计时任务(子线程)
    TimerTask mTask = new TimerTask() {
        @Override
        public void run() {
            Message message = new Message();
            message.what = 1;
            mHandler.sendMessage(message);
        }
    };

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                //发送消息
                case 1:
                    //接受消息,每次减一
                    i--;
                    loadtext.setText(""+i);
                    if (i == 0){
                        getData();
                        //startActivity(new Intent(LoadingActivity.this,LoginActivity.class));
                        //finish();
                    }
                    break;
            }
        }
    };

    public void getData(){
        SharedPreferences sp=getSharedPreferences("login", Context.MODE_PRIVATE);
        int count=sp.getInt("count", 0);
        if (count == 0){
            SharedPreferences.Editor et=sp.edit();
            et.putInt("count",1);
            et.commit();
            Intent intent=new Intent(LoadingActivity.this,StartActivity.class);
            startActivity(intent);
            finish();
        }else {
            //第二次或更多进入
            Intent intent=new Intent(LoadingActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
