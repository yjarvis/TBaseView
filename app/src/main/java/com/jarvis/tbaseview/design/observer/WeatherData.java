package com.jarvis.tbaseview.design.observer;

/**
 * 数据类
 * 作者: tansheng on 2016/3/29.
 * QQ:717549357
 */
public class WeatherData {
    private float temperature;//温度
    private float humidity;//湿度
    private float pressure;//气压

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }
}
