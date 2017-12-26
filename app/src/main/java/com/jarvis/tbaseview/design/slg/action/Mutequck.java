package com.jarvis.tbaseview.design.slg.action;

import android.content.Context;
import android.widget.Toast;

import com.jarvis.tbaseview.design.slg.actioninterface.QuackAction;

/**
 * 实现鸭子的鸣叫行为
 * 作者: tansheng on 2016/3/25.
 * QQ:717549357
 */
public class Mutequck implements QuackAction {
    @Override
    public void quack(Context context) {
        //哑巴鸭子，不会叫的鸭子
        Toast.makeText(context, "哑巴了", Toast.LENGTH_SHORT).show();
    }
}
