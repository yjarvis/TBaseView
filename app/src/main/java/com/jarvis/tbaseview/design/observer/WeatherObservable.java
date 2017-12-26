package com.jarvis.tbaseview.design.observer;

import java.util.Observable;

/**
 * 可观察者，实现Observerable，使这个对象成为可观察者
 * 作者: tansheng on 2016/3/29.
 * QQ:717549357
 */
public class WeatherObservable extends Observable {
    private WeatherData data;
    public WeatherObservable(WeatherData data){
        this.data=data;
    }
    //测量得到的数据发生了改变
    private void setMeasurementsChanged(Object obj) {
        //改变状态
        setChanged();
        /*
         * 注意：此处notifyObservers()没有传送数据对象，说明采用的是被动拉取(pull)数据的方式,即需要的时候数据的时候通过get方法调取
         * 如果notifyObservers(Object)传递数据对象，则使用的是主动推送(push)通知的方式,即使不需要，也会将数据推送过去
         **/
        if (obj != null) {
            notifyObservers(obj);
        } else {
            notifyObservers();
        }
    }

    public void setMeasurements(Object obj) {
        setMeasurementsChanged(obj);
    }

    public WeatherData getData() {
        return data;
    }
}
