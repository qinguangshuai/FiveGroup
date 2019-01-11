package com.bw.movie.film.popwindow;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.bw.movie.MyApp;
import com.bw.movie.R;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

/*
 *▶ 作者: ash ◀
 *@date:2019/1/10 19:24
 *(◕ᴗ◕✿)
 *
 *页面功能-1： 【】 ✔
 *页面功能-2： 【】 ✔
 *页面功能-3： 【】 ✔
 *页面功能-4： 【】 ✔
 *页面功能-5： 【】 ✔
 *页面功能-6： 【】 ✔
 *
 */
public class ScrollWindow {

    private Context mContext ;


    public ScrollWindow(Context context) {
        this.mContext = context;
        doScroll();
    }

    public PopupWindow getmPopupWindow() {
        return mPopupWindow;
    }

    private PopupWindow  mPopupWindow ;



    public void doScroll(){
        View view = View.inflate(MyApp.sContext, R.layout.popwindowscroll,null);
        SimpleDraweeView mSimpleDraweeView =  view.findViewById(R.id.simpscroll);
        Uri uri1 = Uri.parse("res://drawable/" + R.mipmap.chilun);
        //不多解释
        AbstractDraweeController build = Fresco.newDraweeControllerBuilder()
                .setUri(uri1)
                .setAutoPlayAnimations(true)
                .build();
        mSimpleDraweeView.setController(build);
        mPopupWindow = new PopupWindow(view,LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);

    }





    public void showPop(View v){
//        Resources resources = mContext.getResources();
//        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
//        int h = displayMetrics.heightPixels;
        mPopupWindow.setAnimationStyle(R.style.popwin_anim_style);
        mPopupWindow.showAtLocation(v.getRootView(),Gravity.BOTTOM,0,0);

    }


    public void dismissPop(){
        mPopupWindow.dismiss();
    }


}
