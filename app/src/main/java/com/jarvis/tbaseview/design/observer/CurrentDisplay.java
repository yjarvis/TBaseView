package com.jarvis.tbaseview.design.observer;

import android.content.Context;

import com.jarvis.tbaseviewlib.utils.TUtils;

import java.util.Observable;
import java.util.Observer;

/**
 * 当前情况展示类,实现Observer，使其成为观察者，当WeatherData发生改变时，更改展示内容
 * 作者: tansheng on 2016/3/29.
 * QQ:717549357
 */
public class CurrentDisplay implements Observer,DisplayElement{
    private Context context;

    public CurrentDisplay(Observable observable,Context context){
        this.context=context;
        //注册为WeatherData的观察者
        observable.addObserver(this);
    }

    //更新数据
    @Override
    public void update(Observable obs, Object data) {
        //如果这个可观察者是WeatherData
        if (obs instanceof WeatherObservable){
            WeatherData  weatherData=null;
            if (data!=null){
                //推送过来的数据
                 weatherData= (WeatherData) data;
            }else {
                //主动拉取数据
                weatherData=  ((WeatherObservable) obs).getData();
            }

            display(context,weatherData);
        }
    }

    @Override
    public void display(Context context,WeatherData data) {
        //实现更新展示的方法
        TUtils.showToast(context,"当前温度："+data.getTemperature()+"℃ 当前湿度："+data.getHumidity()+" 当前气压："+data.getPressure());
    }
}
