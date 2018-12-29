package com.bw.movie.util;

import android.widget.Toast;

import com.bw.movie.MyApp;

/*
 *作者:ash
 *TODO:
 *
 */public class ToastUtil {


    public void Toast(int a) {
        Toast.makeText(MyApp.context, a + "", Toast.LENGTH_SHORT).show();
    }

    public void Toast(String a) {
        Toast.makeText(MyApp.context, a + "", Toast.LENGTH_SHORT).show();
    }

    public void Toast(double a) {
        Toast.makeText(MyApp.context, a + "", Toast.LENGTH_SHORT).show();
    }

    public void Toast(boolean a) {
        Toast.makeText(MyApp.context, a + "", Toast.LENGTH_SHORT).show();
    }

    public void Toast(int a, boolean islong) {
        if (islong) {
            Toast.makeText(MyApp.context, a + "", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(MyApp.context, a + "", Toast.LENGTH_SHORT).show();
        }
    }

    public void Toast(String a, boolean islong) {
        if (islong) {
            Toast.makeText(MyApp.context, a + "", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(MyApp.context, a + "", Toast.LENGTH_SHORT).show();
        }
    }


    public void Toast(double a, boolean islong) {
        if (islong) {
            Toast.makeText(MyApp.context, a + "", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(MyApp.context, a + "", Toast.LENGTH_SHORT).show();
        }
    }

    public void Toast(boolean a, boolean islong) {
        if (islong) {
            Toast.makeText(MyApp.context, a + "", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(MyApp.context, a + "", Toast.LENGTH_SHORT).show();
        }
    }

}
