package com.bw.movie.custom;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;

/*
 *作者:ash
 *TODO:  自定义搜索框框
 *
 */public class SearchView extends LinearLayout {

    private boolean isShow = false;
    private EditText editText;
    private Click click;

    //click set方法
    public void setClick(Click click) {
        this.click = click;
    }

    //获取输入的值
    public String getEditText() {
        return editText.getText().toString();
    }

    public SearchView(Context context) {
        this(context, null);
    }

    public SearchView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public SearchView(final Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = View.inflate(context, R.layout.customsearchview, this);
        final LinearLayout linearLayout = view.findViewById(R.id.cumtomlinear);
        TextView cumtomsearch = view.findViewById(R.id.cumtomsearch);
        TextView cumtomresult = view.findViewById(R.id.cumtomresult);
        editText = view.findViewById(R.id.cumtomedittext);

        //放大镜 点击事件
        cumtomsearch.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Resources resources = context.getResources();
                DisplayMetrics displayMetrics = resources.getDisplayMetrics();
                int widthPixels = displayMetrics.widthPixels;
                if (isShow == false) {
                    ObjectAnimator animator = ObjectAnimator.ofFloat(linearLayout, "translationX", 0f, -(widthPixels * 3 / 5), -(widthPixels * 2 / 5));
                    animator.setDuration(1000);
                    animator.start();
                    isShow = true;
                } else if (isShow == true) {
                    ObjectAnimator animator = ObjectAnimator.ofFloat(linearLayout, "translationX", -(widthPixels * 2 / 5), 0, 0);
                    animator.setDuration(1000);
                    animator.start();
                    isShow = false;
                }
            }
        });

        //点击搜索
        cumtomresult.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                click.onClickListener(v,editText.getText().toString());

            }
        });

        editText.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_ENTER&& event.getAction() == KeyEvent.ACTION_DOWN) {
                    InputMethodManager imm = (InputMethodManager) v.getContext()
                            .getSystemService(Context.INPUT_METHOD_SERVICE);
                    String text = editText.getText().toString();
                    if(imm.isActive()) {
                        imm.hideSoftInputFromWindow(v.getApplicationWindowToken(),0);
                    }

                    if(TextUtils.isEmpty(text)){
                        Toast.makeText(context,"请输入您想要搜索的地址",Toast.LENGTH_SHORT).show();
                        return true;
                    }else {
                        click.onClickListener(v,text);
                    }
                }
                return false;
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


    public interface Click{
        void onClickListener(View v, String s);
    }
}
