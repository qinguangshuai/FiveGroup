package com.bw.movie.util;

import java.util.List;

/*
 *作者:ash
 *TODO:
 *
 */public class EmptyUtil {

    private EmptyUtil() {
    }
     //判断是否为空
     public static boolean isNull(List o){
        if(o!=null && o.size()>0){
            return false;
        }else {
            return true;
        }
     }

}
