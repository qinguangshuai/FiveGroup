package com.bw.movie.my;


import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.Constant;
import com.bw.movie.MyApp;
import com.bw.movie.R;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.error.AppManager;
import com.bw.movie.login.LoginActivity;
import com.bw.movie.my.attention.activity.MyattentionActivity;
import com.bw.movie.my.message.activity.MyMessage;
import com.bw.movie.my.message.bean.Portrait;
import com.bw.movie.my.mylatest.activity.MyLatestVersionActivity;
import com.bw.movie.my.myoption.activity.MyOpitionActivity;
import com.bw.movie.my.ticket.activity.Ticket_nformationActivity;
import com.bw.movie.util.LogUtil;
import com.bw.movie.util.NewThread;
import com.bw.movie.util.SpUtil;
import com.bw.movie.util.ToastUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 我的页面
 */
public class MyFragment extends BaseFragment {

    @BindView(R.id.my_sound)
    ImageView mMySound;
    @BindView(R.id.my_touxiang)
    SimpleDraweeView mMyTouxiang;
    @BindView(R.id.my_name)
    TextView mMyName;
    @BindView(R.id.my_info)
    LinearLayout mMyInfo;
    @BindView(R.id.my_love)
    LinearLayout mMyLove;
    @BindView(R.id.my_message)
    LinearLayout mMyMessage;
    @BindView(R.id.my_opinion)
    LinearLayout mMyOpinion;
    @BindView(R.id.my_new)
    LinearLayout mMyNew;
    @BindView(R.id.my_yao)
    LinearLayout mMyYao;
    private Unbinder mUnbinder;

    @Override
    public void initView() {
        mUnbinder = ButterKnife.bind(this, rootView);
    }

    @Override
    public void initListener() {
        String headPic = SpUtil.getString("headPic", "");
        Uri uri = Uri.parse(headPic);
        mMyTouxiang.setImageURI(uri);
        String name = SpUtil.getString("nickName", "");
        mMyName.setText(name);
        EventBus.getDefault().register(this);
        mMyYao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivity(new Intent(getActivity(),ShakeActivity.class));
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public int initLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public void initVarisble() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void setXiang(Portrait portrait) {
        Uri uri = Uri.parse(portrait.getImage());
        mMyTouxiang.setImageURI(uri);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @OnClick({R.id.my_sound, R.id.my_touxiang, R.id.my_name, R.id.my_info, R.id.my_love, R.id.my_message, R.id.my_opinion, R.id.my_new})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.my_sound:
                startActivity(new Intent(getContext(), MySoundActivity.class));
                break;
            case R.id.my_touxiang:

                String sessionId = SpUtil.getString("sessionId", "");
                LogUtil.e(sessionId);
                boolean netWork = NewThread.getmNewThread().isNetWork(MyApp.sContext);
                if (netWork) {
                    if (sessionId.equals("") || sessionId == null){
                        ToastUtil.Toast("系统检测到您未登陆,请重新登陆");
                        startActivity(new Intent(getActivity(),LoginActivity.class));
                        AppManager.getAppManager().finishAllActivity();
                    }else {
                        Intent intent = new Intent(getContext(), ScaleImageActivity.class);
                        //创建一个Rect,报错当前imageview的位置信息
                        Rect rect = new Rect();
                        //将位置信息赋给rect
                        mMyTouxiang.getGlobalVisibleRect(rect);
                        intent.setSourceBounds(rect);
                        //跳转
                        startActivity(intent);
                        //屏蔽activity跳转的默认专场效果
                        getActivity().overridePendingTransition(0, 0);
                    }
                } else {
                    showEmpty();
                }
                break;
            case R.id.my_name:
                break;
            case R.id.my_info:
                startActivity(new Intent(getContext(), MyMessage.class));
                break;
            case R.id.my_love:
                startActivity(new Intent(getContext(), MyattentionActivity.class));
                break;
            case R.id.my_message:
                startActivity(new Intent(getContext(), Ticket_nformationActivity.class));
                break;
            case R.id.my_opinion:
                startActivity(new Intent(getContext(), MyOpitionActivity.class));
                break;
            case R.id.my_new:
                startActivity(new Intent(getContext(), MyLatestVersionActivity.class));
                break;
        }
    }
}
