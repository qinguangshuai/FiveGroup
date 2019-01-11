package com.bw.movie.my;


import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.login.LoginActivity;
import com.bw.movie.my.attention.activity.MyattentionActivity;
import com.bw.movie.my.message.activity.MyMessage;
import com.bw.movie.my.message.bean.Portrait;
import com.bw.movie.my.mylatest.activity.MyLatestVersionActivity;
import com.bw.movie.my.myoption.activity.MyOpitionActivity;
import com.bw.movie.my.ticket.activity.Ticket_nformationActivity;
import com.bw.movie.util.SpUtil;
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
    ImageView mMyInfo;
    @BindView(R.id.my_love)
    ImageView mMyLove;
    @BindView(R.id.my_message)
    ImageView mMyMessage;
    @BindView(R.id.my_opinion)
    ImageView mMyOpinion;
    @BindView(R.id.my_new)
    ImageView mMyNew;
    private View view;
    private Unbinder unbinder;

    @Override
    public void initView() {
        unbinder = ButterKnife.bind(this, rootView);
    }

    @Override
    public void initListener() {
        String headPic = SpUtil.getString("headPic", "");
        Uri uri=Uri.parse(headPic);
        mMyTouxiang.setImageURI(uri);
        String name = SpUtil.getString("nickName", "");
        mMyName.setText(name);
        EventBus.getDefault().register(this);
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
    public void setXiang(Portrait portrait){
        Uri uri=Uri.parse(portrait.getImage());
        mMyTouxiang.setImageURI(uri);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.my_sound, R.id.my_touxiang, R.id.my_name, R.id.my_info, R.id.my_love, R.id.my_message, R.id.my_opinion, R.id.my_new})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.my_sound:
                startActivity(new Intent(getContext(),MySoundActivity.class));
                break;
            case R.id.my_touxiang:
                Intent intent=new Intent(getContext(),ScaleImageActivity.class);
                //创建一个Rect,报错当前imageview的位置信息
                Rect rect=new Rect();
                //将位置信息赋给rect
                mMyTouxiang.getGlobalVisibleRect(rect);
                intent.setSourceBounds(rect);
                //跳转
                startActivity(intent);
                //屏蔽activity跳转的默认专场效果
                getActivity().overridePendingTransition(0,0);
                break;
            case R.id.my_name:
                break;
            case R.id.my_info:
                startActivity(new Intent(getContext(),MyMessage.class));
                break;
            case R.id.my_love:
                startActivity(new Intent(getContext(),MyattentionActivity.class));
                break;
            case R.id.my_message:
                startActivity(new Intent(getContext(),Ticket_nformationActivity.class));
                break;
            case R.id.my_opinion:
                startActivity(new Intent(getContext(),MyOpitionActivity.class));
                break;
            case R.id.my_new:
                startActivity(new Intent(getContext(),MyLatestVersionActivity.class));
                break;
        }
    }
}
