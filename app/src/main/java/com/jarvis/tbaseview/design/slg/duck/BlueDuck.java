package com.jarvis.tbaseview.design.slg.duck;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.jarvis.tbaseview.design.slg.action.FlyNoWay;
import com.jarvis.tbaseview.design.slg.action.Mutequck;

/**
 * 作者: tansheng on 2016/3/25.
 * QQ:717549357
 */
public class BlueDuck extends Duck {
    private Context context;

    public BlueDuck(Context context) {
        super(context);
        this.context = context;
        //蓝色的鸭子不能飞，也不能叫
        flyAction = new FlyNoWay();
        quackAction = new Mutequck();

        setFlyAction(flyAction);
        setQuackAction(quackAction);

    }

    @Override
    public void dispaly() {
        Toast.makeText(context, "我是蓝色的鸭子",Toast.LENGTH_SHORT).show();
        Log.e("", "我是蓝色的鸭子");
    }
}
