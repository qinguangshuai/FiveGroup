package com.bw.movie.custom;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import com.bw.movie.R;

/*
 *作者:ash
 *TODO:  自定义  滚动条
 *
 */public class ScrollView extends LinearLayout {

    private CheckBox checkBox;

    public void onChecked(){
        checkBox.setChecked(!checkBox.isChecked());
    }

    public ScrollView(Context context) {
        this(context,null);
    }

    public ScrollView(Context context,  AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public ScrollView(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = View.inflate(context, R.layout.customscrollview, this);
        checkBox = view.findViewById(R.id.customscrollbar);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ObjectAnimator animator = ObjectAnimator.ofFloat(checkBox, "translationX", -1000f, 1000f, 1000f);
                animator.setDuration(2000);
                ObjectAnimator animator2 = ObjectAnimator.ofFloat(checkBox, "translationX", -1000f, 0f, 0f);
                animator2.setDuration(1000);
                AnimatorSet set = new AnimatorSet();
                set.play(animator).before(animator2);
                set.start();
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
