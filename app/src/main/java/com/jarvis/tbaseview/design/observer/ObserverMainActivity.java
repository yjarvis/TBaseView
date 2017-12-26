package com.jarvis.tbaseview.design.observer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.jarvis.tbaseviewlib.ui.common.TActivity;

import java.util.Random;

/**
 * 观察者模式：在对象之间定义一对多的依赖，这样依赖，当一个对象改变状态，依赖他的对象都会收到通知
 * 作者: tansheng on 2016/3/25.
 * QQ:717549357
 */
public class ObserverMainActivity extends TActivity {
    private LinearLayout view;
    private Button changeBtn;

    private WeatherData weatherData;//数据类
    private WeatherObservable weatherObservable;//可观察者
    private CurrentDisplay currentDisplay;//观察者，观察WeatherObservable


    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        //创建一个数据类
        weatherData=new WeatherData();
        //创建一个数据可观察者
        weatherObservable=new WeatherObservable(weatherData);
        //注册为WeatherData的观察者
        currentDisplay=new CurrentDisplay(weatherObservable,context);
        initView(false);
        setData();
    }

    @Override
    public void initView(boolean isStatusBar) {
        super.initView(isStatusBar);
        view = new LinearLayout(context);
        changeBtn = new Button(context);
        changeBtn.setText("改变数据");
        changeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //随机一些数据
                Random random=new Random();
                float temperature=random.nextInt(100);
                float humidity=random.nextInt(100);
                float pressure=random.nextInt(100);
                weatherData.setTemperature(temperature);
                weatherData.setHumidity(humidity);
                weatherData.setPressure(pressure);
                //方法一：使用被动拉取数据pull
                weatherObservable.setMeasurements(null);
                //方法二：使用主动推送数据push，根据情况选择一种实现方式即可
//                weatherObservable.setMeasurements(weatherData);

            }
        });
        view.addView(changeBtn);
        setContentView(view);
    }


    @Override
    public void setData() {

    }

    @Override
    public void requestData(boolean isShow) {

    }
}
