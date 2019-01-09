package com.bw.movie.util;

import android.widget.Toast;

import com.bw.movie.MyApp;

/*
 *作者:ash
 *TODO:
 *
 */public class ToastUtil {
    private ToastUtil() {
    }

    public static void Toast(int a) {
        Toast.makeText(MyApp.sContext, a + "", Toast.LENGTH_SHORT).show();
    }

    public static void Toast(String a) {
        Toast.makeText(MyApp.sContext, a + "", Toast.LENGTH_SHORT).show();
    }

    public static void Toast(double a) {
        Toast.makeText(MyApp.sContext, a + "", Toast.LENGTH_SHORT).show();
    }

    public  static void Toast(boolean a) {
        Toast.makeText(MyApp.sContext, a + "", Toast.LENGTH_SHORT).show();
    }

    public static void Toast(int a, boolean islong) {
        if (islong) {
            Toast.makeText(MyApp.sContext, a + "", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(MyApp.sContext, a + "", Toast.LENGTH_SHORT).show();
        }
    }

    public  static void Toast(String a, boolean islong) {
        if (islong) {
            Toast.makeText(MyApp.sContext, a + "", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(MyApp.sContext, a + "", Toast.LENGTH_SHORT).show();
        }
    }


    public  static void Toast(double a, boolean islong) {
        if (islong) {
            Toast.makeText(MyApp.sContext, a + "", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(MyApp.sContext, a + "", Toast.LENGTH_SHORT).show();
        }
    }

    public static void Toast(boolean a, boolean islong) {
        if (islong) {
            Toast.makeText(MyApp.sContext, a + "", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(MyApp.sContext, a + "", Toast.LENGTH_SHORT).show();
        }
    }

}
