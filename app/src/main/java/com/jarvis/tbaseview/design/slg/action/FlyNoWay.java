package com.jarvis.tbaseview.design.slg.action;

import android.content.Context;
import android.widget.Toast;

import com.jarvis.tbaseview.design.slg.actioninterface.FlyAction;

/**
 * 实现鸭子的飞行行为
 * 作者: tansheng on 2016/3/25.
 * QQ:717549357
 */
public class FlyNoWay implements FlyAction {
    @Override
    public void fly(Context context) {
        //什么都不做，鸭子不想飞
        Toast.makeText(context, "鸭子不想飞", Toast.LENGTH_SHORT).show();
    }
}
