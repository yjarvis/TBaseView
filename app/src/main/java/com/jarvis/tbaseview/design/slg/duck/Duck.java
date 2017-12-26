package com.jarvis.tbaseview.design.slg.duck;

import android.content.Context;
import android.util.Log;

import com.jarvis.tbaseview.design.slg.actioninterface.FlyAction;
import com.jarvis.tbaseview.design.slg.actioninterface.QuackAction;

/**
 * 策略模式：定义了算法族(flyAction,quackAction)，分别封装起来，让他们之间可以互相替换，此模式让算法的变化独立于使用算法的客户
 *
 * 鸭子父类，实现鸭子具有的共同行为
 * 作者: tansheng on 2016/3/25.
 * QQ:717549357
 */
public abstract class Duck {
    private Context context;
    public Duck(Context context){
        this.context=context;
    }
    //飞行
    public FlyAction flyAction;
    //鸣叫
    public QuackAction quackAction;
    //游泳
    public void swim(){
        Log.e("tag","所有鸭子都会游泳！！！");
    }
    //其他
    public abstract void dispaly();
    //为鸭子设置飞行行为
    public void setFlyAction(FlyAction flyAction) {
        this.flyAction = flyAction;
    }
    //为鸭子设置鸣叫行为
    public void setQuackAction(QuackAction quackAction) {
        this.quackAction = quackAction;
    }
    //执行鸭子的飞行行为
    public void doFlyAction(){
        flyAction.fly(context);
    }
    //执行鸭子的鸣叫行为
    public void doQuackAction(){
        quackAction.quack(context);
    }
}
