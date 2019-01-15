package com.bw.movie.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.bw.movie.R;

/*
 *作者:ash
 *TODO:
 *
 */
public class CustomLinear extends LinearLayout {

    public CustomLinear(Context context) {
        this(context, null);
    }

    public CustomLinear(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public CustomLinear(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = View.inflate(context, R.layout.customlinear, this);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
