package com.bw.movie.custom;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bw.movie.R;

/*
 *作者:ash
 *TODO:  自定义搜索框框
 *
 */public class SearchView extends LinearLayout {

     private boolean isShow = false;

    public SearchView(Context context) {
        this(context,null);
    }

    public SearchView(Context context,  AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public SearchView(final Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = View.inflate(context, R.layout.customsearchview, this);
        final LinearLayout linearLayout =  view.findViewById(R.id.cumtomlinear);
        TextView cumtomsearch = view.findViewById(R.id.cumtomsearch);
        TextView cumtomresult = view.findViewById(R.id.cumtomresult);


        cumtomsearch.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Resources resources = context.getResources();
                DisplayMetrics displayMetrics = resources.getDisplayMetrics();
                int widthPixels = displayMetrics.widthPixels;
                if(isShow == false){
                    ObjectAnimator animator = ObjectAnimator.ofFloat(linearLayout, "translationX", 0f, -(widthPixels*3/5), -(widthPixels*2/5));
                    animator.setDuration(1000);
                    animator.start();
                    isShow = true;
                }else if(isShow == true){
                    ObjectAnimator animator = ObjectAnimator.ofFloat(linearLayout, "translationX", -(widthPixels*2/5), 0, 0);
                    animator.setDuration(1000);
                    animator.start();
                    isShow = false;
                }

            }
        });


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
