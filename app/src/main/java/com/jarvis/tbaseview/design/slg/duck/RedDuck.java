package com.jarvis.tbaseview.design.slg.duck;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.jarvis.tbaseview.design.slg.action.FlyWithWings;
import com.jarvis.tbaseview.design.slg.action.Squck;

/**
 * 作者: tansheng on 2016/3/25.
 * QQ:717549357
 */
public class RedDuck extends Duck {
    private Context context;

    public RedDuck(Context context) {
        super(context);
        this.context = context;
        //红色的鸭子不能飞，并能吱吱叫
        flyAction = new FlyWithWings();
        quackAction = new Squck();
        //为红色的鸭子设置飞行、鸣叫行为
        setFlyAction(flyAction);
        setQuackAction(quackAction);
    }

    @Override
    public void dispaly() {
        Toast.makeText(context, "我是红色的鸭子", Toast.LENGTH_SHORT).show();
        Log.e("", "我是红色的鸭子");
    }
}
