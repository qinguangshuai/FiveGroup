package com.bw.movie.custom;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bw.movie.R;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

/*
 *作者:ash
 *TODO:热门电影
 *  具备动画功能
 */
public class TextViewOfHot extends LinearLayout {

    private TextView tv;
    private SimpleDraweeView img;

    public TextViewOfHot(Context context) {
        this(context, null);
    }

    public TextViewOfHot(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public TextViewOfHot(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = View.inflate(context, R.layout.customtextview, this);
        tv = view.findViewById(R.id.tv_customtv);
        img = view.findViewById(R.id.img_customimg);


        //路径
        Uri uri1 = Uri.parse("res://drawable/" + R.drawable.hot);
        //不多解释
        AbstractDraweeController build = Fresco.newDraweeControllerBuilder()
                .setUri(uri1)
                .setAutoPlayAnimations(true)
                .build();
        img.setController(build);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }


}
