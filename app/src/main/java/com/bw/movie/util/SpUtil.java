package com.bw.movie.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.bw.movie.MyApp;
/*
*  spUtil 工具类
* */
public class SpUtil {
    private static final String name="login";
    private static final int sp_name=Context.MODE_PRIVATE;
    private static Context mContext=MyApp.sContext;

    public static boolean put(String key,Object value){
        SharedPreferences sharedPreferences=mContext.getSharedPreferences(name,sp_name);
        SharedPreferences.Editor edit=sharedPreferences.edit();

        if(value instanceof String){
            if(!TextUtils.isEmpty((CharSequence) value)){
                edit.putString(key, (String) value);
            }
        }
        else if(value instanceof Boolean){
           edit.putBoolean(key, (Boolean) value);
        }
        else if(value instanceof Float){
            edit.putFloat(key, (Float) value);
        }
        else if(value instanceof Float){
            edit.putFloat(key, (Float) value);
        }else if(value instanceof Integer){
            edit.putInt(key, (Integer) value);
        }else{
            edit.putLong(key, (Long) value);
        }

        boolean commit = edit.commit();
        return commit;
    }

    public static String getString(String key,String array){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(name, sp_name);
        return sharedPreferences.getString(key,array);
    }
    public static int getInt(String key,int defualt){
      SharedPreferences preferences=mContext.getSharedPreferences(name,sp_name);
      return preferences.getInt(key,defualt);
    }
    public static boolean getSb(String key,boolean defualt){
        SharedPreferences preferences=mContext.getSharedPreferences(name,sp_name);
        return preferences.getBoolean(key,defualt);
    }

    public static long getLong(String key,long defualt){
        SharedPreferences preferences=mContext.getSharedPreferences(name,sp_name);
        return preferences.getLong(key,defualt);
    }

    //摧毁方法
    public static void remove(String key){
        SharedPreferences sharedPreferences=mContext.getSharedPreferences(name,sp_name);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.remove(key);
        editor.commit();
    }
}
