package com.bw.movie.a;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.bw.movie.R;

/**
 * date:2019/1/9    20:43
 * author:Therefore(Lenovo)
 * fileName:empityview
 */
public class empityview extends LinearLayout {

    private View view;

    public empityview(Context context) {
        this(context,null);
    }

    public empityview(Context context, AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public empityview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        view = LayoutInflater.from(context).inflate(R.layout.layout_error, this);
    }
}
