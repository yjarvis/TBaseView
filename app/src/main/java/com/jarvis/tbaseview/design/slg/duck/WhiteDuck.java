package com.jarvis.tbaseview.design.slg.duck;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.jarvis.tbaseview.design.slg.action.FlyWithWings;
import com.jarvis.tbaseview.design.slg.action.Quck;

/**
 * 作者: tansheng on 2016/3/25.
 * QQ:717549357
 */
public class WhiteDuck extends Duck {

    private Context context;

    public WhiteDuck(Context context){
        super(context);
        this.context=context;
        //白色的鸭子可以飞，并能嘎嘎叫
        flyAction=new FlyWithWings();
        quackAction=new Quck();
        //为红色的鸭子设置飞行、鸣叫行为
        setFlyAction(flyAction);
        setQuackAction(quackAction);
    }

    @Override
    public void dispaly() {
        Toast.makeText(context, "我是白色的鸭子", Toast.LENGTH_SHORT).show();
        Log.e("","我是白色的鸭子");
    }
}
