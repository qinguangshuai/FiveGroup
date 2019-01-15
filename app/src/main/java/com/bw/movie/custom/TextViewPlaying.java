package com.bw.movie.custom;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bw.movie.R;

/*
 *作者:ash
 *TODO:正在热映
 *  具备动画功能
 */
public class TextViewPlaying extends LinearLayout {

    private TextView mTv;

    public TextViewPlaying(Context context) {
        this(context,null);
    }

    public TextViewPlaying(Context context,  AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public TextViewPlaying(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = View.inflate(context, R.layout.customfilter, this);
        TextView v =  view.findViewById(R.id.v_customv);
        mTv = view.findViewById(R.id.tv_customtv2);
        ObjectAnimator animator = ObjectAnimator.ofFloat(v, "translationX", 0f, 800f, 800f);
        animator.setDuration(8000);
        animator.setRepeatCount(-1);
        animator.start();

    }
}
