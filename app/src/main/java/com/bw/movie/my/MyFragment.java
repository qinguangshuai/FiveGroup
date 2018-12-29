package com.bw.movie.my;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.my.attention.activity.MyattentionActivity;
import com.bw.movie.my.message.activity.MyMessage;
import com.bw.movie.my.mylatest.activity.MyLatestVersionActivity;
import com.bw.movie.my.myoption.activity.MyOpitionActivity;
import com.bw.movie.my.ticket.activity.Ticket_nformationActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 我的页面
 */
public class MyFragment extends Fragment {


    @BindView(R.id.my_sound)
    ImageView mMySound;
    @BindView(R.id.my_touxiang)
    ImageView mMyTouxiang;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        unbinder = ButterKnife.bind(this, view);


        return view;
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
                startActivity(new Intent(getContext(),MyHeadportraitActivity.class));
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
