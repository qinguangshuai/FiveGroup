package com.bw.movie.film.synopsis.popwindow.pop;

import android.content.Context;
import android.view.View;
import android.widget.PopupWindow;

import com.bw.movie.film.details.bean.DetailBean;

/*
 *作者:ash
 *TODO:
 *
 */
public class TrailPopupwindow {
    private View mDatail;
    private DetailBean.ResultBean result;
    private PopupWindow popupWindow;
    private Context context ;

    public TrailPopupwindow(View mDatail, DetailBean.ResultBean result, PopupWindow popupWindow, Context context) {
        this.mDatail = mDatail;
        this.result = result;
        this.popupWindow = popupWindow;
        this.context = context;
    }
}
