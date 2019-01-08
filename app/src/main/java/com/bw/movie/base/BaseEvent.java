package com.bw.movie.base;

import org.greenrobot.eventbus.EventBus;

public class BaseEvent {

    public BaseEvent() {
    }

    /*取消订阅*/
    public static void unregister(Object subscriber){
        EventBus.getDefault().unregister(subscriber);//取消注册
    }

    /*订阅事件*/
    public static void register(Object subscriber){
        EventBus.getDefault().register(subscriber);//开始订阅
    }

    /*发送事件*/
    public static void post(Object event){//开始发送
       EventBus.getDefault().post(event);
    }

}
